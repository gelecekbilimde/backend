package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostManagerControl;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.mapper.PostDomainToPostProcessModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostManagerControlToPostProcessModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostModelToPostDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.gelecekbilimde.scienceplatform.post.repository.PostProcessRepository;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.settings.dto.domain.SettingsDomain;
import org.gelecekbilimde.scienceplatform.settings.service.SettingsService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostProcessServiceImp implements PostProcessService {

	private final PostProcessRepository postProcessRepository;
	private final PostRepository postRepository;
	private final Identity identity;

	private final SettingsService settingsService;

	private static final PostDomainToPostProcessModelMapper postDomainToPostProcessModelMapper = PostDomainToPostProcessModelMapper.initialize();
	private static final PostManagerControlToPostProcessModelMapper postManagerControlToPostProcessModelMapper = PostManagerControlToPostProcessModelMapper.initialize();
	private static final PostModelToPostDomainMapper postModelToPostDomain = PostModelToPostDomainMapper.initialize();

	public void savePostProcess(PostDomain postDomain, boolean done) {

		final PostProcess postProcess = postDomainToPostProcessModelMapper.mapForSaving(postDomain, identity.getUserId(), done);
		postProcessRepository.save(postProcess);
	}

	@Transactional
	public void updatePostProcess(PostManagerControl postManagerControl) {

		Optional<PostProcess> accessProcess = accessProcess(postManagerControl.getProcess(), postManagerControl.getPostId());

		if (accessProcess.isEmpty()) {
			throw new ClientException("Post son kontrol aşaması için uygun değil");
		}

		PostProcess postProcess = accessProcess.get();

		if (postProcess.getUserId().equals(identity.getUserId()) && postManagerControl.getProcess() != Process.CREATOR_CONTROL) {
			throw new AuthorizationServiceException("Kendi postunuza işlem yapmazsınız");
		}

		if (postManagerControl.getProcess() == Process.LAST_CONTROL && !identity.hasPermission("admin:last:control")) {
			throw new AuthorizationServiceException("Son onay işlemini yapmaya yetkiniz yok");
		}



		this.updateControl(postProcess, postManagerControl);

		switch (postManagerControl.getProcess()) {
			case CONTROL -> this.control(postManagerControl, Process.LAST_CONTROL);
			case LAST_CONTROL -> this.completeLastControl(postManagerControl);
			case CREATOR_CONTROL -> this.completeCreatorControl();
		}
	}

	private void updateControl(PostProcess postProcess, PostManagerControl postManagerControl) {

		if (postManagerControl.getHeader().isEmpty()) {
			postManagerControl.setHeader(postProcess.getHeader());
		}

		if (postManagerControl.getContent().isEmpty()) {
			postManagerControl.setContent(postProcess.getContent());
		}

		if (postManagerControl.getCategory().getName().isEmpty()) {
			postManagerControl.setCategory(postProcess.getCategory());
		}

		Helper helper = new Helper();
		postManagerControl.setSlug(helper.slugify(postManagerControl.getHeader()));

		final PostProcess postProcessBuild = postManagerControlToPostProcessModelMapper.mapForSaving(postManagerControl, identity.getUserId(), true);
		postProcessRepository.save(postProcessBuild);

	}

	private Optional<PostProcess> accessProcess(Process currentProcess, String postId) {
		PostProcess postProcess = postProcessRepository.getTopByPostIdOrderByCreatedAtDesc(postId)
			.orElseThrow(() -> new ClientException("Postun aktif süreci bulunamadı"));

		Process accessibleProcess = switch (currentProcess) {
            case CONTROL -> Process.CREATE;
            case LAST_CONTROL -> Process.CONTROL;
            case CREATOR_CONTROL -> Process.LAST_CONTROL;
            default -> throw new ClientException("Yanlış bir status: " + postId + "--->" + currentProcess);
        };

        if (!postProcess.getProcess().equals(accessibleProcess)) {
			return Optional.empty();
		}
		return Optional.of(postProcess);

	}


	private Post control(PostManagerControl postManagerControl, Process nextProcess) {
		final Post post = postRepository.getById(postManagerControl.getPostId());

		postManagerControl.setProcess(nextProcess);
		post.setLastProcess(nextProcess);

		if (!postManagerControl.isCopyrightControl() || !postManagerControl.isTypoControl() || !postManagerControl.isDangerousControl()) {
			postManagerControl.setProcess(Process.REJECT);
			post.setLastProcess(Process.REJECT);
			return  post;
		}

		final PostDomain postDomain = postModelToPostDomain.map(post);

		boolean isChanged = false;
		if(!postDomain.getHeader().equals(postManagerControl.getHeader())){
			isChanged = true;
		}

		if (!postDomain.getContent().equals(postManagerControl.getContent())){
			isChanged = true;
		}

		if (!postDomain.getCategory().getName().equals(postManagerControl.getCategory().getName())){
			isChanged = true;
		}

		if (!isChanged){
			postManagerControl.setProcess(Process.CREATOR_CONTROL);
			final PostProcess postProcessBuild = postManagerControlToPostProcessModelMapper.mapForSaving(postManagerControl, identity.getUserId(), true);
			postProcessRepository.save(postProcessBuild);
			post.setLastProcess(Process.CREATOR_CONTROL);
			postRepository.save(post);
			return post;
		}

		final PostProcess postProcessBuild = postManagerControlToPostProcessModelMapper.mapForSaving(postManagerControl, identity.getUserId(), true);
		postProcessRepository.save(postProcessBuild);
		postRepository.save(post);

		return post;
	}

	private void completeLastControl(PostManagerControl postManagerControl) {

		final Post post = control(postManagerControl, Process.PUBLISH);

		if (post.getLastProcess() != Process.PUBLISH){
			return;
		}

	//	final SettingsDomain settings = settingsService.getSettings();
	}

	private void completeCreatorControl() {
	}

}

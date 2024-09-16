package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.exception.ClientException;
import org.gelecekbilimde.scienceplatform.post.exception.PostNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.post.exception.PostProcessNotFoundByPostIdException;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostProcessEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostEntityToPostMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostManagerControlRequestToPostProcessEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostToPostProcessEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.PostManagerControlRequest;
import org.gelecekbilimde.scienceplatform.post.repository.PostProcessRepository;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.util.PostUtil;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PostProcessServiceImpl implements PostProcessService {

	private final PostProcessRepository postProcessRepository;
	private final PostRepository postRepository;
	private final Identity identity;

	private final PostToPostProcessEntityMapper postToPostProcessEntityMapper = PostToPostProcessEntityMapper.initialize();
	private final PostManagerControlRequestToPostProcessEntityMapper postManagerControlRequestToPostProcessEntityMapper = PostManagerControlRequestToPostProcessEntityMapper.initialize();
	private final PostEntityToPostMapper postEntityToPostMapper = PostEntityToPostMapper.initialize();

	public void savePostProcess(Post post, boolean done) {

		final PostProcessEntity postProcessEntity = postToPostProcessEntityMapper.mapForSaving(post, identity.getUserId(), done);
		postProcessRepository.save(postProcessEntity);
	}

	@Transactional
	public void updatePostProcess(PostManagerControlRequest postManagerControlRequest) {

		Optional<PostProcessEntity> accessProcess = accessProcess(postManagerControlRequest.getProcess(), postManagerControlRequest.getPostId());

		if (accessProcess.isEmpty()) {
			throw new ClientException("Post son kontrol aşaması için uygun değil");
		}

		PostProcessEntity postProcessEntity = accessProcess.get();

		if (postProcessEntity.getUserId().equals(identity.getUserId()) && postManagerControlRequest.getProcess() != Process.CREATOR_CONTROL) {
			throw new AuthorizationServiceException("Kendi postunuza işlem yapmazsınız");
		}

		if (postManagerControlRequest.getProcess() == Process.LAST_CONTROL && !identity.hasPermission("admin:last:control")) {
			throw new AuthorizationServiceException("Son onay işlemini yapmaya yetkiniz yok");
		}


		this.updateControl(postProcessEntity, postManagerControlRequest);

		switch (postManagerControlRequest.getProcess()) { // TODO : default case eklenmeli
			case CONTROL -> this.control(postManagerControlRequest, Process.LAST_CONTROL);
			case LAST_CONTROL -> this.completeLastControl(postManagerControlRequest);
			case CREATOR_CONTROL -> this.completeCreatorControl();
		}
	}

	private void updateControl(PostProcessEntity postProcessEntity, PostManagerControlRequest postManagerControlRequest) {

		if (postManagerControlRequest.getHeader().isEmpty()) {
			postManagerControlRequest.setHeader(postProcessEntity.getHeader());
		}

		if (postManagerControlRequest.getContent().isEmpty()) {
			postManagerControlRequest.setContent(postProcessEntity.getContent());
		}

		//TODO: Category kontrolü yapılacak

		postManagerControlRequest.setSlug(PostUtil.slugging(postManagerControlRequest.getHeader()));

		final PostProcessEntity postProcessEntityBuild = postManagerControlRequestToPostProcessEntityMapper.mapForSaving(postManagerControlRequest, identity.getUserId(), true);
		postProcessRepository.save(postProcessEntityBuild);

	}

	private Optional<PostProcessEntity> accessProcess(Process currentProcess, String postId) {

		PostProcessEntity postProcessEntity = postProcessRepository.getTopByPostIdOrderByCreatedAtDesc(postId)
			.orElseThrow(() -> new PostProcessNotFoundByPostIdException(postId));

		Process accessibleProcess = switch (currentProcess) {
			case CONTROL -> Process.CREATE;
			case LAST_CONTROL -> Process.CONTROL;
			case CREATOR_CONTROL -> Process.LAST_CONTROL;
			default -> throw new ClientException("Yanlış bir status: " + postId + "--->" + currentProcess);
		};

		if (!postProcessEntity.getProcess().equals(accessibleProcess)) {
			return Optional.empty();
		}
		return Optional.of(postProcessEntity);

	}


	private PostEntity control(PostManagerControlRequest postManagerControlRequest, Process nextProcess) {

		final PostEntity postEntity = postRepository.findById(postManagerControlRequest.getPostId())
			.orElseThrow(() -> new PostNotFoundByIdException(postManagerControlRequest.getPostId()));

		postManagerControlRequest.setProcess(nextProcess);
		postEntity.setLastProcess(nextProcess);

		if (!postManagerControlRequest.isCopyrightControl() || !postManagerControlRequest.isTypoControl() || !postManagerControlRequest.isDangerousControl()) {
			postManagerControlRequest.setProcess(Process.REJECT);
			postEntity.setLastProcess(Process.REJECT);
			return postEntity;
		}

		final Post post = postEntityToPostMapper.map(postEntity);

		boolean isHeaderChanged = !post.getHeader().equals(postManagerControlRequest.getHeader());
		boolean isContentChanged = !post.getContent().equals(postManagerControlRequest.getContent());
		boolean isNameChanged = !post.getCategoryEntity().getName().equals(postManagerControlRequest.getCategory().getName());
		if (!isHeaderChanged || !isContentChanged || !isNameChanged) {
			postManagerControlRequest.setProcess(Process.CREATOR_CONTROL);
			final PostProcessEntity postProcessEntityBuild = postManagerControlRequestToPostProcessEntityMapper.mapForSaving(postManagerControlRequest, identity.getUserId(), true);
			postProcessRepository.save(postProcessEntityBuild);
			postEntity.setLastProcess(Process.CREATOR_CONTROL);
			postRepository.save(postEntity);
			return postEntity;
		}

		final PostProcessEntity postProcessEntityBuild = postManagerControlRequestToPostProcessEntityMapper.mapForSaving(postManagerControlRequest, identity.getUserId(), true);
		postProcessRepository.save(postProcessEntityBuild);
		postRepository.save(postEntity);

		return postEntity;
	}

	private void completeLastControl(PostManagerControlRequest postManagerControlRequest) {

		final PostEntity postEntity = control(postManagerControlRequest, Process.PUBLISH);

		if (postEntity.getLastProcess() != Process.PUBLISH) {
			return;
		}

		//	final SettingsDomain settings = settingsService.getSettings();
	}

	private void completeCreatorControl() { // TODO : bu metot doldurulduğu ve kullanıldığı zaman yazılmalı
	}

}

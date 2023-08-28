package org.gelecekbilimde.scienceplatform.postProcess.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.postProcess.dto.Domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.postProcess.mapper.PostProcessCreateToPostProcessModelMapper;
import org.gelecekbilimde.scienceplatform.postProcess.model.PostProcess;
import org.gelecekbilimde.scienceplatform.postProcess.repository.PostProcessRepository;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostProcessService {

	private final PostProcessRepository postProcessRepository;
	private final Identity identity;
	private static final PostProcessCreateToPostProcessModelMapper postProcessCreateToPostProcessModel = PostProcessCreateToPostProcessModelMapper.initialize();

	@Transactional
	public void savePostAfter(PostProcessCreate postProcessCreate) {

		final PostProcess postProcess = postProcessCreateToPostProcessModel.mapForSaving(postProcessCreate, identity.getUserId());
		postProcessRepository.save(postProcess);
	}
}

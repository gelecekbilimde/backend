package org.gelecekbilimde.scienceplatform.postprocess.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.postprocess.dto.domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.postprocess.mapper.PostProcessCreateToPostProcessModelMapper;
import org.gelecekbilimde.scienceplatform.postprocess.model.PostProcess;
import org.gelecekbilimde.scienceplatform.postprocess.repository.PostProcessRepository;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostProcessService {

	private final PostProcessRepository postProcessRepository;
	private final Identity identity;
	private static final PostProcessCreateToPostProcessModelMapper postProcessCreateToPostProcessModel = PostProcessCreateToPostProcessModelMapper.initialize();

	public void savePostAfter(PostProcessCreate postProcessCreate) {

		final PostProcess postProcess = postProcessCreateToPostProcessModel.mapForSaving(postProcessCreate, identity.getUserId());
		postProcessRepository.save(postProcess);
	}
}

package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.post.PostCreateDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.PostProcess;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.PostProcessRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostProcessService {

	private final PostProcessRepository postProcessRepository;

	@Transactional
	public void save(Post post, PostCreateDTO postCreateDTO, User user) {

		var postProcess = PostProcess
				.builder()
				.header(postCreateDTO.getHeader())
				.content(postCreateDTO.getContent())
				.process(postCreateDTO.getLastProcess())
				.postProcess(post)
				.user(user)
				.slug(postCreateDTO.getSlug())
				.build();

		postProcessRepository.save(postProcess);
	}
}

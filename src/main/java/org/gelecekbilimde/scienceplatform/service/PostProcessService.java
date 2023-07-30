package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.PostDTO;
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
	public void save(Post post, PostDTO postDTO, User user) {

		var postProcess = PostProcess
				.builder()
				.header(postDTO.getHeader())
				.content(postDTO.getContent())
				.process(postDTO.getLastProcess())
				.postProcess(post)
				.user(user)
				.slug(postDTO.getSlug())
				.build();

		postProcessRepository.save(postProcess);
	}
}

package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;

import org.gelecekbilimde.scienceplatform.dto.PostDTO;
import org.gelecekbilimde.scienceplatform.events.PostProcessCreateEvent;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



// https://drive.google.com/file/d/1F8TCSt_Oo4Yjl2q32r3XjoTarcLn85KI/view?usp=sharing
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final ApplicationContext applicationContext;

	@Autowired
	public PostService(ApplicationContext applicationContext,PostRepository postRepository) {
		this.applicationContext = applicationContext;
		this.postRepository = postRepository;
	}

	@Transactional
	public PostDTO save (PostDTO postDTO,User user){
		Helper helper = new Helper();

		postDTO.setLastProcess(PostProcessEnum.CREATE);
		postDTO.setSlug(helper.slugify(postDTO.getHeader()));

		var post = Post.builder()
				.header(postDTO.getHeader())
				.content(postDTO.getContent())
				.user(user)
				.likeCount(postDTO.getLikeCount())
				.active(postDTO.getActive())
				.lastProcess(postDTO.getLastProcess())
				.slug(postDTO.getSlug())
				.build();

		postRepository.save(post);
		applicationContext.publishEvent(new PostProcessCreateEvent(post,postDTO, user));
		return postDTO;
	}

}

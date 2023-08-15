package org.gelecekbilimde.scienceplatform.listeners;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.Post.PostCreateDTO;
import org.gelecekbilimde.scienceplatform.events.PostProcessCreateEvent;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.service.PostProcessService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PostProcessCreateListener implements ApplicationListener<PostProcessCreateEvent> {

	private final PostProcessService postProcessService;
	@Override
	public void onApplicationEvent(PostProcessCreateEvent event) {
		Post post = event.getPost();
		PostCreateDTO postCreateDTO = event.getPostCreateDTO();
		User user = event.getUser();

		postProcessService.save(post, postCreateDTO, user);
	}
}

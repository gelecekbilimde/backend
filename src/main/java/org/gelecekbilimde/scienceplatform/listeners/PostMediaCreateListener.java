package org.gelecekbilimde.scienceplatform.listeners;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.PostMediaDTO;
import org.gelecekbilimde.scienceplatform.events.PostMediaCreateEvent;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PostMediaCreateListener implements ApplicationListener<PostMediaCreateEvent> {

	private final PostService postService;
	@Override
	public void onApplicationEvent(PostMediaCreateEvent event) {
		Integer postId = event.getPostId();
		List<PostMediaDTO> postMediaDTOs = event.getPostMediaDTOs();
		User user = event.getUser();

		postService.savePostMedia(postId, postMediaDTOs, user);
	}
}

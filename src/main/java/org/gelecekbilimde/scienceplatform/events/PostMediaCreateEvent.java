package org.gelecekbilimde.scienceplatform.events;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.dto.PostMediaDTO;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.List;

@Getter
public class PostMediaCreateEvent extends ApplicationEvent {

	private final List <PostMediaDTO> postMediaDTOs;
	private final Integer postId;
	private final User user;

	public PostMediaCreateEvent(PostService postService, List <PostMediaDTO> postMediaDTOs, Integer postId, User user) {
		super(postService);
		this.postMediaDTOs = postMediaDTOs;
		this.postId = postId;
		this.user = user;
	}

	public PostMediaCreateEvent(PostService postService, List <PostMediaDTO> postMediaDTOs, Integer postId, Clock clock, User user) {
		super(postService, clock);
		this.postMediaDTOs = postMediaDTOs;
		this.postId = postId;
		this.user = user;
	}

}

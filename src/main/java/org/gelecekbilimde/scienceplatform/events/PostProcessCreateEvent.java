package org.gelecekbilimde.scienceplatform.events;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.dto.PostDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class PostProcessCreateEvent extends ApplicationEvent {

	private final PostDTO postDTO;
	private final Post post;
	private final User user;

	public PostProcessCreateEvent(Post post, PostDTO postDTO, User user) {
		super(post);
		this.postDTO = postDTO;
		this.post = post;
		this.user = user;
	}

	public PostProcessCreateEvent(Post post, PostDTO postDTO, Clock clock, User user) {
		super(post, clock);
		this.postDTO = postDTO;
		this.post = post;
		this.user = user;
	}

}

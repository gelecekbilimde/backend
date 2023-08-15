package org.gelecekbilimde.scienceplatform.events;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.dto.Post.PostCreateDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class PostProcessCreateEvent extends ApplicationEvent {

	private final PostCreateDTO postCreateDTO;
	private final Post post;
	private final User user;

	public PostProcessCreateEvent(Post post, PostCreateDTO postCreateDTO, User user) {
		super(post);
		this.postCreateDTO = postCreateDTO;
		this.post = post;
		this.user = user;
	}

	public PostProcessCreateEvent(Post post, PostCreateDTO postCreateDTO, Clock clock, User user) {
		super(post, clock);
		this.postCreateDTO = postCreateDTO;
		this.post = post;
		this.user = user;
	}

}

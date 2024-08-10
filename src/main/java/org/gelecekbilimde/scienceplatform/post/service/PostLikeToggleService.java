package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.model.PostLike;

public interface PostLikeToggleService {

	PostLike toggleLikeOfPost(String id);

}

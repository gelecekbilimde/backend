package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.dto.domain.PostLikeDomain;

public interface PostLikeToggleService {

	PostLikeDomain toggleLikeOfPost(String id);

}

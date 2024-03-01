package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;

public interface PostLikeToggleService {

	Response<PostLikeResponse> toggleLikeOfPost(String id);

}

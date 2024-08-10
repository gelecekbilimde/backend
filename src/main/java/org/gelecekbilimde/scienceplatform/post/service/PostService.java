package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.common.model.Paging;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.PostCreateRequest;

public interface PostService {

	Post save(PostCreateRequest postCreateRequest);

	Paging<Post> getPostListAdmin(AdminPostListRequest listRequest);

}

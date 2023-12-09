package org.gelecekbilimde.scienceplatform.post.service;


import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;

public interface PostService {

	PostDomain save(PostCreateRequest postCreateRequest);
	Paging<PostDomain> getPostListAdmin(AdminPostListRequest listRequest);

}

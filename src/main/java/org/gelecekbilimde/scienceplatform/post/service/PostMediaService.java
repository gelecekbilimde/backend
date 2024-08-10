package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.request.PostMediaCreateRequest;

import java.util.List;

public interface PostMediaService {

	List<PostMedia> savePostMedias(List<PostMediaCreateRequest> postMediaCreateRequestList);

}

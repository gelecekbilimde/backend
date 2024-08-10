package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.request.PostManagerControlRequest;

public interface PostProcessService {

	void savePostProcess(Post post, boolean done);

	void updatePostProcess(PostManagerControlRequest postManagerControlRequest);
}

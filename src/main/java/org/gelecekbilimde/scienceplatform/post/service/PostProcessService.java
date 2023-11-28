package org.gelecekbilimde.scienceplatform.post.service;


import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostProcessDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostManagerControl;

public interface PostProcessService {

	void savePostProcess(PostDomain postDomain, boolean done);

	void updatePostProcess(PostManagerControl postManagerControl);
}

package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleSelfApplicationListRequest;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;

public interface RoleSelfApplicationService {

	BasePage<RoleApplication> findAll(RoleSelfApplicationListRequest listRequest);

	void createAuthorApplication();

	void createModeratorApplication();

	void cancel();

}

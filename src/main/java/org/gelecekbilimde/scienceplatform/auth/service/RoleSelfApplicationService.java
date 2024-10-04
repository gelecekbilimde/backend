package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;

public interface RoleSelfApplicationService {

	BasePage<RoleApplication> findAll(RoleApplicationListRequest listRequest);

	void createAuthorApplication();

	void createModeratorApplication();

	void cancel();

}

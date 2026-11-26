package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;

public interface RoleApplicationService {

	BasePage<RoleApplication> findAll(RoleApplicationListRequest listRequest);

	void approve(String id);

	void reject(String id);

}

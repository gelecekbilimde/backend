package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleApplicationService {

	Page<RoleApplication> findAll(List<RoleChangeRequestsFilter> filters, int page, int size);

	void createAuthorApplication();

	void createModeratorApplication();

	void approve(String id);

	void reject(String id);

}

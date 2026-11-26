package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyConcludedException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationReadPort;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationSavePort;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.port.UserSavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class RoleApplicationServiceImpl implements RoleApplicationService {

	private final RoleApplicationReadPort roleApplicationReadPort;
	private final RoleApplicationSavePort roleApplicationSavePort;
	private final UserSavePort userSavePort;


	@Override
	public BasePage<RoleApplication> findAll(final RoleApplicationListRequest listRequest) {
		return roleApplicationReadPort
			.findAll(listRequest.getPageable(), listRequest.getFilter());
	}


	@Override
	@Transactional
	public void approve(final String id) {

		RoleApplication application = roleApplicationReadPort.findById(id)
			.orElseThrow(() -> new RoleApplicationNotFoundByIdException(id));

		if (application.isConcluded()) {
			throw new RoleApplicationAlreadyConcludedException(id);
		}

		User user = application.getUser();
		user.setRole(application.getRole());
		userSavePort.save(user);

		application.approve();
		roleApplicationSavePort.save(application);
	}


	@Override
	public void reject(final String id) {

		RoleApplication application = roleApplicationReadPort.findById(id)
			.orElseThrow(() -> new RoleApplicationNotFoundByIdException(id));

		if (application.isConcluded()) {
			throw new RoleApplicationAlreadyConcludedException(id);
		}

		application.reject();
		roleApplicationSavePort.save(application);
	}

}

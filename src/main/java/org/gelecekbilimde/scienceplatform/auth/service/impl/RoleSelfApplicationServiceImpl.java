package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyExistException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationNotFoundByUserIdAndStatusException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundByNameException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleSelfApplicationFilter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleSelfApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationReadPort;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationSavePort;
import org.gelecekbilimde.scienceplatform.auth.port.RoleReadPort;
import org.gelecekbilimde.scienceplatform.auth.service.RoleSelfApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class RoleSelfApplicationServiceImpl implements RoleSelfApplicationService {

	private final RoleApplicationReadPort roleApplicationReadPort;
	private final RoleApplicationSavePort roleApplicationSavePort;
	private final RoleReadPort roleReadPort;
	private final Identity identity;


	@Override
	public BasePage<RoleApplication> findAll(final RoleSelfApplicationListRequest listRequest) {

		Optional.ofNullable(listRequest.getFilter())
			.ifPresentOrElse(
				filter -> filter.addUserId(identity.getUserId()),
				() -> {
					RoleSelfApplicationFilter filter = RoleSelfApplicationFilter.builder().build();
					filter.addUserId(identity.getUserId());
					listRequest.setFilter(filter);
				}
			);

		return roleApplicationReadPort.findAll(listRequest.getPageable(), listRequest.getFilter());
	}


	@Override
	public void createAuthorApplication() {
		this.createApplication(RoleName.AUTHOR);
	}

	@Override
	public void createModeratorApplication() {
		this.createApplication(RoleName.MODERATOR);
	}

	private void createApplication(final RoleName roleName) {

		boolean existAnyApplicationInReview = roleApplicationReadPort
			.existsByUserIdAndStatus(identity.getUserId(), RoleApplicationStatus.IN_REVIEW);
		if (existAnyApplicationInReview) {
			throw new RoleApplicationAlreadyExistException();
		}

		final Role role = roleReadPort.findByName(roleName)
			.orElseThrow(() -> new RoleNotFoundByNameException(roleName.name()));

		final RoleApplication application = RoleApplication.builder()
			.user(User.builder().id(identity.getUserId()).build())
			.role(role)
			.status(RoleApplicationStatus.IN_REVIEW)
			.build();

		roleApplicationSavePort.save(application);
	}


	@Override
	public void cancel() {

		final RoleApplication application = roleApplicationReadPort
			.findByUserIdAndStatus(identity.getUserId(), RoleApplicationStatus.IN_REVIEW)
			.orElseThrow(() -> new RoleApplicationNotFoundByUserIdAndStatusException(
					identity.getUserId(),
					RoleApplicationStatus.IN_REVIEW
				)
			);

		application.cancel();
		roleApplicationSavePort.save(application);
	}

}

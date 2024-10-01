package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleChangeStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;

@Getter
@Setter
public class RoleApplication {

	private Long id;

	private User user;

	private RoleDomain role;

	private RoleChangeStatus status;

}

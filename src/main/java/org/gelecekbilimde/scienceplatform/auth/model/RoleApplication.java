package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;

@Getter
@Setter
public class RoleApplication {

	private String id;
	private User user;
	private Role role;
	private RoleApplicationStatus status;

}

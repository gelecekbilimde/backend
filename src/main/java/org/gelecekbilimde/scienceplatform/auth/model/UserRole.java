package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.user.model.User;

@Getter
@Setter
public class UserRole {

	private User user;

	private RoleEntity role;

}

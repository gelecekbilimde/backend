package org.gelecekbilimde.scienceplatform.auth.model.response;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;

@Getter
@Setter
public class RoleSelfApplicationsResponse {

	private Role role;
	private RoleApplicationStatus status;

	@Getter
	@Setter
	public static class Role {
		private String name;
	}

}

package org.gelecekbilimde.scienceplatform.auth.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;

@Getter
@Setter
@Builder
public class RoleApplicationsResponse {

	private String id;
	private User user;
	private Role role;
	private RoleApplicationStatus status;

	@Getter
	@Setter
	public static class User {
		private String id;
		private String firstName;
		private String lastName;
		private String email;
	}

	@Getter
	@Setter
	public static class Role {
		private String id;
		private String name;
	}

}

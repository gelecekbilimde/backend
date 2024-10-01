package org.gelecekbilimde.scienceplatform.auth.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleApplicationResponse {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String requestRoleName;
	private String status;

}

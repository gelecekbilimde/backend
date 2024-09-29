package org.gelecekbilimde.scienceplatform.auth.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleApplicationResponse {

	Long id;
	String firstName;
	String lastName;
	String email;
	String requestRoleName;
	String status;

}

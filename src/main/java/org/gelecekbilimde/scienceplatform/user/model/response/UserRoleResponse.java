package org.gelecekbilimde.scienceplatform.user.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {
	private String userId;
	private String userEmail;
	private String roleName;
}

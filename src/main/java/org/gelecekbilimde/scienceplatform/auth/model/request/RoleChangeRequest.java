package org.gelecekbilimde.scienceplatform.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleChangeRequest {

	@NotBlank
	private String userId;

	@NotNull
	private String roleName;

}

package org.gelecekbilimde.scienceplatform.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerifyRequest {

	@NotBlank
	private String verificationId;

}

package org.gelecekbilimde.scienceplatform.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {

	@NotBlank
	private String verificationId;

}

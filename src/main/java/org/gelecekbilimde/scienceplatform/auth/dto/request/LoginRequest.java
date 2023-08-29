package org.gelecekbilimde.scienceplatform.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	@Size(max = 255, message = "gender should not be less than 255")
	private String email;

	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 255, message = "Password should not be less than 8-255")
	private String password;

}

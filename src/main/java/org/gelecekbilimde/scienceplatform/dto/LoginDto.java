package org.gelecekbilimde.scienceplatform.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;

	@NotNull(message = "Password cannot be null")
	@Min(value = 8, message = "Password should not be less than 8")
	private String password;

}

package org.gelecekbilimde.scienceplatform.auth.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.util.validation.Username;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserDegree;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserGender;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotBlank
	@Size(min = 2, max = 25)
	private String firstname;

	@NotBlank
	@Size(min = 2, max = 25)
	private String lastname;

	@NotBlank
	@Username
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Email
	@Size(min = 8, max = 255)
	private String email;

	@NotBlank
	@Size(min = 8, max = 255)
	private String password;

	private String biography;

	private LocalDate birthDate;

	private UserGender gender;

	private UserDegree degree;

}

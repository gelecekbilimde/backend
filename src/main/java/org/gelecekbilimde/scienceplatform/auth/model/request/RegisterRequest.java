package org.gelecekbilimde.scienceplatform.auth.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotNull(message = "firstname cannot be null")
	@Size(min = 2 ,max = 25)
	private String firstname;

	@NotNull(message = "lastname cannot be null")
	@Size(min = 2, max = 25)
	private String lastname;

	@NotNull(message = "email cannot be null")
	@Email(message = "email should be valid")
	@Size(min = 8, max = 255)
	private String email;

	@NotNull(message = "password cannot be null")
	@Size(min = 8, max = 255)
	private String password;

	private String biography;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Size(max = 255)
	private String gender;

	@Size(max = 255)
	private String degree;

}

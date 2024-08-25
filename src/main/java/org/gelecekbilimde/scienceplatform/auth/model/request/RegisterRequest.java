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
	@Size(max = 25, min = 2 , message = "firstname should be more than 2 and less than 25")
	private String firstname;

	@NotNull(message = "lastname cannot be null")
	@Size(max = 25, min = 2, message = "lastname should be more than 2 and less than 25")
	private String lastname;

	@NotNull(message = "email cannot be null")
	@Email(message = "email should be valid")
	@Size(max = 255, message = "email should be less than 255")
	private String email;

	@NotNull(message = "password cannot be null")
	@Size(max = 255, min = 8, message = "password should be more than 8 and less than 255")
	private String password;
	private String biography;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Size(max = 255, message = "gender should be less than 255")
	private String gender;

	@Size(max = 255, message = "degree should be less than 255")
	private String degree;

}

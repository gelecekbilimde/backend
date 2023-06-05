package org.gelecekbilimde.scienceplatform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
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
public class RegisterDto {

	@NotNull(message = "firstname cannot be null")
	@Max(value = 25, message = "firstname should not be less than 25")
	private String firstname;
	@NotNull(message = "lastname cannot be null")
	@Max(value = 25, message = "lastname should not be less than 25")
	private String lastname;
	@NotNull(message = "email cannot be null")
	@Email(message = "email should be valid")
	@Max(value = 255, message = "email should not be less than 255")
	private String email;
	@NotNull(message = "password cannot be null")
	@Max(value = 255, message = "password should not be less than 255")
	private String password;
	@NotNull(message = "birth_date cannot be null")
	@JsonProperty("birth_date")
	private String birthDate;
	@NotNull(message = "biyografi cannot be null")
	private String biography;
	@NotNull(message = "gender cannot be null")
	@Max(value = 255, message = "gender should not be less than 255")
	private String gender;
	@Max(value = 255, message = "degree should not be less than 255")
	private String degree;

}

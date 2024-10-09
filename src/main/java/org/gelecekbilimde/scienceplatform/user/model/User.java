package org.gelecekbilimde.scienceplatform.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserDegree;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserGender;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomainModel {

	private String id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String avatarPath;
	private String biography;
	private LocalDate birthDate;
	private UserDegree degree;
	private UserGender gender;
	private UserStatus status;

	private Role role;


	public void verify() {
		this.status = UserStatus.VERIFIED;
	}

}

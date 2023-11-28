package org.gelecekbilimde.scienceplatform.user.dto.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.user.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.enums.Gender;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class UserDomain {
	private String id;
	private String avatarPath;
	private String biography;
	private LocalDate birthDate;
	private LocalDateTime createDate;
	private Degree degree;
	private String email;
	private Gender gender;
	private String name;
	private String lastName;
	private UserStatus status;


}

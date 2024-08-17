package org.gelecekbilimde.scienceplatform.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.user.model.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.model.enums.Gender;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class User {

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
	private List<User> followings;

}

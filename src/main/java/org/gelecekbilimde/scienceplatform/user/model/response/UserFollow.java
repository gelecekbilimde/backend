package org.gelecekbilimde.scienceplatform.user.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserFollow {
	private String id;
	private String avatarPath;
	private String biography;
	private String name;
	private String lastName;
}

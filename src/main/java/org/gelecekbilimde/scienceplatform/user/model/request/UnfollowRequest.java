package org.gelecekbilimde.scienceplatform.user.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class UnfollowRequest {

	@UUID
	@NotBlank
	private String followerId;

}

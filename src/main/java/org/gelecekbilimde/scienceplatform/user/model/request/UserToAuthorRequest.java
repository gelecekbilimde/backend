package org.gelecekbilimde.scienceplatform.user.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToAuthorRequest {
	@NotNull
	private String userId;
}

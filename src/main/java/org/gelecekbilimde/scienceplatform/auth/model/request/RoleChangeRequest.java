package org.gelecekbilimde.scienceplatform.auth.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleChangeRequest {

	@NotNull
	private String userId;

}

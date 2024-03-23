package org.gelecekbilimde.scienceplatform.auth.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

	private String accessToken;
	private String refreshToken;

}

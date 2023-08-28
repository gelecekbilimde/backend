package org.gelecekbilimde.scienceplatform.auth.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

	private String accessToken;
	private String refreshToken;

}

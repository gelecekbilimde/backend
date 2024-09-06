package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@Builder
public class Token {

	private String accessToken;
	private String refreshToken;


	private static final String TOKEN_PREFIX = "Bearer ";

	public static boolean isBearerToken(String authorizationHeader) {
		return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TOKEN_PREFIX);
	}

	public static String getJwt(String authorizationHeader) {
		return authorizationHeader.replace(TOKEN_PREFIX, "");
	}

}

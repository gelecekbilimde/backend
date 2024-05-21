package org.gelecekbilimde.scienceplatform.auth.model;

import org.gelecekbilimde.scienceplatform.auth.util.BeanScope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope(value = BeanScope.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Identity {

	public String getUserId() {
		return getJwt().getClaim("userId");
	}

	public boolean hasPermission(String requiredPermission) {
		ArrayList<String> scope = getJwt().getClaim("scope");
		return scope.contains(requiredPermission);
	}

	public String getAccessToken() {
		return this.getJwt().getTokenValue();
	}

	private Jwt getJwt() {
		return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}

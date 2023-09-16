package org.gelecekbilimde.scienceplatform.user.service;


import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BeanScope;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanScope.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class Identity {

	public Long getUserId() {
		return getUser().getId();
	}

	public boolean isEmailVerify() {
		return getUser().isEmailVerify();
	}

	public boolean isUserEnable() {
		return getUser().isUserEnable();
	}

	public boolean isUserLock() {
		return getUser().isUserLock();
	}

	private User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}

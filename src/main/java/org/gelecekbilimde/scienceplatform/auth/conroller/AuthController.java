package org.gelecekbilimde.scienceplatform.auth.conroller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.dto.Request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.Response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.dto.Request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service;

	@PostMapping("/register")
	public Response<TokenResponse> register(@RequestBody RegisterRequest request) {
		return Response.ok(service.register(request));
	}

	@PostMapping("/login")
	public Response<TokenResponse> login(@RequestBody LoginRequest request) {
		return Response.ok(service.login(request));
	}

	@PostMapping("/refresh-token")
	public Response<Object> refreshToken(HttpServletRequest request) {
		return Response.ok(service.refreshToken(request));
	}

	@PostMapping("/guest")
	public Response<TokenResponse> guest() {
		return Response.ok(service.generateGuestToken());
	}
}

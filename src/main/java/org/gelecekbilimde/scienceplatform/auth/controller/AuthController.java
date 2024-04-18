package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.dto.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.TokenRefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public Response<TokenResponse> register(@RequestBody @Valid RegisterRequest request) {
		return Response.ok(authenticationService.register(request));
	}

	@PostMapping("/verify")
	public Response<Void> verify(@RequestBody @Valid UserVerifyRequest userVerifyRequest) {
		authenticationService.verify(userVerifyRequest);
		return Response.NO_CONTENT;
	}

	@PostMapping("/login")
	public Response<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
		return Response.ok(authenticationService.login(request));
	}

	@PostMapping("/refresh-token")
	public Response<TokenResponse> refreshToken(@RequestBody @Valid TokenRefreshRequest refreshRequest) {
		return Response.ok(authenticationService.refreshToken(refreshRequest));
	}

	@PostMapping("/guest")
	public Response<TokenResponse> guest() {
		return Response.ok(authenticationService.generateGuestToken());
	}

}

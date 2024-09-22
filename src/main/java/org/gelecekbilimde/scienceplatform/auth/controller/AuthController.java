package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.request.*;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

	private final RegistrationService registrationService;
	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public Response<Void> register(@RequestBody @Valid RegisterRequest request) {
		registrationService.register(request);
		return Response.NO_CONTENT;
	}

	@PostMapping("/verify")
	public Response<Void> verify(@RequestBody @Valid VerifyRequest verifyRequest) {
		registrationService.verify(verifyRequest);
		return Response.NO_CONTENT;
	}


	@PostMapping("/login")
	public Response<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
		Token token = authenticationService.login(request);
		return Response.ok(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/refresh")
	public Response<TokenResponse> refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
		Token token = authenticationService.refresh(refreshRequest);
		return Response.ok(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/logout")
	public Response<Void> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
		authenticationService.logout(logoutRequest.getRefreshToken());
		return Response.NO_CONTENT;
	}

}

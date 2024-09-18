package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.LogoutRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.VerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.common.model.response.GenericResponse;
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
	GenericResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
		registrationService.register(request);
		return GenericResponse.success();
	}

	@PostMapping("/verify")
	GenericResponse<Void> verify(@RequestBody @Valid VerifyRequest verifyRequest) {
		registrationService.verify(verifyRequest);
		return GenericResponse.success();
	}


	@PostMapping("/login")
	GenericResponse<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
		Token token = authenticationService.login(request);
		return GenericResponse.success(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/refresh")
	GenericResponse<TokenResponse> refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
		Token token = authenticationService.refresh(refreshRequest);
		return GenericResponse.success(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/logout")
	GenericResponse<Void> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
		authenticationService.logout(logoutRequest.getRefreshToken());
		return GenericResponse.success();
	}

}

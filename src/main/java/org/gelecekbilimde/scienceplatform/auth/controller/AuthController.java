package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.LogoutRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.VerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class AuthController {

	private final RegistrationService registrationService;
	private final AuthenticationService authenticationService;
	private final RoleService roleService;

	@PostMapping("/register")
	SuccessResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
		registrationService.register(request);
		return SuccessResponse.success();
	}

	@PostMapping("/verify")
	SuccessResponse<Void> verify(@RequestBody @Valid VerifyRequest verifyRequest) {
		registrationService.verify(verifyRequest);
		return SuccessResponse.success();
	}


	@PostMapping("/login")
	SuccessResponse<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
		Token token = authenticationService.login(request);
		return SuccessResponse.success(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/refresh")
	SuccessResponse<TokenResponse> refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
		Token token = authenticationService.refresh(refreshRequest);
		return SuccessResponse.success(
			TokenResponse.builder()
				.accessToken(token.getAccessToken())
				.refreshToken(token.getRefreshToken())
				.build()
		);
	}

	@PostMapping("/logout")
	SuccessResponse<Void> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
		authenticationService.logout(logoutRequest.getRefreshToken());
		return SuccessResponse.success();
	}

	@PutMapping("/requestToBeAuthor")
	@PreAuthorize("hasRole('USER')")
	public SuccessResponse<UserRoleResponse> requestToBeAuthor() {
		return SuccessResponse.success(roleService.userRoletoAuthorRoleRequest());
	}

	@PutMapping("/requestToBeAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public SuccessResponse<UserRoleResponse> requestToBeAdmin(@RequestBody @Valid RoleChangeRequest request) {
		return SuccessResponse.success(roleService.makeUserToAdmin(request));
	}

	@PutMapping("/makeUserAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public SuccessResponse<UserRoleResponse> makeUserAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return SuccessResponse.success(roleService.makeUserToAuthor(request));
	}

	@GetMapping("/getRequestToBeAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public SuccessResponse<List<UserRoleResponse>> getAllRequestToBeAuthor() {
		return SuccessResponse.success(roleService.getAllUserRoletoAuthorRoleRequest());
	}

	@DeleteMapping("/deleteRequestToBeAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public SuccessResponse deleteRequestToBeAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return SuccessResponse.success(roleService.deleteUserRoletoAuthorRoleRequest(request));
	}

}

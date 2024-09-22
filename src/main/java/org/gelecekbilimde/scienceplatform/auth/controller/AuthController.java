package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.request.*;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
	public Response<UserRoleResponse> requestToBeAuthor() {
		return Response.ok(roleService.userRoletoAuthorRoleRequest());
	}

	@PutMapping("/requestToBeAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<UserRoleResponse> requestToBeAdmin(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.makeUserToAdmin(request));
	}

	@PutMapping("/makeUserAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<UserRoleResponse> makeUserAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.makeUserToAuthor(request));
	}

	@GetMapping("/getRequestToBeAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<List<UserRoleResponse>> getAllRequestToBeAuthor() {
		return Response.ok(roleService.getAllUserRoletoAuthorRoleRequest());
	}

	@DeleteMapping("/deleteRequestToBeAuthor")
	@PreAuthorize("hasRole('ADMIN')")
	public Response deleteRequestToBeAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.deleteUserRoletoAuthorRoleRequest(request));
	}

}

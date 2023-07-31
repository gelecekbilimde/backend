package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.LoginDto;
import org.gelecekbilimde.scienceplatform.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.dto.RegisterDto;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(HttpServletRequest httpServletRequest,
												@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,
												@RequestBody RegisterDto request) {
		return Response.ok(httpServletRequest, service.register(language, request));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(HttpServletRequest httpServletRequest,
											 @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,
											 @RequestBody LoginDto request) {
		return Response.ok(httpServletRequest, service.login(language, request));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<ApiResponse> refreshToken(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,
													HttpServletRequest request) {
		return Response.ok(request, service.refreshToken(language, request));
	}

	@PostMapping("/guest")
	public ResponseEntity<ApiResponse> guest(HttpServletRequest httpServletRequest) {
		return Response.ok(httpServletRequest, service.generateGuestToken());
	}
}

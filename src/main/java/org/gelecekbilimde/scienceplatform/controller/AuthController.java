package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.LoginDto;
import org.gelecekbilimde.scienceplatform.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.dto.RegisterDto;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ApiResponse> register(HttpServletRequest httpServletRequest, @RequestBody RegisterDto request) {
		return Response.ok(httpServletRequest, service.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(HttpServletRequest httpServletRequest, @RequestBody LoginDto request) {
		return Response.ok(httpServletRequest, service.login(request));
	}

	@PostMapping("/refresh-token")
	public  ResponseEntity<ApiResponse> refreshToken(HttpServletRequest request) {
		return Response.ok(request, service.refreshToken(request));
	}

	@PostMapping("/guest")
	public ResponseEntity<ApiResponse> guest(HttpServletRequest httpServletRequest) {
		return Response.ok(httpServletRequest,  service.generateGuestToken());
	}
}

package org.gelecekbilimde.scienceplatform.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
	public ResponseEntity<ApiResponse> register(HttpServletRequest httpServletRequest, @RequestBody RegisterRequest request) {
		return Response.ok(httpServletRequest, service.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<ApiResponse> authenticate(HttpServletRequest httpServletRequest, @RequestBody AuthenticationRequest request) {
		return Response.ok(httpServletRequest, service.authenticate(request));
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

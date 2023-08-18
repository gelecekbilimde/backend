package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.LoginDto;
import org.gelecekbilimde.scienceplatform.dto.TokenDto;
import org.gelecekbilimde.scienceplatform.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.dto.RegisterDto;
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
	public Response<TokenDto> register(@RequestBody RegisterDto request) {
		return Response.ok(service.register(request));
	}

	@PostMapping("/login")
	public Response<TokenDto> login(@RequestBody LoginDto request) {
		return Response.ok(service.login(request));
	}

	@PostMapping("/refresh-token")
	public Response<Object> refreshToken(HttpServletRequest request) {
		return Response.ok(service.refreshToken(request));
	}

	@PostMapping("/guest")
	public Response<TokenDto> guest() {
		return Response.ok(service.generateGuestToken());
	}
}

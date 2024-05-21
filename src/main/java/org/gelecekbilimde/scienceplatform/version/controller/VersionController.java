package org.gelecekbilimde.scienceplatform.version.controller;

import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class VersionController { // TODO : Bu sınıf yerine Spring Boot Actuator kullanılabilir.
	@RequestMapping("/version")
	public Response<String> version() {
		return Response.ok("API version: 0.0.1");
	}

	@RequestMapping("/secret/version")
	public Response<String> secretVersion() {
		return Response.ok("Secret API version: 0.0.1");
	}

	@RequestMapping("/secret/version/dev")
	@PreAuthorize("hasAuthority('dev:version')")
	public Response<String> secretVersionRole() {
		return Response.ok("Secret Dev API version: 0.0.1");
	}
}

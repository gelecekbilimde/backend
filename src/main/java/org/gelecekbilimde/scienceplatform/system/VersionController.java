package org.gelecekbilimde.scienceplatform.system;

import jakarta.servlet.http.HttpServletRequest;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class VersionController {
	@RequestMapping("/version")
	public  ResponseEntity<ApiResponse> version(HttpServletRequest request) {
		throw new ClientException("Versiyon Alınamadı");
		//return Response.ok(request,"API version: 0.0.1");
	}

	@RequestMapping("/secret/version")
	public  ResponseEntity<ApiResponse> secretVersion(HttpServletRequest request) {
		//throw new ClientException("Versiyon Alınamadı");
		return Response.ok(request,"Secret API version: 0.0.1");
	}

	@RequestMapping("/secret/version/dev")
	@PreAuthorize("hasAuthority('dev:version')")
	public  ResponseEntity<ApiResponse> secretVersionRole(HttpServletRequest request) {
		//throw new ClientException("Versiyon Alınamadı");
		return Response.ok(request,"Secret Dev API version: 0.0.1");
	}
}

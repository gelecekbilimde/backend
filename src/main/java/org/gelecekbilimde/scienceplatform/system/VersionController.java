package org.gelecekbilimde.scienceplatform.system;

import jakarta.servlet.http.HttpServletRequest;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class VersionController {
	@RequestMapping("/version")
	public  ResponseEntity<ApiResponse> version(HttpServletRequest request) {
		//throw new ClientException("Versiyon Alınamadı");
		return Response.ok(request,"API version: 0.0.1");
	}
}

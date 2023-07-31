package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.util.Constant;
import org.gelecekbilimde.scienceplatform.util.MessagesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class VersionController {
	private final MessagesUtil messagesUtil = new MessagesUtil();

	@RequestMapping("/version")
	public ResponseEntity<ApiResponse> version(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language, HttpServletRequest request) {
		return Response.ok(request, "API " + messagesUtil.getMessage("version", language) + ": " + Constant.VERSION);
	}

	@RequestMapping("/secret/version")
	public ResponseEntity<ApiResponse> secretVersion(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language, HttpServletRequest request) {
		return Response.ok(request, messagesUtil.getMessage("secret", language) + " API "
			+ messagesUtil.getMessage("version", language) + ": " + Constant.VERSION);
	}

	@RequestMapping("/secret/version/dev")
	@PreAuthorize("hasAuthority('dev:version')")
	public ResponseEntity<ApiResponse> secretVersionRole(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language, HttpServletRequest request) {
		return Response.ok(request, messagesUtil.getMessage("secret", language) + " API "
			+ messagesUtil.getMessage("dev", language) + messagesUtil.getMessage("version", language) + ": " + Constant.VERSION);
	}
}

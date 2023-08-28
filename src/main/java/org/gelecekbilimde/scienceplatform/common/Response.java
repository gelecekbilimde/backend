package org.gelecekbilimde.scienceplatform.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

	private Response() {
		// Private constructor to prevent instantiation
		throw new UnsupportedOperationException("This class cannot be instantiated");
	}

	public static ResponseEntity<ApiResponse> ok(HttpServletRequest request, Object response) {
		ApiResponse apiResponse = new ApiResponse(request,HttpStatus.OK,response);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	public static ResponseEntity<ApiResponse> noContent() {
		return ResponseEntity.noContent().build();
	}

	public static ResponseEntity<ApiResponse> created(HttpServletRequest request, Object response) {
		ApiResponse apiResponse = new ApiResponse(request,HttpStatus.CREATED,response);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	public static ResponseEntity<ApiResponse> multiStatus(HttpServletRequest request, Object response) {
		ApiResponse apiResponse = new ApiResponse(request,HttpStatus.MULTI_STATUS,response);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}

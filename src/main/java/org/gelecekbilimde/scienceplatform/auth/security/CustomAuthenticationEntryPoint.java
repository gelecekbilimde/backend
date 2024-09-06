package org.gelecekbilimde.scienceplatform.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.registerModule(new JavaTimeModule());
	}

	@Override
	public void commence(HttpServletRequest httpServletRequest,
						 HttpServletResponse httpServletResponse,
						 AuthenticationException authenticationException) throws IOException {

		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

		final ResponseEntity<Object> errorResponse = ResponseEntity
			.status(HttpStatus.UNAUTHORIZED)
			.build();
		final String responseBody = OBJECT_MAPPER
			.writer(DateFormat.getDateInstance())
			.writeValueAsString(errorResponse);
		httpServletResponse.getOutputStream().write(responseBody.getBytes());
	}

}

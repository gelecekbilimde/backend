package org.gelecekbilimde.scienceplatform.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Builder
public class Response<T> {

	private final HttpStatus statusText;
	private final Integer status;


	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T list;

	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();

	private Map<String, Object> request;

	public String responseCode;

	public static final Response<Void> NO_CONTENT = Response.<Void>builder()
		.statusText(HttpStatus.NO_CONTENT)
		.status(HttpStatus.NO_CONTENT.value())
		.build();

	public static <T> Response<T> ok(final T response, HttpServletRequest httpServletRequest) {
		return Response.<T>builder()
			.statusText(HttpStatus.OK)
			.status(HttpStatus.OK.value())
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.request(buildRequestParams(httpServletRequest))
			.build();
	}


	public static <T> Response<T> create(final T response, HttpServletRequest httpServletRequest) {
		return Response.<T>builder()
			.statusText(HttpStatus.CREATED)
			.status(HttpStatus.CREATED.value())
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.request(buildRequestParams(httpServletRequest))
			.build();
	}


	@JsonIgnore
	private static Map<String, Object> buildRequestParams(HttpServletRequest httpRequest) {
		Map<String, Object> requestArgs = new HashMap<>();
		requestArgs.put("params", getRequestParams(httpRequest));
		requestArgs.put("path", httpRequest.getRequestURL());
		requestArgs.put("args", getRequestArgs(httpRequest));

		return requestArgs;
	}

	@JsonIgnore
	private static Map<String, String> getRequestParams(HttpServletRequest httpRequest) {
		HashMap<String, String> params = new HashMap<>();

		String queryString = httpRequest.getQueryString();
		if (queryString == null) {
			return params;
		}

		String[] parameters = queryString.split("&");

		for (String parameter : parameters) {
			String[] keyValue = parameter.split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			params.put(key, value);
		}

		return params;
	}

	@JsonIgnore
	private static Object getRequestArgs(HttpServletRequest httpRequest) {
		return httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	}
}

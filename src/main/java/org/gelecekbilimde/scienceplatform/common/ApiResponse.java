package org.gelecekbilimde.scienceplatform.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
public class ApiResponse {
	private final HttpStatus statusText;
	private final Integer status;
	private final Object list;
	private final int count;
	private final String timestamp;
	private final Map<String, Object> request;
	private final String responseCode;

	public ApiResponse(HttpServletRequest request, HttpStatus status, Object response) {
		this.statusText = status;
		this.status = status.value();
		this.list = formatList(response);
		this.count = calculateCount(this.list);
		this.timestamp = getCurrentTimestamp();
		this.request = createRequest(request);
		this.responseCode = UUID.randomUUID().toString();
	}

	private Object formatList(Object response) {
		if (response instanceof String) {
			HashMap<String, Object> responseData = new HashMap<>();
			responseData.put("message", response);
			return responseData;
		}
		return response;
	}

	private int calculateCount(Object list) {
		if (list instanceof List) {
			return ((List<?>) list).size();
		} else if (list instanceof Map) {
			return ((Map<?, ?>) list).size();
		} else if (list instanceof Set) {
			return ((Set<?>) list).size();
		}
		return 0;
	}

	private String getCurrentTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ZonedDateTime.now(ZoneId.of("Z")).format(formatter);
	}

	private Map<String, Object> createRequest(HttpServletRequest httpRequest) {
		Map<String, Object> requestArgs = new HashMap<>();
		requestArgs.put("params", getRequestParams(httpRequest));
		requestArgs.put("path", httpRequest.getRequestURL());
		requestArgs.put("args", getRequestArgs(httpRequest));
		return requestArgs;
	}

	private Map<String, String> getRequestParams(HttpServletRequest httpRequest) {
		HashMap<String, String> params = new HashMap<>();

		String queryString = httpRequest.getQueryString();
		if (queryString == null){
			return params;
		}

		String[] parameters = queryString.split("&");

		for (String parameter : parameters) {
			String[] keyValue = parameter.split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			params.put(key, value);
		}

		return  params;
	}

	private Object getRequestArgs(HttpServletRequest httpRequest)
	{
		return httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	}

}

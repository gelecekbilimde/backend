package org.gelecekbilimde.scienceplatform.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ApiResponse {
	private final HttpStatus statusText;
	private final Integer status;

	private Object list;

	protected int count;

	public String timestamp;

	private Map<String, Object> request;
	public String responseCode;

	public ApiResponse(HttpServletRequest request, HttpStatus status, Object response) {

		this.statusText = status;
		this.status = status.value();
		setList(response);
		setTimestamp();
		setRequest(request);
		setResponseCode();
		setCount();
	}


	public void setList(@Nullable Object response) {
		if (response instanceof String) {
			HashMap<String, Object> responseData = new HashMap<>();
			responseData.put("message", response);
			response = responseData;
		}
		list = response;
	}

	public Object getList() {
		return list;
	}

	public void setCount() {
		if (list instanceof List) {
			count = ((List<?>) list).size();
		} else if (list instanceof Map) {
			count = ((Map<?, ?>) list).size();
		} else if (list instanceof Set) {
			count = ((Set<?>) list).size();
		} else {
			count = 0;
		}
	}

	public int getCount() {
		return count;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	private void setRequest(HttpServletRequest httpRequest) {
		Map<String, Object> requestArgs = new HashMap<>();
		requestArgs.put("params", getRequestParams(httpRequest));
		requestArgs.put("path", httpRequest.getRequestURL());
		requestArgs.put("args", getRequestArgs(httpRequest));

		request = requestArgs;
	}


	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode() {
		this.responseCode = UUID.randomUUID().toString();
	}

	public HttpStatus getStatusText() {
		return statusText;
	}

	public Integer getStatus() {
		return status;
	}

	public void setTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		timestamp = ZonedDateTime.now(ZoneId.of("Z")).format(formatter);
	}

	public String getTimestamp() {
		return timestamp;
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

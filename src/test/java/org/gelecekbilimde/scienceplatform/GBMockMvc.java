package org.gelecekbilimde.scienceplatform;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.ErrorResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.util.MockResultMatchersBuilders;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@Component
@RequiredArgsConstructor
public class GBMockMvc {

	// TODO: [alpemreelmas] [28.09.2024] we do not use prefix that's why putting here a prefix will be opposite for our code structure // NOSONAR
	private final org.springframework.test.web.servlet.MockMvc servletMockMvc;

	public ResultActions perform(final MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
								 final SuccessResponse<?> mockResponse) throws Exception {

		return servletMockMvc.perform(mockHttpServletRequestBuilder)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockResultMatchersBuilders.time()
				.isNotEmpty())
			.andExpect(MockResultMatchersBuilders.isSuccess()
				.isBoolean())
			.andExpect(MockResultMatchersBuilders.isSuccess()
				.value(mockResponse.getIsSuccess()));
	}

	public ResultActions perform(final MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
								 final ErrorResponse mockErrorResponse) throws Exception {

		return servletMockMvc.perform(mockHttpServletRequestBuilder)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockResultMatchersBuilders.time()
				.isNotEmpty())
			.andExpect(MockResultMatchersBuilders.isSuccess()
				.isBoolean())
			.andExpect(MockResultMatchersBuilders.header()
				.isString())
			.andExpect(MockResultMatchersBuilders.header()
				.value(mockErrorResponse.getHeader()))
			.andExpect(MockResultMatchersBuilders.response()
				.doesNotHaveJsonPath());
	}

}

package org.gelecekbilimde.scienceplatform.common.mail;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.common.mail.service.UserService;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {
	private final UserService userService;

	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public Response<String> confirmUserAccount(HttpServletRequest servletRequest, @RequestParam("token")String confirmationToken) {
		try {
			userService.confirmEmail(confirmationToken);
			return Response.ok("Succesfull");
		}catch (Exception e) {
			throw new ClientException("Bad Request");
		}
	}
}

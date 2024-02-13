package org.gelecekbilimde.scienceplatform.common.mail;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.common.mail.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirm-account")
@RequiredArgsConstructor
public class EmailController {
	private final UserService userService;

	@GetMapping()
	public Response<Void> confirmUserAccount(@RequestParam("token") String confirmationToken) {

		userService.confirmEmail(confirmationToken);
		return Response.NO_CONTENT;
	}
}

package org.gelecekbilimde.scienceplatform.service;

import org.springframework.http.ResponseEntity;

public interface UserService {
	ResponseEntity<?> confirmEmail(String confirmationToken);
}

package org.gelecekbilimde.scienceplatform.user.port;

import org.gelecekbilimde.scienceplatform.user.model.UserVerification;

import java.util.Optional;

public interface UserVerificationReadPort {

	Optional<UserVerification> findById(String id);

}

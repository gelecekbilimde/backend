package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.NotFoundException;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.ConfirmationTokenRepository;
import org.gelecekbilimde.scienceplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;


	private final ConfirmationTokenRepository confirmationTokenRepository;

	private final EmailService emailService;

	@Override
	public ResponseEntity<?> confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null){
			User user = userRepository.findByEmail(token.getUser().getEmail()).orElseThrow(()-> new UserNotFoundException("User not found!"));
			user.setEmailVerify(true); //arkadaşların yazdığı koddan aldım sor bunu
			userRepository.save(user);
			return ResponseEntity.ok("Email verified successfully!");

		}
		return ResponseEntity.badRequest().body("Error: Couldn't verify email");
	}
}

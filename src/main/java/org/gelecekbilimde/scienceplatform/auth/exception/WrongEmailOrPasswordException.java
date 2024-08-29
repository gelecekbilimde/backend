package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class WrongEmailOrPasswordException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -5855985402072628508L;

	public WrongEmailOrPasswordException() {
		super("Hatalı Eposta veya Şifre");
	}
}

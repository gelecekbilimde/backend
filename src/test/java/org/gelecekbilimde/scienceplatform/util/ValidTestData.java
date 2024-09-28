package org.gelecekbilimde.scienceplatform.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidTestData {

	public static final String EMAIL_ADDRESS = "test@gelecekbilimde.org";
	public static final String PASSWORD = "password.";
	public static final String PASSWORD_ENCRYPTED = "$2a$10$H/lKEaKsusQztOaJmYTAi.4MAmjvnxWOh0DY.XrgwHy5D2gENVIky";

	public static class Author {
		public static final String ID = "f882cb9c-9341-473b-a040-3fbd05c09ac6";
		public static final String EMAIL_ADDRESS = "james.william@gelecekbilimde.org";
	}

	public static class Admin {
		public static final String ID = "9ebcd692-fc0b-4f76-9948-3dd246d73758";
		public static final String EMAIL_ADDRESS = "kyle.joanne@gelecekbilimde.org";
	}

	public static class User {
		public static final String ID = "cf1587fd-f800-42f2-ac6e-bd7b1c4d993d";
		public static final String EMAIL_ADDRESS = "diego.ruiz@gelecekbilimde.org";
	}

	public static class Moderator {
		public static final String ID = "cf1587fd-f800-42f2-ac6e-bd7b1c4d993d";
		public static final String EMAIL_ADDRESS = "diego.ruiz@gelecekbilimde.org";
	}
}

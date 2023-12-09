package org.gelecekbilimde.scienceplatform.common;


import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Util {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
}

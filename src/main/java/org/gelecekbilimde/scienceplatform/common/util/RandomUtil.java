package org.gelecekbilimde.scienceplatform.common.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RandomUtil {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

}

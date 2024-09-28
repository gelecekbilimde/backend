package org.gelecekbilimde.scienceplatform.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@UtilityClass
public class RandomUtil {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String generateText(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

}

package org.gelecekbilimde.scienceplatform.common;

import lombok.experimental.UtilityClass;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;

import java.util.UUID;

@UtilityClass
public class Util {

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static MediaType getMediaType(String ext) {
		return switch (ext) {
			case "jpg", "jpeg", "svg", "img", "png" -> MediaType.IMAGE;
			case "gif" -> MediaType.GIF;
			default -> MediaType.IMAGE;
		};
	}

}

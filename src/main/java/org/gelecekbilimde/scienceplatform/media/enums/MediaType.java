package org.gelecekbilimde.scienceplatform.media.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum MediaType {

	IMAGE(Set.of("jpg", "jpeg", "svg", "img", "png")),
	GIF(Set.of("gif")),
	VIDEO(Set.of());

	private final Set<String> extensions;

	public static MediaType valueOfExtension(String extension) {
		return Arrays.stream(MediaType.values())
			.filter(mediaType -> mediaType.getExtensions().contains(extension))
			.findFirst()
			.orElse(MediaType.IMAGE);
	}

}

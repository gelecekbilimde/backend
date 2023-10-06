package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Thumbnails {
	List<Thumbnail> defaultThumbnail;
	List<Thumbnail> mediumThumbnail;
	List<Thumbnail> highThumbnail;
	List<Thumbnail> standardThumbnail;
	List<Thumbnail> maxresThumbnail;
}

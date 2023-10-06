package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
	String kind;
	String etag;
	String id;
	Snippet snippet;
}

package org.gelecekbilimde.scienceplatform.post.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PostMedia extends BaseDomainModel {

	private Long id;
	private Long mediaId;
	private boolean cover;

	private String url;
	private MediaContentType contentType;
	private String title;
	private boolean shared;

	private Long groupId;
	private String groupName;

}

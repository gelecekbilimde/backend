package org.gelecekbilimde.scienceplatform.post.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseDomainModel {

	private String id;
	private String header;
	private String slug;
	private String content;
	private CategoryEntity categoryEntity;
	private Process lastProcess;
	private List<String> label;
	private Integer likeCount;
	private boolean active;
	private boolean copyrightControl;
	private boolean typoControl;
	private boolean dangerousControl;

	private String userId;

	private User user;
	private List<PostMedia> medias;

}

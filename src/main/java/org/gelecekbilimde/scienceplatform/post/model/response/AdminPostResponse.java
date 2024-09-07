package org.gelecekbilimde.scienceplatform.post.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.user.model.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserGender;

import java.util.List;


@Getter
@Setter
@Builder
public class AdminPostResponse {


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
	private User user;
	private List<Media> medias;

	@Getter
	@Setter
	public static class User {
		private String id;
		private String name;
		private String lastName;
		private String avatar;
		private Degree degree;
		private UserGender gender;
	}

	@Builder
	@Getter
	@Setter
	public static class Media {
		private Long id;
		private boolean cover;

		private String url;
		private MediaContentType contentType;
		private String title;
		private boolean shared;

		private Long groupId;
		private String groupName;
	}
}

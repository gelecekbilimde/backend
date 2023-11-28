package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media")
public class Media extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url")
	private String url;


	@Enumerated(EnumType.STRING)
	@Column(name = "content_type")
	private MediaContentType contentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type")
	private MediaType mediaType;

	@Column(name = "title")
	private String title;

	@Column(name = "is_shared")
	private boolean shared;

	@Column(name = "user_id")
	private String userId;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private MediaGroup mediaGroup;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;

	@OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
	private List<PostMedia> postMedia;

	@Override
	public String toString() {
		return "Media{" +
			"id=" + getId() +
			", url='" + getUrl() + '\'' +
			", contentType='" + getContentType() + '\'' +
			", mediaType='" + getMediaType() + '\'' +
			", title='" + getTitle() + '\'' +
			", shared='" + isShared() + '\'' +
			", userId='" + getUser() + '\'' +
			'}';
	}
}

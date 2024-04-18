package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.enums.MediaStatus;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.util.List;

@Entity
@Getter
@Setter
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
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
	private List<PostMedia> postMedia;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private MediaStatus status;

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

package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.post.enums.PostMediaStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_media")
public class PostMedia extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "post_id")
	private String postId;

	@Column(name = "media_id")
	private Long mediaId;

	@Column(name = "user_id")
	private String userId;

	@ManyToOne
	@JoinColumn(name = "post_id",insertable = false, updatable = false)
	private Post post;

	@ManyToOne
	@JoinColumn(name = "media_id",insertable = false, updatable = false)
	private Media media;

	@Column
	private boolean cover;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PostMediaStatus status;


	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;
	@Override
	public String toString() {
		return "PostMedia{" +
			"id=" + getId() +
			", postId='" + getPostId() + '\'' +
			", mediaId='" + getMediaId() + '\'' +
			", cover='" + isCover() + '\'' +
			'}';
	}
}

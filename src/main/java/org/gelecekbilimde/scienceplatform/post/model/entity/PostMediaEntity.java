package org.gelecekbilimde.scienceplatform.post.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.PostMediaStatus;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_media")
public class PostMediaEntity extends BaseEntity {
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
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private PostEntity postEntity;

	@ManyToOne
	@JoinColumn(name = "media_id", insertable = false, updatable = false)
	private MediaEntity mediaEntity;

	@Column
	private boolean cover;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PostMediaStatus status;


	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;

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

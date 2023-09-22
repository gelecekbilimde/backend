package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_media")
public class PostMedia extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "post_id")
	private Long postId;

	@Column(name = "media_id")
	private Long mediaId;

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "post_id",insertable = false, updatable = false)
	private Post post;

	@ManyToOne
	@JoinColumn(name = "media_id",insertable = false, updatable = false)
	private Media media;

	@Column
	private boolean cover;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;
}

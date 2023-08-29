package org.gelecekbilimde.scienceplatform.postmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_media")
public class PostMedia {
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
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "media_id")
	private Media media;

	@Column
	private Boolean cover;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private Date createdDate;
}

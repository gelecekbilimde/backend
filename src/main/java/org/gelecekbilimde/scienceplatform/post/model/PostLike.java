package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "post_like")
public class PostLike{
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "post_id")
	private String postId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "created_at")
	protected LocalDateTime createdAt;
}

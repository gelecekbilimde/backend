package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_process")
public class PostProcess {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String header;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String slug;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25)")
	private PostProcessEnum process;

	@Column(name = "post_id")
	private Long postId;

	@Column(name = "user_id")
	private Long userId;


	@ManyToOne
	@JoinColumn(name = "post_id",insertable = false, updatable = false)
	private Post post;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;

	@Column(columnDefinition = "jsonb")
	private String message;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;

}

package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_process")
public class PostProcess extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)")
	private String header;

	@Column(columnDefinition = "text")
	private String content;

	@Column(columnDefinition = "varchar(255)")
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

}

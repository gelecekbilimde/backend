package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.user.model.User;

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

	@Column(name = "header")
	private String header;

	@Column(name = "content")
	private String content;

	@Column(name = "slug")
	private String slug;

	@Enumerated(EnumType.STRING)
	@Column(name = "process")
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

	@Column(name = "message")
	private String message;

}

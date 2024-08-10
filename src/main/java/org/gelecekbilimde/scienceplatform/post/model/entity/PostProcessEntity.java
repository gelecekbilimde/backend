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
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_process")
public class PostProcessEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "header")
	private String header;

	@Column(name = "content")
	private String content;

	@Column(name = "slug")
	private String slug;

	@ManyToOne
	@JoinColumn(name = "category")
	private CategoryEntity categoryEntity;

	@Enumerated(EnumType.STRING)
	@Column(name = "process")
	private Process process;

	@Column(name = "post_id")
	private String postId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "done")
	private boolean done;

	@Column(name = "copyright_control")
	private boolean copyrightControl;

	@Column(name = "typo_control")
	private boolean typoControl;

	@Column(name = "dangerous_control")
	private boolean dangerousControl;

	@ManyToOne
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private PostEntity postEntity;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;

	@Column(name = "message")
	private String message;

}

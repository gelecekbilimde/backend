package org.gelecekbilimde.scienceplatform.post.model.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.PostStatus;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gb_post")
public class PostEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "header")
	private String header;

	@Column(name = "content")
	private String content;

	@Column(name = "slug")
	private String slug;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;


	@Column(name = "like_count")
	private Integer likeCount;


	@Column(name = "copyright_control")
	private boolean copyrightControl;

	@Column(name = "typo_control")
	private boolean typoControl;

	@Column(name = "dangerous_control")
	private boolean dangerousControl;

	@Enumerated(EnumType.STRING)
	@Column(name = "last_process")
	private Process lastProcess;


	@Column(name = "user_id")
	private String userId;


	@Column(name = "is_active")
	private boolean active;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PostStatus status;

	@OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
	private List<PostMediaEntity> medias;


	@OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
	private List<PostProcessEntity> processes;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserEntity userEntity;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<CommentEntity> commentEntities;


	public void like() {
		this.likeCount++;
	}

	public void unlike() {
		this.likeCount--;
	}

}

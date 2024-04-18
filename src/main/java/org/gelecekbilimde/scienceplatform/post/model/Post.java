package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.enums.PostStatus;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "header")
	private String header;

	@Column(name = "content")
	private String content;

	@Column(name = "slug")
	private String slug;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;


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

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostMedia> medias;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<org.gelecekbilimde.scienceplatform.post.model.PostProcess> processes;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;


	@ManyToMany
	@JoinTable(name = "post_comments", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private Set<Comment> comments = new HashSet<>();


	@Column(name = "created_at")
	protected LocalDateTime createdAt;


	public void like() {
		this.likeCount++;
	}

	public void unlike() {
		this.likeCount--;
	}

}

package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.post.enums.Process;

import java.time.LocalDateTime;
import java.util.*;

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

	@Override
	public String toString() {
		return "Post{" +
			"id=" + getId() +
			", header='" + getSlug() + '\'' +
			'}';
	}
}

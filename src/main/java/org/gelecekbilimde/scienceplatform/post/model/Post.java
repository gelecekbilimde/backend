package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;
import org.hibernate.annotations.CreationTimestamp;

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
	@GeneratedValue
	private Long id;

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
	private PostProcessEnum lastProcess;


	@Column(name = "user_id")
	private Long userId;


	@Column(name = "is_active")
	private boolean active;



	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private LocalDateTime createAt;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostMedia> postMedia;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostProcess> process;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

}

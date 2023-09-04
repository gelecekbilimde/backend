package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.comment.model.Comment;
import org.gelecekbilimde.scienceplatform.postmedia.model.PostMedia;
import org.gelecekbilimde.scienceplatform.postprocess.model.PostProcess;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.postprocess.enums.PostProcessEnum;
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

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String header;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String slug;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(columnDefinition = "boolean default false")
	private Boolean active;

	@Column(columnDefinition = "boolean default false")
	private Boolean copyrightControl;

	@Column(columnDefinition = "boolean default false")
	private Boolean typoControl;

	@Column(columnDefinition = "boolean default false")
	private Boolean dangerousControl;

	@Column(columnDefinition = "integer", nullable = false)
	private Integer likeCount;


	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25)")
	private PostProcessEnum lastProcess;


	@Column(name = "user_id")
	private Long userId;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostMedia> postMedia;


	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostProcess> process;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false,updatable = false)
	private User user;


	@ManyToMany
	@JoinTable(name = "post_comment", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private Set<Comment> comments = new HashSet<>();


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;


}

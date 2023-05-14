package org.gelecekbilimde.scienceplatform.post;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.comment.Comment;
import org.gelecekbilimde.scienceplatform.user.User;

import java.util.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String header;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(columnDefinition = "boolean default false")
	private Boolean active;

	private Integer likeCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;


	@ManyToMany(mappedBy = "posts")
	private Set<User> users = new HashSet<>();

	@ManyToMany
	@JoinTable(
		name = "post_comment",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "comment_id")
	)
	private Set<Comment> comments = new HashSet<>();

}

package org.gelecekbilimde.scienceplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String header;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(columnDefinition = "boolean default false")
	private Boolean active;

	@Column(columnDefinition = "integer", nullable = false)
	private Integer likeCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;


	@ManyToMany(mappedBy = "posts")
	private Set<User> users = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "post_comment", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private Set<Comment> comments = new HashSet<>();

}

package org.gelecekbilimde.scienceplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "text", nullable = false)
	private String comment;

	@Column(columnDefinition = "integer", nullable = false)
	private Integer likeCount;

	@ManyToMany(mappedBy = "comments")
	private Set<Post> posts = new HashSet<>();

}

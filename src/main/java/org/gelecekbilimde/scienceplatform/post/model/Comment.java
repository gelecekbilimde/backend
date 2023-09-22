package org.gelecekbilimde.scienceplatform.post.model;

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
	private String content;

	@Column(columnDefinition = "integer", nullable = false)
	private Integer likeCount;

	// todo : oneToMany olmalı
	@ManyToMany(mappedBy = "comments")
	private Set<Post> posts = new HashSet<>();

}

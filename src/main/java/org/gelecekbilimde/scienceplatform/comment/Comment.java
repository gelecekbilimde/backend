package org.gelecekbilimde.scienceplatform.comment;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.post.Post;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	@Lob
	@Column(nullable = false)
	private String comment;

	private Integer likeCount;

	@ManyToMany(mappedBy = "comments")
	private Set<Post> posts = new HashSet<>();


}

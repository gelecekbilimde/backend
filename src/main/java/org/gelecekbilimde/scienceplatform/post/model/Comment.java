package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.post.model.Post;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "comment")
	private String comment;

	@Column(name = "like_count")
	private Integer likeCount;


}

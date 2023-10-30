package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;


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

	@Column(name = "message")
	private String message;


	@Column(name = "like_count")
	private Integer likeCount;


}

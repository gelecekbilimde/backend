package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent", referencedColumnName = "id", insertable = false, updatable = false)
	private Category parent;

	@Column(name = "parent_id")
	private Long parentId;

	@OneToMany(mappedBy = "parent")
	private List<Category> children;

	@OneToMany(mappedBy = "category")
	private List<Post> posts;
}

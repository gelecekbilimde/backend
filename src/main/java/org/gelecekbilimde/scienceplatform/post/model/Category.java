package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "order_number")
	private Integer order;

	@Column(name = "slug")
	private String slug;

	@Column(name = "icon")
	private String icon;

	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

//	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//	private List<Category> children;
}

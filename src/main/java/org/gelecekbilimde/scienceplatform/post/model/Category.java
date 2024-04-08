package org.gelecekbilimde.scienceplatform.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category extends BaseModel {

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

	@Column(name = "parent_id")
	private Long parentId;

	@OneToOne
	@JoinColumn(name = "parent_id", updatable = false, insertable = false)
	private Category parent;
}

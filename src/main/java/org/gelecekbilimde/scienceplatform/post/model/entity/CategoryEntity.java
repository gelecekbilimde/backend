package org.gelecekbilimde.scienceplatform.post.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

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
	private CategoryEntity parent;

	public void increaseOrder() {
		this.order++;
	}
}

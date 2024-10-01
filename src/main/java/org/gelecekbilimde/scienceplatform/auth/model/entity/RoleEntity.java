package org.gelecekbilimde.scienceplatform.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleStatus;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gb_role")
public class RoleEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_default")
	private boolean isDefault;

	@Column(name = "is_hidden")
	private boolean isHidden;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RoleStatus status;

	@ManyToMany
	@JoinTable(
		name = "gb_role_permission",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<PermissionEntity> permissions;


	public List<String> getPermissionNames() {
		return permissions.stream()
			.map(PermissionEntity::getName)
			.toList();
	}

}

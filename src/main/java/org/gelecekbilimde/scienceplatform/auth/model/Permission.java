package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.enums.PermissionStatus;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class Permission extends BaseModel {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_hidden")
	private boolean isHidden;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PermissionStatus status;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

}

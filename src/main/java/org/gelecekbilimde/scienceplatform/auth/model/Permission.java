package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.enums.PermissionStatus;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

import java.util.HashSet;
import java.util.Set;

@Data
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

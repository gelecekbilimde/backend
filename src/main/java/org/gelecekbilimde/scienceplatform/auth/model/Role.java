package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.auth.enums.RoleStatus;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseModel {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_default")
	private boolean isDefault = false;

	@Column(name = "is_hidden")
	private boolean isHidden = false;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RoleStatus status;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@Fetch(FetchMode.SELECT)
	private Set<Permission> permissions = new HashSet<>();

	public List<SimpleGrantedAuthority> getPermissions() {
		var authorities = permissions
			.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()))
			.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + getName()));
		return authorities;
	}

}

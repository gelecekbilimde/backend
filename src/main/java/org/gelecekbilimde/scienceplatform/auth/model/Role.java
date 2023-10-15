package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_default")
	private boolean isDefault = false;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@Fetch(FetchMode.SELECT)
	private Set<Permission> permissions = new HashSet<>();

	public List<SimpleGrantedAuthority> getPermissions(){
		var authorities = permissions
			.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()))
			.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + getName()));
		return  authorities;
	}

}

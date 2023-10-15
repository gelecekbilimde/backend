package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class Permission extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
}

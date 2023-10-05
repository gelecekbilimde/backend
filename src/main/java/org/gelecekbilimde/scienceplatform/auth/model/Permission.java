package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	private String permission;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String description;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();


}

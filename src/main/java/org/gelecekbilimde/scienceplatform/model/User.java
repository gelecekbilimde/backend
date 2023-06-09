package org.gelecekbilimde.scienceplatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.model.enums.Degree;
import org.gelecekbilimde.scienceplatform.model.enums.Gender;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(25)", nullable = false)
	private String name;

	@Column(columnDefinition = "varchar(25)", nullable = false)
	private String lastname;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String email;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String password;

	@Column(columnDefinition = "varchar(255)")
	private String avatarPath;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(255)")
	private Gender gender;

	@Enumerated(EnumType.ORDINAL)
	@Column(columnDefinition = "integer")
	private Degree degree;

	@Column(columnDefinition = "text")
	private String biography;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private Date birthDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private Date createDate;

	@Column(columnDefinition = "boolean default false")
	public boolean emailVerify = false;

	@Column(columnDefinition = "boolean default true")
	public boolean userEnable = true;

	@Column(columnDefinition = "boolean default false")
	public boolean userLock = false;

	@OneToMany(mappedBy = "user")
	private List<Token> tokens;

	@OneToOne
	@JoinColumn(referencedColumnName = "role")
	private Role role;

	@ManyToMany
	@JoinTable(name = "follower", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
	private Set<User> followerUsers = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_post", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private Set<Post> posts = new HashSet<>();

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getPermissions();
	}

	@Override
	public boolean isEnabled() {
		return userEnable;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !userLock;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}

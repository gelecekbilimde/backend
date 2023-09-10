package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.user.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.enums.Gender;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
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

	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "date")
	private LocalDate birthDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createDate;

	@Column(columnDefinition = "boolean default false")
	public boolean emailVerify = false;

	@Column(columnDefinition = "boolean default true")
	public boolean userEnable = true;

	@Column(columnDefinition = "boolean default false")
	public boolean userLock = false;

	@OneToMany(mappedBy = "user")
	private List<Token> token;

	@OneToOne
	@JoinColumn(referencedColumnName = "role")
	private Role role;

	@ManyToMany
	@JoinTable(name = "follower", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
	private Set<User> followerUsers = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> post;

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

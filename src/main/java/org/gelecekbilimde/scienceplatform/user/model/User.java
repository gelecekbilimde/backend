package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
public class User extends BaseModel implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;


	@Column(name = "avatar_path")
	private String avatar;

	@Column(name = "biography")
	private String biography;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "degree")
	@Enumerated(EnumType.STRING)
	private Degree degree;

	@Column(name = "email")
	private String email;

	@Column(name = "email_verify")
	private boolean emailVerify;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastName;


	@Column(name = "password")
	private String password;

	@Column(name = "user_enable")
	private boolean userEnable = true;

	@Column(name = "user_lock")
	private boolean userLock = true;

	@Column(name = "role_name")
	private String roleName;


	@OneToMany(mappedBy = "user")
	private List<Token> token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_name", referencedColumnName = "name", insertable = false, updatable = false)
	private Role role;

	@ManyToMany
	@JoinTable(name = "follower", joinColumns = @JoinColumn(name = "follower_user_id"), inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
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

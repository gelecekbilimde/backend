package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.user.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.enums.Gender;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
public class User extends BaseModel {

	@Id
	@Column(name = "id")
	private String id;

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

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastName;


	@Column(name = "password")
	private String password;


	@Column(name = "role_id")
	private String roleId;


	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private UserStatus status;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Role role;

	@ManyToMany
	@JoinTable(name = "user_followers", joinColumns = @JoinColumn(name = "follower_user_id"), inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
	private Set<User> followerUsers = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> post;

	public String getUsername() {
		return email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getPermissions();
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + getId() +
			", username='" + getUsername() + '\'' +
			// Diğer alanlar
			'}';
	}
}

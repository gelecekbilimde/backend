package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.user.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.enums.Gender;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public void verify() {
		this.status = UserStatus.VERIFIED;
	}


	public boolean isVerified() {
		return this.status == UserStatus.VERIFIED;
	}


	public String getUsername() {
		return email;
	}

}

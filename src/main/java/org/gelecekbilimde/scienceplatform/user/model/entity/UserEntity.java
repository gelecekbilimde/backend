package org.gelecekbilimde.scienceplatform.user.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserDegree;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserGender;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gb_user")
public class UserEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "avatar_path")
	private String avatar;

	@Column(name = "biography")
	private String biography;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "degree")
	@Enumerated(EnumType.STRING)
	private UserDegree degree;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private UserGender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private UserStatus status;

	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@ManyToMany
	@JoinTable(
		name = "gb_user_follow",
		joinColumns = @JoinColumn(name = "follower_user_id"),
		inverseJoinColumns = @JoinColumn(name = "followed_user_id")
	)
	private List<UserEntity> followings = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "gb_user_follow",
		joinColumns = @JoinColumn(name = "followed_user_id"),
		inverseJoinColumns = @JoinColumn(name = "follower_user_id")
	)
	private List<UserEntity> followers = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<PostEntity> postEntity;


	public boolean isVerified() {
		return this.status == UserStatus.VERIFIED;
	}

}

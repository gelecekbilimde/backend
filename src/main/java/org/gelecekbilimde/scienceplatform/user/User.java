package org.gelecekbilimde.scienceplatform.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.Post;
import org.gelecekbilimde.scienceplatform.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name",length = 25, nullable = false)
	private String name;

	@Column(name = "lastname",length = 25, nullable = false)
	private String lastname;

	private String email;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth_date", nullable = true)
	private Date birthDate;

	@Enumerated(EnumType.STRING)
	private Gender gender;


	@OneToMany(mappedBy = "user")
	private List<Token> tokens;

	@ManyToMany
	@JoinTable(
		name = "follower",
		joinColumns = @JoinColumn(name = "follower_id"),
		inverseJoinColumns = @JoinColumn(name = "followed_id"))
	private Set<User> followedUsers = new HashSet<>();


	private String avatarPath;

	@Lob
	private String bio;

	@Column(columnDefinition = "INTEGER")
	private Degree degree;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
		name = "user_post",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private Set<Post> posts = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}

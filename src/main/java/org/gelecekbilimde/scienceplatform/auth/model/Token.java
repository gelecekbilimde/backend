package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.enums.TokenType;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.user.model.User;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token extends BaseModel {

	@Id
	@GeneratedValue
	public Long id;

	@Column(name = "jwt")
	public String jwt;

	@Enumerated(EnumType.STRING)
	@Column(name = "token_type")
	public TokenType tokenType = TokenType.BEARER;

	@Column(name = "is_revoked")
	public boolean revoked;

	@Column(name = "is_expired")
	public boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User user;

}

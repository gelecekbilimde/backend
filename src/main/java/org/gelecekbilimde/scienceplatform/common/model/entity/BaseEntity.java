package org.gelecekbilimde.scienceplatform.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

	@Column(name = "created_by")
	protected String createdUser;

	@Column(name = "created_at")
	protected LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		this.createdUser = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
			.map(Authentication::getPrincipal)
			.filter(user -> !"anonymousUser".equals(user))
			.map(Jwt.class::cast)
			.map(jwt -> jwt.getClaim(TokenClaims.USER_ID.getValue()).toString())
			.orElse("GUEST");
		this.createdAt = LocalDateTime.now();
	}

	@Column(name = "updated_by")
	protected String updatedUser;

	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;

	@PreUpdate
	public void preUpdate() {
		this.updatedUser = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
			.map(Authentication::getPrincipal)
			.filter(user -> !"anonymousUser".equals(user))
			.map(Jwt.class::cast)
			.map(jwt -> jwt.getClaim(TokenClaims.USER_ID.getValue()).toString())
			.orElse("GUEST");
		this.updatedAt = LocalDateTime.now();
	}

}

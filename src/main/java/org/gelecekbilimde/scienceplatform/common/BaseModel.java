package org.gelecekbilimde.scienceplatform.common;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {

	@Column(name = "create_user_id")
	protected Long createdUser;

	@Column(name = "created_at")
	protected LocalDateTime createdAt;
}

package org.gelecekbilimde.scienceplatform.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseModel {

	@Column(name = "create_user_id")
	protected String createdUser;

	@Column(name = "created_at")
	protected LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {}

	@Column(name = "update_user_id")
	protected String updatedUser;

	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;
	@PreUpdate
	public void preUpdate() {}

}

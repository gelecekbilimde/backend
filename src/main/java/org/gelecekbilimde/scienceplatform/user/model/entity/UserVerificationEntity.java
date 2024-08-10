package org.gelecekbilimde.scienceplatform.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserVerificationStatus;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "user_verification")
public class UserVerificationEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@Builder.Default
	private String id = RandomUtil.generateUUID();

	@Column(name = "user_id")
	private String userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private UserVerificationStatus status;

	public void complete() {
		this.status = UserVerificationStatus.COMPLETED;
	}

	public boolean isCompleted() {
		return this.status == UserVerificationStatus.COMPLETED;
	}

}

package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.user.enums.UserVerificationStatus;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "user_verification")
public class UserVerification extends BaseModel {

	@Id
	@Column(name = "id")
	@Builder.Default
	private String id = Util.generateUUID();

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

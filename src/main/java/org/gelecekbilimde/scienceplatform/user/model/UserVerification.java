package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.common.Util;

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

}

package org.gelecekbilimde.scienceplatform.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleChangeStatus;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gb_role_application")
public class RoleApplicationEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RoleChangeStatus status;


	public void approve() {
		this.status = RoleChangeStatus.APPROVED;
	}

	public void reject() {
		this.status = RoleChangeStatus.REJECTED;
	}

}

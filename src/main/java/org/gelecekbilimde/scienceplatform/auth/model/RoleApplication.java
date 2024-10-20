package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;

@Getter
@Setter
@Builder
public class RoleApplication {

	private String id;
	private User user;
	private Role role;
	private RoleApplicationStatus status;


	public boolean isConcluded() {
		return this.status == RoleApplicationStatus.APPROVED || this.status == RoleApplicationStatus.REJECTED;
	}


	public void cancel() {
		this.status = RoleApplicationStatus.CANCELLED;
	}

	public void approve() {
		this.status = RoleApplicationStatus.APPROVED;
	}

	public void reject() {
		this.status = RoleApplicationStatus.REJECTED;
	}

}

package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.PermissionEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleStatus;

import java.util.List;

@Getter
@Setter
public class Role {

	private String id;
	private String name;
	private String description;
	private boolean isHidden;
	private RoleStatus status;
	private List<PermissionEntity> permissions;

}

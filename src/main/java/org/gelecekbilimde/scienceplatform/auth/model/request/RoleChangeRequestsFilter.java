package org.gelecekbilimde.scienceplatform.auth.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleChangeRequestsFilter {

	private String columnName;

	private Object columnValue;

}

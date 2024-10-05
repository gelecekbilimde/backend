package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationsResponse;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleApplicationToRoleApplicationResponseMapper extends BaseMapper<RoleApplication, RoleApplicationsResponse> {

	static RoleApplicationToRoleApplicationResponseMapper initialize() {
		return Mappers.getMapper(RoleApplicationToRoleApplicationResponseMapper.class);
	}

}

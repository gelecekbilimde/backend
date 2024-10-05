package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleSelfApplicationsResponse;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleApplicationToRoleSelfApplicationsResponseMapper extends BaseMapper<RoleApplication, RoleSelfApplicationsResponse> {

	static RoleApplicationToRoleSelfApplicationsResponseMapper initialize() {
		return Mappers.getMapper(RoleApplicationToRoleSelfApplicationsResponseMapper.class);
	}

}

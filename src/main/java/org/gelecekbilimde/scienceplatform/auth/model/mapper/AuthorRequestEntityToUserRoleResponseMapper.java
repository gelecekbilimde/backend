package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorRequestEntityToUserRoleResponseMapper extends BaseMapper<RoleApplicationEntity, RoleApplication> {

	static AuthorRequestEntityToUserRoleResponseMapper initialize() {
		return Mappers.getMapper(AuthorRequestEntityToUserRoleResponseMapper.class);
	}

}

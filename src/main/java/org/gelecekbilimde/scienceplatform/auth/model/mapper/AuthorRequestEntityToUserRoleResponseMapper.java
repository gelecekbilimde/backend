package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.auth.model.UserRole;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorRequestEntityToUserRoleResponseMapper extends BaseMapper<AuthorRequestEntity, UserRole> {

	static AuthorRequestEntityToUserRoleResponseMapper initialize() {
		return Mappers.getMapper(AuthorRequestEntityToUserRoleResponseMapper.class);
	}

}

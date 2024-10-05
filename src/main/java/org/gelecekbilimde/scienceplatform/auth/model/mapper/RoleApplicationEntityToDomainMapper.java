package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleApplicationEntityToDomainMapper extends BaseMapper<RoleApplicationEntity, RoleApplication> {

	static RoleApplicationEntityToDomainMapper initialize() {
		return Mappers.getMapper(RoleApplicationEntityToDomainMapper.class);
	}

}

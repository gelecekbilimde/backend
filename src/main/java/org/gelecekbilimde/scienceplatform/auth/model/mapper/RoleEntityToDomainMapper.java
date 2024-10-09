package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleEntityToDomainMapper extends BaseMapper<RoleEntity, Role> {

    static RoleEntityToDomainMapper initialize() {
        return Mappers.getMapper(RoleEntityToDomainMapper.class);
    }

}

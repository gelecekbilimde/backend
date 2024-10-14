package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToEntityMapper extends BaseMapper<User, UserEntity> {

	static UserToEntityMapper initialize() {
		return Mappers.getMapper(UserToEntityMapper.class);
	}

}

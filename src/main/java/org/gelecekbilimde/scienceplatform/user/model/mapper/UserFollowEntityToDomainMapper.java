package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserFollowEntityToDomainMapper extends BaseMapper<UserFollowEntity, UserFollow> {

	static UserFollowEntityToDomainMapper initialize() {
		return Mappers.getMapper(UserFollowEntityToDomainMapper.class);
	}

}

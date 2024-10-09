package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserFollowToEntityMapper extends BaseMapper<UserFollow, UserFollowEntity> {

	static UserFollowToEntityMapper initialize() {
		return Mappers.getMapper(UserFollowToEntityMapper.class);
	}

}

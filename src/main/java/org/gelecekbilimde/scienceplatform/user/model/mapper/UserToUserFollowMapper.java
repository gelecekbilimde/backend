package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserFollowMapper extends BaseMapper<User, UserFollow> {

	static UserToUserFollowMapper initialize() {
		return Mappers.getMapper(UserToUserFollowMapper.class);
	}

}

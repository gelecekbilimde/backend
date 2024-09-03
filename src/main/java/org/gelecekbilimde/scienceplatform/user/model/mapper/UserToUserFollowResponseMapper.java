package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollowResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserFollowResponseMapper extends BaseMapper<User, UserFollowResponse> {

	static UserToUserFollowResponseMapper initialize() {
		return Mappers.getMapper(UserToUserFollowResponseMapper.class);
	}

}

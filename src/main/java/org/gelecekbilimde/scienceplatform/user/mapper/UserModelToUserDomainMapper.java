package org.gelecekbilimde.scienceplatform.user.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.dto.domain.UserDomain;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserModelToUserDomainMapper extends BaseMapper<User, UserDomain> {

	@Override
	UserDomain map(User source);
	static UserModelToUserDomainMapper initialize() {
		return Mappers.getMapper(UserModelToUserDomainMapper.class);
	}
}

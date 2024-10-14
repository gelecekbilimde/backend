package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserVerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserVerificationEntityToDomainMapper extends BaseMapper<UserVerificationEntity, UserVerification> {

	static UserVerificationEntityToDomainMapper initialize() {
		return Mappers.getMapper(UserVerificationEntityToDomainMapper.class);
	}

}

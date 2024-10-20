package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.auth.model.entity.InvalidTokenEntity;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvalidTokenToEntityMapper extends BaseMapper<InvalidToken, InvalidTokenEntity> {

	static InvalidTokenToEntityMapper initialize() {
		return Mappers.getMapper(InvalidTokenToEntityMapper.class);
	}

}

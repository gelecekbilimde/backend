package org.gelecekbilimde.scienceplatform.notification.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.notification.model.NotificationToken;
import org.gelecekbilimde.scienceplatform.notification.model.entity.NotificationTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationTokenEntityToDomainMapper extends BaseMapper<NotificationTokenEntity, NotificationToken> {

	static NotificationTokenEntityToDomainMapper initialize() {
		return Mappers.getMapper(NotificationTokenEntityToDomainMapper.class);
	}

}

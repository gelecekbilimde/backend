package org.gelecekbilimde.scienceplatform.notification.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.notification.model.NotificationToken;
import org.gelecekbilimde.scienceplatform.notification.model.entity.NotificationTokenEntity;
import org.gelecekbilimde.scienceplatform.notification.model.mapper.NotificationTokenEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.notification.port.NotificationTokenReadPort;
import org.gelecekbilimde.scienceplatform.notification.repository.NotificationTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class NotificationTokenAdapter implements NotificationTokenReadPort {

	private final NotificationTokenRepository notificationTokenRepository;


	private final NotificationTokenEntityToDomainMapper notificationTokenEntityToDomainMapper = NotificationTokenEntityToDomainMapper.initialize();


	@Override
	public List<NotificationToken> findAllByUserId(final String userId) {
		final List<NotificationTokenEntity> notificationTokenEntities = notificationTokenRepository
			.findAllByUserId(userId);
		return notificationTokenEntityToDomainMapper.map(notificationTokenEntities);
	}

}

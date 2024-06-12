package org.gelecekbilimde.scienceplatform.notification.repository;

import org.gelecekbilimde.scienceplatform.notification.model.entity.NotificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationTokenRepository extends JpaRepository<NotificationTokenEntity, Long> {

	List<NotificationTokenEntity> findAllByUserId(String userId);

}

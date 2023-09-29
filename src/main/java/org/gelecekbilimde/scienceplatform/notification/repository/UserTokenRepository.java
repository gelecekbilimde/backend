package org.gelecekbilimde.scienceplatform.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<NotificationToken, Long> {

	List<NotificationToken> findAllByUserId(Long userId);
}

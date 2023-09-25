package org.gelecekbilimde.scienceplatform.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<NotificationToken, Long> {

	List<NotificationToken> findAllByUserId(Long userId);

//	@Query("DELETE FROM NotificationToken u WHERE u.deviceToken IN ?1")
//	void deleteDevicesById(List<String> tokens);
}

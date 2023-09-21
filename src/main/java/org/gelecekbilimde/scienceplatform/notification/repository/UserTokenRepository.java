package org.gelecekbilimde.scienceplatform.notification.repository;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<NotificationToken, Long> {

	@Query("SELECT u.deviceToken FROM NotificationToken u")
	List<String> findAllDeviceTokens();

	@Query("SELECT u.deviceToken FROM NotificationToken u WHERE u.user = ?1")
	List<String> findDeviceTokensByUserId(User user);

	@Query("DELETE FROM NotificationToken u WHERE u.deviceToken IN ?1")
	void deleteDevicesById(List<String> tokens);

}


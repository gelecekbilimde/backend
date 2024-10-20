package org.gelecekbilimde.scienceplatform.notification.port;

import org.gelecekbilimde.scienceplatform.notification.model.NotificationToken;

import java.util.List;

public interface NotificationTokenReadPort {

	List<NotificationToken> findAllByUserId(String userId);

}

package org.gelecekbilimde.scienceplatform.notification.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification_token")

public class NotificationToken extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "device_token")
	private String deviceToken;

	@Column(name = "device_id")
	private String deviceId;

}

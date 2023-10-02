package org.gelecekbilimde.scienceplatform.notification.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class NotificationToken {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(columnDefinition = "text")
	private String deviceToken;

	@Column(columnDefinition = "varchar(255)")
	private String deviceId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;
}

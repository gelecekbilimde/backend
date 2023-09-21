package org.gelecekbilimde.scienceplatform.notification.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class NotificationToken {

	@Id
	@GeneratedValue
	private Long id;

//	@Column(name = "user_id")
//	private Long userId;

	@Column(columnDefinition = "text")
	private String deviceToken;

	@Column(columnDefinition = "varchar(255)")
	private String deviceId;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false,updatable = false)
	private User user;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;

}

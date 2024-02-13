package org.gelecekbilimde.scienceplatform.common.mail.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {
	@Id
	@Column(name = "token_id")
	@Setter
	private String tokenId;

	@Column(name = "confirmation_token")
	private String confirmationToken = UUID.randomUUID().toString();

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate = LocalDateTime.now();

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	@Setter
	private User user;
}

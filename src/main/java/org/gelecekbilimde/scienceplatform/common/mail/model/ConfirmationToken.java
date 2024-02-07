package org.gelecekbilimde.scienceplatform.common.mail.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {
	@Id
	@Column(name = "token_id")
	private String tokenId;

	@Column(name = "confirmation_token")

	private String confirmationToken;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public ConfirmationToken(User user) {
		this.tokenId= Util.generateUUID();
		this.user = user;
		createdDate = LocalDateTime.now();
		confirmationToken = UUID.randomUUID().toString();
	}
}

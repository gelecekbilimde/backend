package org.gelecekbilimde.scienceplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tokenId;
	private String confirmationToken;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public ConfirmationToken(User user) {
		this.user = user;
		createdDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
	}
}

package org.gelecekbilimde.scienceplatform.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket_message")
public class Message extends BaseModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_id")
	private Long ticketId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "message")
	private String message;

}

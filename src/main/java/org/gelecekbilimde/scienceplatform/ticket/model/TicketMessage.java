package org.gelecekbilimde.scienceplatform.ticket.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "ticket_message")
public class TicketMessage extends BaseModel {
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

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;

}

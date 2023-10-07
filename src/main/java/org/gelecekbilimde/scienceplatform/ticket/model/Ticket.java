package org.gelecekbilimde.scienceplatform.ticket.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(columnDefinition = "text", nullable = false)
	private String message;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25) default 'OPEN'")
	private TicketStatus status = TicketStatus.OPEN;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updateAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;


}

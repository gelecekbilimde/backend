package org.gelecekbilimde.scienceplatform.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatus;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketSubject;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(columnDefinition = "text", nullable = false)
	private String message;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25) default 'OPEN'")
	private TicketStatus status = TicketStatus.OPEN;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25) default 'OTHER'")
	private TicketSubject subject = TicketSubject.OTHER;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updateAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createAt;

}

package org.gelecekbilimde.scienceplatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts_process")
public class PostProcess {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String header;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String slug;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25)")
	private PostProcessEnum process;


	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post postProcess;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(columnDefinition = "jsonb")
	private String message;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private Date createdDate;

}

package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media_group")
public class MediaGroup {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Integer parentId;

	@Column(columnDefinition = "varchar(50) default 'Space'", nullable = false)
	private String name;

	@OneToMany(mappedBy = "mediaGroup", cascade = CascadeType.ALL)
	private List<Media> mediaList;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;
}

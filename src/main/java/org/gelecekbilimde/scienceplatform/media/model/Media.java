package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;
import org.gelecekbilimde.scienceplatform.postmedia.model.PostMedia;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media")
public class Media {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String url;


	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25)", nullable = false)
	private MediaContentType type;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(25)", nullable = false)
	private MediaType mediaType;

	@Column(columnDefinition = "varchar(100)", nullable = false)
	private String title;

	@Column(columnDefinition ="boolean default false", nullable = false)
	private Boolean shared;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private MediaGroup mediaGroup;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
	private List<PostMedia> postMedia;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp")
	private Date createdDate;


}

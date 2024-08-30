package org.gelecekbilimde.scienceplatform.media.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaStatus;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaType;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gb_media")
public class MediaEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url")
	private String url;

	@Enumerated(EnumType.STRING)
	@Column(name = "content_type")
	private MediaContentType contentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type")
	private MediaType mediaType;

	@Column(name = "title")
	private String title;

	@Column(name = "is_shared")
	private boolean shared;

	@Column(name = "user_id")
	private String userId;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private MediaGroupEntity mediaGroupEntity;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;

	@OneToMany(mappedBy = "mediaEntity", cascade = CascadeType.ALL)
	private List<PostMediaEntity> postMediaEntity;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private MediaStatus status;

}

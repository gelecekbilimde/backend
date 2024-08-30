package org.gelecekbilimde.scienceplatform.post.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.entity.BaseEntity;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gb_post_media")
public class PostMediaEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "post_id")
	private String postId;

	@Column(name = "media_id")
	private Long mediaId;

	@Column(name = "is_cover")
	private boolean cover;

	@ManyToOne
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private PostEntity postEntity;

	@ManyToOne
	@JoinColumn(name = "media_id", insertable = false, updatable = false)
	private MediaEntity mediaEntity;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity userEntity;

}

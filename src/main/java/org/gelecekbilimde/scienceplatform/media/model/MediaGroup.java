package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media_group")
public class MediaGroup extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "name")
	private String name;

	@Column(name = "user_id")
	private String userId;

	@OneToMany(mappedBy = "mediaGroup", cascade = CascadeType.ALL)
	private List<Media> mediaList;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;

}

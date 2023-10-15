package org.gelecekbilimde.scienceplatform.media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.BaseModel;
import org.gelecekbilimde.scienceplatform.user.model.User;

import java.util.List;

@Entity
@Data
@SuperBuilder
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media_group")
public class MediaGroup extends BaseModel {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Integer parentId;

	@Column(name = "name")
	private String name;

	@Column(name = "user_id")
	private Long userId;

	@OneToMany(mappedBy = "mediaGroup", cascade = CascadeType.ALL)
	private List<Media> mediaList;

	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false, updatable = false)
	private User user;

}

package org.gelecekbilimde.scienceplatform.settings.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseModel;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "settings")
public class Settings extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "group_name")
	private String groupName;


	@Column(name = "name")
	private String name;

	@Column(name = "definition")
	private String definition;

	@Column(name = "is_hidden")
	private boolean isHidden;

}

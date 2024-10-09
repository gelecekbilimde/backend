package org.gelecekbilimde.scienceplatform.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserFollow extends BaseDomainModel {

	private Long id;
	private User follower;
	private User followed;

}

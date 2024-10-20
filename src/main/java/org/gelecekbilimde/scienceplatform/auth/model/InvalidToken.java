package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class InvalidToken extends BaseDomainModel {

	private Long id;
	private String tokenId;

}

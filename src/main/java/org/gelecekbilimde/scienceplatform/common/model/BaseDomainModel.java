package org.gelecekbilimde.scienceplatform.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public abstract class BaseDomainModel {

	protected String createdBy;
	protected LocalDateTime createdAt;
	protected String updatedBy;
	protected LocalDateTime updatedAt;

}

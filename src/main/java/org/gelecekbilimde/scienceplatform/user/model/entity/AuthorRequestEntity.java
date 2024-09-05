package org.gelecekbilimde.scienceplatform.user.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_request")
public class AuthorRequestEntity {

	@Id
	private String id;

	@OneToOne
	@Unique
	private UserEntity user;

}

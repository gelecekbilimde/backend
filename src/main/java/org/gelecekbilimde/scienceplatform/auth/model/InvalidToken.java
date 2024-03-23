package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.*;
import lombok.*;
import org.gelecekbilimde.scienceplatform.common.BaseModel;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invalid_token")
public class InvalidToken extends BaseModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "token_id")
	private String tokenId;
}

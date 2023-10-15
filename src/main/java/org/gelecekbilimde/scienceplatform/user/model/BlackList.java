package org.gelecekbilimde.scienceplatform.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "black_list")
public class BlackList {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name= "description")
	private String description;

}

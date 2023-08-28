package org.gelecekbilimde.scienceplatform.postProcess.dto.Domain;

import lombok.Data;
import lombok.NonNull;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;


@Data

public class Message {


	@NonNull
	private Long userId;

	@NonNull
	private String message;

	private Date createDate;

	@JsonIgnore
	private Boolean hidden = false;
}

package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;
import lombok.NonNull;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;


@Data

public class PostProcessMessage {


	@NonNull
	private Long userId;

	@NonNull
	private String message;

	private LocalDateTime createDate;

	@JsonIgnore
	private boolean hidden = false;
}

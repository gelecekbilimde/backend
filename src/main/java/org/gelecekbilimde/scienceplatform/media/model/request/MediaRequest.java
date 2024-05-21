package org.gelecekbilimde.scienceplatform.media.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaRequest {

	private String url;

	private MediaContentType contentType;

	private MediaType mediaType;

	private String title;

	private boolean shared = false;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private Long groupId;

	private Integer userId;

}

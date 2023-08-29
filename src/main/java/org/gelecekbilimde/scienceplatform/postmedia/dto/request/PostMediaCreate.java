package org.gelecekbilimde.scienceplatform.postmedia.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostMediaCreate {

    private Long postMediaId;

    private Boolean cover = false;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be null")
    private Long mediaId;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
    private Long postId;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
    private Long userId;
}

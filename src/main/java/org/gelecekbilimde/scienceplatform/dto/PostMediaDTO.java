package org.gelecekbilimde.scienceplatform.dto;

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
public class PostMediaDTO {

    private Integer postMediaId;

    private Boolean cover = false;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be null")
    private Integer mediaId;

    private Integer postId;

    private Integer userId;
}

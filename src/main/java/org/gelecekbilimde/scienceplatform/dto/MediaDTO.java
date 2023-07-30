package org.gelecekbilimde.scienceplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.gelecekbilimde.scienceplatform.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.model.enums.MediaType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {


    @JsonIgnore
    private String url;

    private MediaContentType type;

    private MediaType mediaType;

    private String Title;

    private Boolean shared=false;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be null")
    private Integer groupId;

    private Integer userId;
}

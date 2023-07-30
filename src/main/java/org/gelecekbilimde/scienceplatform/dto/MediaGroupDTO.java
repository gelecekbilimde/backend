package org.gelecekbilimde.scienceplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaGroupDTO {

    private List<MediaDTO> medias;

    private Integer groupId;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be null")
    private String name;

    private Integer parentId;

}

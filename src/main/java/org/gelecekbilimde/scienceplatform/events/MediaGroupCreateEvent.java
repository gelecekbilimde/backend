package org.gelecekbilimde.scienceplatform.events;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.dto.MediaGroupDTO;
import org.gelecekbilimde.scienceplatform.dto.PostDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.service.MediaService;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;

import java.time.Clock;

@Getter
public class MediaGroupCreateEvent extends ApplicationEvent {

    private final User user;
    ;
    private final MediaGroupDTO mediaGroupDTO;


    public MediaGroupCreateEvent(MediaService mediaService, String name, @Nullable Integer parentId, User user) {

        super(mediaService);

        MediaGroupDTO mediaGroupDTO = new MediaGroupDTO();
        mediaGroupDTO.setName(name);
        mediaGroupDTO.setParentId(parentId);

        this.mediaGroupDTO = mediaGroupDTO;
        this.user = user;
    }

    public MediaGroupCreateEvent(MediaService mediaService, String name, @Nullable Integer parentId, Clock clock, User user) {
        super(mediaService, clock);

        MediaGroupDTO mediaGroupDTO = new MediaGroupDTO();
        mediaGroupDTO.setName(name);
        mediaGroupDTO.setParentId(parentId);

        this.mediaGroupDTO = mediaGroupDTO;
        this.user = user;
    }

}

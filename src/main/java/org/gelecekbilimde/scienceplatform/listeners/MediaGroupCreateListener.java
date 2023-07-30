package org.gelecekbilimde.scienceplatform.listeners;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.MediaGroupDTO;
import org.gelecekbilimde.scienceplatform.events.MediaGroupCreateEvent;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.service.MediaService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MediaGroupCreateListener implements ApplicationListener<MediaGroupCreateEvent> {

	private final MediaService mediaService;
	@Override
	public void onApplicationEvent(MediaGroupCreateEvent event) {
		MediaGroupDTO mediaGroupDTO = event.getMediaGroupDTO();
		User user = event.getUser();

		mediaService.saveMediaGroup(mediaGroupDTO, user);
	}
}

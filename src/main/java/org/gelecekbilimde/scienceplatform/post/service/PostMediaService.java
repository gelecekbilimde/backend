package org.gelecekbilimde.scienceplatform.post.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.post.mapper.PostMediaCreateToPostMediaModelMapper;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.repository.PostMediaRepository;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMediaService {

	private final Identity identity;

	private final PostMediaRepository postMediaRepository;
	private static final PostMediaCreateToPostMediaModelMapper postMediaCreateToPostMediaModel = PostMediaCreateToPostMediaModelMapper.initialize();

	public void savePostMedia(List<PostMediaCreate> postMediaCreateList) {
		postMediaCreateList.forEach(this::savePostMediaOne);

	}

	@Transactional
	public PostMediaCreate savePostMediaOne(@Valid PostMediaCreate postMediaCreate) {

		PostMedia postMedia = postMediaCreateToPostMediaModel.mapForSaving(postMediaCreate,identity.getUserId());

		PostMedia data = postMediaRepository.save(postMedia);
		postMedia.setId(data.getId());
		return postMediaCreate;
	}

}

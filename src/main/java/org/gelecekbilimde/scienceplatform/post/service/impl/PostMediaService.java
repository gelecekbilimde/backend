package org.gelecekbilimde.scienceplatform.post.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostMediaDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.PostMediaCreateToPostMediaModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostMediaModelToPostMediaDomainMapper;
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
	private static final PostMediaModelToPostMediaDomainMapper postMediaModelToPostMediaDomainMapper = PostMediaModelToPostMediaDomainMapper.initialize();

	public List<PostMediaDomain> savePostMedia(List<PostMediaCreateRequest> postMediaCreateRequestList) {
		return postMediaCreateRequestList.stream().map(this::savePostMediaOne).toList();

	}

	@Transactional
	public PostMediaDomain savePostMediaOne(@Valid PostMediaCreateRequest postMediaCreateRequest) {

		final PostMedia postMediaSave = postMediaCreateToPostMediaModel.mapForSaving(postMediaCreateRequest,identity.getUserId());

		PostMedia postMedia = postMediaRepository.save(postMediaSave);

        return postMediaModelToPostMediaDomainMapper.map(postMedia);
	}

}

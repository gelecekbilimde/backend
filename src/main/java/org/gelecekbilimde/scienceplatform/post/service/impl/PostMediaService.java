package org.gelecekbilimde.scienceplatform.post.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostMediaCreateRequestToPostMediaEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostMediaEntityToPostMediaMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.repository.PostMediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMediaService { // TODO : interface yazılmalı

	private final Identity identity;

	private final PostMediaRepository postMediaRepository;

	private final PostMediaCreateRequestToPostMediaEntityMapper postMediaCreateToPostMediaModel = PostMediaCreateRequestToPostMediaEntityMapper.initialize();
	private final PostMediaEntityToPostMediaMapper postMediaEntityToPostMediaMapper = PostMediaEntityToPostMediaMapper.initialize();

	public List<PostMedia> savePostMedia(List<PostMediaCreateRequest> postMediaCreateRequestList) {
		return postMediaCreateRequestList.stream().map(this::savePostMediaOne).toList();
	}

	@Transactional
	public PostMedia savePostMediaOne(@Valid PostMediaCreateRequest postMediaCreateRequest) {

		final PostMediaEntity postMediaEntitySave = postMediaCreateToPostMediaModel.mapForSaving(postMediaCreateRequest, identity.getUserId());

		PostMediaEntity postMediaEntity = postMediaRepository.save(postMediaEntitySave);

		return postMediaEntityToPostMediaMapper.map(postMediaEntity);
	}

}

package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostMediaCreateRequestToPostMediaEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostMediaEntityToPostMediaMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.repository.PostMediaRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostMediaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class PostMediaServiceImpl implements PostMediaService {

	private final Identity identity;

	private final PostMediaRepository postMediaRepository;

	private final PostMediaCreateRequestToPostMediaEntityMapper postMediaCreateToPostMediaModel = PostMediaCreateRequestToPostMediaEntityMapper.initialize();
	private final PostMediaEntityToPostMediaMapper postMediaEntityToPostMediaMapper = PostMediaEntityToPostMediaMapper.initialize();

	@Override
	public List<PostMedia> savePostMedias(List<PostMediaCreateRequest> postMediaCreateRequests) {
		return postMediaCreateRequests.stream().map(this::savePostMedia).toList(); // TODO : tek tek veritabanına kaydetmek yerine toplu bir şekilde kaydedilmeli!
	}

	private PostMedia savePostMedia(PostMediaCreateRequest postMediaCreateRequest) {

		final PostMediaEntity postMediaEntitySave = postMediaCreateToPostMediaModel.mapForSaving(postMediaCreateRequest, identity.getUserId());

		PostMediaEntity postMediaEntity = postMediaRepository.save(postMediaEntitySave);

		return postMediaEntityToPostMediaMapper.map(postMediaEntity);
	}

}

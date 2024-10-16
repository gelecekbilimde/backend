package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BaseSpecification;
import org.gelecekbilimde.scienceplatform.media.util.SlugUtil;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostCreateRequestToEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostEntityToPostMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostMediaService;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostProcessService postProcessService;
	private final PostMediaService postMediaService;
	private final Identity identity;

	private final PostCreateRequestToEntityMapper createRequestToEntityMapper = PostCreateRequestToEntityMapper.initialize();
	private final PostEntityToPostMapper postEntityToPostMapper = PostEntityToPostMapper.initialize();

	@Transactional
	public Post save(PostCreateRequest postCreateRequest) {

		if (postCreateRequest.getMedias() != null && !postCreateRequest.getMedias().stream().allMatch(postMediaCreate -> true)) {
			// TODO : Bu kontrol request objesi içerisine taşınmalı
		}

		postCreateRequest.setLastProcess(Process.CREATE);
		postCreateRequest.setSlug(SlugUtil.slugging(postCreateRequest.getHeader()));

		final PostEntity postEntity = createRequestToEntityMapper.map(postCreateRequest);
		postEntity.setUserId(identity.getUserId());

		PostEntity postEntityFromDatabase = postRepository.save(postEntity);

		Post post = postEntityToPostMapper.map(postEntityFromDatabase);

		if (postCreateRequest.getMedias() != null) {

			List<PostMediaCreateRequest> postMediaCreateRequestList = postCreateRequest.getMedias().stream().map(postMediaCreate -> {
				postMediaCreate.setPostId(post.getId());
				postMediaCreate.setUserId(post.getUserId()); // TODO : Toplu Bir Şekilde İncelenecek
				return postMediaCreate;
			}).toList();

			post.setMedias(postMediaService.savePostMedias(postMediaCreateRequestList));

		}

		post.setLastProcess(Process.CREATE);
		postProcessService.savePostProcess(post, true);

		return post;
	}

	@Override
	public BasePage<Post> getPostListAdmin(AdminPostListRequest listRequest) {
		if (listRequest.getFilter() == null || listRequest.getFilter().getIsActive() == null) {
			return this.getPostListForAdmin(listRequest);
		}
		return this.getFilteredPostListForAdmin(listRequest);
	}

	private BasePage<Post> getPostListForAdmin(AdminPostListRequest listRequest) {
		Page<PostEntity> postEntities = postRepository.findAll(listRequest.getPageable().toPageable());
		List<Post> posts = postEntityToPostMapper.map(postEntities.getContent());
		return BasePage.of(postEntities, posts);
	}

	private BasePage<Post> getFilteredPostListForAdmin(AdminPostListRequest listRequest) {
		Boolean isActive = listRequest.getFilter().getIsActive();
		final Map<String, Object> filter = Map.of("active", isActive);
		final Specification<PostEntity> specification = BaseSpecification.<PostEntity>builder().and(filter);
		final Page<PostEntity> postEntities = postRepository.findAll(specification, listRequest.getPageable().toPageable());
		final List<Post> posts = postEntityToPostMapper.map(postEntities.getContent());
		return BasePage.of(postEntities, posts);
	}

}

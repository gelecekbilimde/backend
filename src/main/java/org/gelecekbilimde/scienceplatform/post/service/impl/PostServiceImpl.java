package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseSpecification;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.mapper.PostCreateRequestToPostModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostModelToPostDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
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
	private static final PostCreateRequestToPostModelMapper postCreateRequestToPostModel = PostCreateRequestToPostModelMapper.initialize();
	private static final PostModelToPostDomainMapper postModelToPostDomain = PostModelToPostDomainMapper.initialize();

	@Transactional
	public PostDomain save(PostCreateRequest postCreateRequest) {

		if (postCreateRequest.getMedias() != null && !postCreateRequest.getMedias().stream().allMatch(postMediaCreate -> true)) {
			throw new ClientException("Posta media yüklerken media id zorunlu |" + postCreateRequest.getMedias().toString());
		}

		Helper helper = new Helper();

		postCreateRequest.setLastProcess(Process.CREATE);
		postCreateRequest.setSlug(helper.slugify(postCreateRequest.getHeader()));

		final Post postSave = postCreateRequestToPostModel.mapForSaving(postCreateRequest, identity.getUserId());

		Post post = postRepository.save(postSave);

		PostDomain postDomain = postModelToPostDomain.map(post);

		if (postCreateRequest.getMedias() != null) {

			List<PostMediaCreateRequest> postMediaCreateRequestList = postCreateRequest.getMedias().stream().map(postMediaCreate -> {
				postMediaCreate.setPostId(postDomain.getPostId());
				postMediaCreate.setUserId(postDomain.getUserId());
				return postMediaCreate;
			}).toList();

			postDomain.setMedias(postMediaService.savePostMedia(postMediaCreateRequestList));

		}

		// CREATE için process gönder
		postDomain.setLastProcess(Process.CREATE);
		postProcessService.savePostProcess(postDomain, true);

		return postDomain;
	}

	@Override
	public Paging<PostDomain> getPostListAdmin(AdminPostListRequest listRequest) {
		if (listRequest.getIsActive() == null) {
			return this.getPostListForAdmin(listRequest);
		}
		return this.getFilteredPostListForAdmin(listRequest);
	}

	private Paging<PostDomain> getPostListForAdmin(AdminPostListRequest listRequest) {
		Page<Post> postModels = postRepository.findAll(listRequest.toPageable());
		List<PostDomain> domainDTOList = postModelToPostDomain.map(postModels.getContent());
		return Paging.of(postModels, domainDTOList);
	}

	private Paging<PostDomain> getFilteredPostListForAdmin(AdminPostListRequest listRequest) {
		final Map<String, Object> filter = Map.of("active", listRequest.getIsActive());
		final Specification<Post> specification = BaseSpecification.<Post>builder().and(filter);
		final Page<Post> postModels = postRepository.findAll(specification, listRequest.toPageable());
		final List<PostDomain> domainDTOList = postModelToPostDomain.map(postModels.getContent());
		return Paging.of(postModels, domainDTOList);
	}

}

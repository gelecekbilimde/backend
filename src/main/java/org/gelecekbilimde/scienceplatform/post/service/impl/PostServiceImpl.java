package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.BaseSpecification;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.exception.NotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.mapper.PostCreateRequestToPostModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostLikeRequestToPostLikeModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostModelToPostDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.gelecekbilimde.scienceplatform.post.repository.PostLikeRepository;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


// https://drive.google.com/file/d/1F8TCSt_Oo4Yjl2q32r3XjoTarcLn85KI/view?usp=sharing
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostProcessService postProcessService;
	private final PostMediaService postMediaService;
	private final PostLikeRepository postLikeRepository;
	private final Identity identity;
	private static final PostCreateRequestToPostModelMapper postCreateRequestToPostModel = PostCreateRequestToPostModelMapper.initialize();
	private static final PostModelToPostDomainMapper postModelToPostDomain = PostModelToPostDomainMapper.initialize();
	private static final PostLikeRequestToPostLikeModelMapper postLikeRequestToPostLikeModel = PostLikeRequestToPostLikeModelMapper.initialize();


	@Transactional
	public PostDomain save(PostCreateRequest postCreateRequest) {

		if (postCreateRequest.getMedias() != null && !postCreateRequest.getMedias().stream().allMatch(postMediaCreate -> postMediaCreate.getMediaId() != null)) {
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

	public Paging<PostDomain> getPostListAdmin(AdminPostListRequest listRequest) {
		if (listRequest.getIsActive() == null) {
			return getPostListForAdmin(listRequest);
		}
		return getFilteredPostListForAdmin(listRequest);
	}

	@Override
	public Response<PostLikeResponse> likePost(String postId) {
		try {
			String userId = identity.getUserId();
			Post post = isPostExistsById(postId);
			int likecount = post.getLikeCount();
			PostLike postLike;
			if (!isUserLikedPost(userId, post.getId())) {
				postLike = postLikeRequestToPostLikeModel.mapForSaving(postId, userId);
				postLikeRepository.save(postLike);
				likecount += 1;
				post.setLikeCount(likecount);
				postRepository.save(post);
			} else {
				postLike = postLikeRepository.findPostLikeByPostIdAndUserId(post.getId(), userId);
				postLikeRepository.delete(postLike);
				likecount -= 1;
				post.setLikeCount(likecount);
				postRepository.save(post);
			}
			return Response.<PostLikeResponse>builder()
				.list(PostLikeResponse.builder().likeCount(likecount).createdAt(postLike.getCreatedAt()).build())
				.responseCode(HttpStatus.OK.toString())
				.build();
		} catch (Exception e) {
			throw new NotFoundException("Post not found");
		}
	}

	private Post isPostExistsById(String postId) {
		Post post = postRepository.findByIdEquals(postId);
		if (post == null) {
			throw new NotFoundException("Post not found");
		}
		return post;
	}

	private boolean isUserLikedPost(String userId, String postId) {
		return postLikeRepository.findPostLikeByPostIdAndUserId(postId, userId) != null;
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

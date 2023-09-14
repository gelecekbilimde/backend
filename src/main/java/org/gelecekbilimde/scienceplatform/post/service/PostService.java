package org.gelecekbilimde.scienceplatform.post.service;

import lombok.RequiredArgsConstructor;

import org.gelecekbilimde.scienceplatform.post.dto.domain.AdminPostListDomain;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.mapper.AdminPostModelToAdminPostListDomainMapper;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.PostCreateRequestToPostModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostDomainToPostProcessCreateMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostModelToPostDomainMapper;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


// https://drive.google.com/file/d/1F8TCSt_Oo4Yjl2q32r3XjoTarcLn85KI/view?usp=sharing
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final PostProcessService postProcessService;
	private final PostMediaService postMediaService;

	private final Identity identity;
	private static final AdminPostModelToAdminPostListDomainMapper adminPostModelToDomainMapper = AdminPostModelToAdminPostListDomainMapper.initialize();
	private static final PostCreateRequestToPostModelMapper postCreateRequestToPostModel = PostCreateRequestToPostModelMapper.initialize();
	private static final PostModelToPostDomainMapper postModelToPostDomain = PostModelToPostDomainMapper.initialize();
	private static final PostDomainToPostProcessCreateMapper postDomainToPostProcessCreate = PostDomainToPostProcessCreateMapper.initialize();


	@Transactional
	public PostDomain save (PostCreateRequest postCreateRequest){

		if (postCreateRequest.getMedias() != null && !postCreateRequest.getMedias().stream().allMatch(postMediaCreate -> postMediaCreate.getMediaId() != null)){
			throw new ClientException("Posta media yüklerken media id zorunlu |"+postCreateRequest.getMedias().toString());
		}

		Helper helper = new Helper();

		postCreateRequest.setLastProcess(PostProcessEnum.CREATE);
		postCreateRequest.setSlug(helper.slugify(postCreateRequest.getHeader()));

		final Post postSave = postCreateRequestToPostModel.mapForSaving(postCreateRequest, identity.getUserId());

		Post post = postRepository.save(postSave);

		PostDomain postDomain = postModelToPostDomain.map(post);

		// ilk process Kaydını gönder
		postProcessService.savePostAfter(postDomainToPostProcessCreate.map(postDomain));


		if (postCreateRequest.getMedias() != null){

			List<PostMediaCreate> postMediaCreateList = postCreateRequest.getMedias().stream().map(postMediaCreate -> {
				postMediaCreate.setPostId(postDomain.getPostId());
				postMediaCreate.setUserId(postDomain.getUserId());
				return postMediaCreate;
			}).collect(Collectors.toList());

			postMediaService.savePostMedia(postMediaCreateList);
		}

		return postDomain;
	}

	public Paging<AdminPostListDomain> getPostListForAdmin(AdminPostListRequest listRequest) {
		Page<Post> postModels = postRepository.findAll(listRequest.toPageable());
		List<AdminPostListDomain> domainDTOList = adminPostModelToDomainMapper.map(postModels.getContent());
		return Paging.of(postModels, domainDTOList);
	}
}

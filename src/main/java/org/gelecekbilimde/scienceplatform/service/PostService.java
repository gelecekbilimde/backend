package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;

import org.gelecekbilimde.scienceplatform.Mapper.AdminPostModelToBusinessMapper;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.dto.Post.Business.AdminPostListBusinessDTO;
import org.gelecekbilimde.scienceplatform.dto.Post.PostCreateDTO;
import org.gelecekbilimde.scienceplatform.dto.Post.Request.AdminPostListRequestDTO;
import org.gelecekbilimde.scienceplatform.dto.PostMediaDTO;
import org.gelecekbilimde.scienceplatform.events.PostMediaCreateEvent;
import org.gelecekbilimde.scienceplatform.events.PostProcessCreateEvent;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.model.Media;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.gelecekbilimde.scienceplatform.model.PostMedia;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.repository.MediaRepository;
import org.gelecekbilimde.scienceplatform.repository.PostMediaRepository;
import org.gelecekbilimde.scienceplatform.repository.PostRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// https://drive.google.com/file/d/1F8TCSt_Oo4Yjl2q32r3XjoTarcLn85KI/view?usp=sharing
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final ApplicationContext applicationContext;
	private final PostMediaRepository postMediaRepository;
	private final MediaRepository mediaRepository;
	private static final AdminPostModelToBusinessMapper adminPostModelToBusinessMapper = AdminPostModelToBusinessMapper.initialize();

	@Transactional
	public PostCreateDTO save (PostCreateDTO postCreateDTO, User user){
		Helper helper = new Helper();

		postCreateDTO.setLastProcess(PostProcessEnum.CREATE);
		postCreateDTO.setSlug(helper.slugify(postCreateDTO.getHeader()));

		var post = Post.builder()
				.header(postCreateDTO.getHeader())
				.content(postCreateDTO.getContent())
				.user(user)
				.likeCount(postCreateDTO.getLikeCount())
				.active(postCreateDTO.getActive())
				.lastProcess(postCreateDTO.getLastProcess())
				.slug(postCreateDTO.getSlug())
				.build();

		postRepository.save(post);
		applicationContext.publishEvent(new PostProcessCreateEvent(post, postCreateDTO, user));

		if (postCreateDTO.getMedias() != null){
			applicationContext.publishEvent(new PostMediaCreateEvent(this, postCreateDTO.getMedias(), post.getId().intValue(), user));
		}

		return postCreateDTO;
	}

	@Transactional
	public void savePostMedia(Integer postId, List<PostMediaDTO> postMediaDTOs, User user) {

		Post post = this.postRepository.findById(postId).orElseThrow(()->new ClientException("Post Bulunamadı"));
		postMediaDTOs
			.stream()
			.map(postMediaDTO -> savePostMediaOne(postMediaDTO,post,user));
	}

	@Transactional
	public PostMediaDTO savePostMediaOne(PostMediaDTO postMediaDTO, Post post, User user) {

		if (postMediaDTO.getMediaId() == null){
			throw new ClientException("media idsi boş olamaz|" + postMediaDTO.toString());
		}

		Media media = this.mediaRepository.findById(postMediaDTO.getMediaId()).orElseThrow(() -> new ClientException("Media Bulunamadı"));

		var postMedia = PostMedia.builder()
				.media(media)
				.postMedia(post)
				.cover(postMediaDTO.getCover())
				.user(user)
				.build()
				;

		PostMedia data = this.postMediaRepository.save(postMedia);
		postMedia.setId(data.getId());
		return postMediaDTO;
	}


	public Paging<AdminPostListBusinessDTO> getPostListForAdmin(AdminPostListRequestDTO listRequest) {
		Page<Post> postModels = postRepository.findAll(listRequest.toPageable());
		List<AdminPostListBusinessDTO> businessDTOList = adminPostModelToBusinessMapper.map(postModels.getContent());
		return Paging.of(postModels, businessDTOList);
	}
}

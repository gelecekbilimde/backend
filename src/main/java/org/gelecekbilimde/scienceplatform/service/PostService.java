package org.gelecekbilimde.scienceplatform.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.gelecekbilimde.scienceplatform.dto.PostDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

	@Autowired
	public PostService(ApplicationContext applicationContext, PostRepository postRepository, PostMediaRepository postMediaRepository, MediaRepository mediaRepository) {
		this.applicationContext = applicationContext;
		this.postRepository = postRepository;
		this.postMediaRepository = postMediaRepository;
		this.mediaRepository = mediaRepository;
	}

	@Transactional
	public PostDTO save (PostDTO postDTO,User user){
		Helper helper = new Helper();

		postDTO.setLastProcess(PostProcessEnum.CREATE);
		postDTO.setSlug(helper.slugify(postDTO.getHeader()));

		var post = Post.builder()
				.header(postDTO.getHeader())
				.content(postDTO.getContent())
				.user(user)
				.likeCount(postDTO.getLikeCount())
				.active(postDTO.getActive())
				.lastProcess(postDTO.getLastProcess())
				.slug(postDTO.getSlug())
				.build();

		postRepository.save(post);
		applicationContext.publishEvent(new PostProcessCreateEvent(post,postDTO, user));

		if (!postDTO.getMedias().isEmpty()){
			applicationContext.publishEvent(new PostMediaCreateEvent(this, postDTO.getMedias(), post.getId().intValue(), user));
		}

		return postDTO;
	}

	@Transactional
	public void savePostMedia(Integer postId, List<PostMediaDTO> postMediaDTOs, User user) {

		Post post = this.postRepository.findById(postId).orElseThrow(()->new ClientException("Post Bulunamadı"));
		for (PostMediaDTO postMediaDTO : postMediaDTOs) {
			this.savePostMediaOne(postMediaDTO,post,user);
		}

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

}

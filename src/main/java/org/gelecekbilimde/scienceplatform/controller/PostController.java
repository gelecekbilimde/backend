package org.gelecekbilimde.scienceplatform.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.dto.post.PostCreateDTO;
import org.gelecekbilimde.scienceplatform.dto.PostMediaDTO;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

	private final PostService postService;
	private final UserRepository userRepository;
	@PostMapping("")
	@PreAuthorize("hasAuthority('post:create')")
	public Response<Void> savePost(HttpServletRequest httpServletRequest, @RequestBody @Valid PostCreateDTO request)
	{
		if (!request.getMedias().isEmpty()){
			for(PostMediaDTO postMediaDTO : request.getMedias()){
				if (postMediaDTO.getMediaId() == null){
					throw new ClientException("Posta media yüklerken media id zorunlu |"+request.getMedias().toString());
				}
			}
		}

		postService.save(request,(User)httpServletRequest.getAttribute("user"));
		return Response.NO_CONTENT;
	}
}

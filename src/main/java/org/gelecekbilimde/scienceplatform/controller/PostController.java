package org.gelecekbilimde.scienceplatform.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.dto.PostDTO;
import org.gelecekbilimde.scienceplatform.dto.PostMediaDTO;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final UserRepository userRepository;
	@PostMapping("")
	@PreAuthorize("hasAuthority('post:create')")
	public ResponseEntity<ApiResponse> secretVersionRole(HttpServletRequest httpServletRequest, @RequestBody @Valid PostDTO request)
	{
		if (!request.getMedias().isEmpty()){
			for(PostMediaDTO postMediaDTO : request.getMedias()){
				if (postMediaDTO.getMediaId() == null){
					throw new ClientException("Posta media yüklerken media id zorunlu |"+request.getMedias().toString());
				}
			}
		}

		postService.save(request,(User)httpServletRequest.getAttribute("user"));
		return Response.noContent();
	}
}

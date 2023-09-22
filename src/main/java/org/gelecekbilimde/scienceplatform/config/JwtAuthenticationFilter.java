package org.gelecekbilimde.scienceplatform.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.exception.UnAuthorizedException;
import org.gelecekbilimde.scienceplatform.auth.model.Permission;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.post.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.repository.TokenRepository;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final TokenRepository tokenRepository;
	private final RoleRepository roleRepository;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws UnAuthorizedException, ServletException, IOException {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String jwt;
		final String userName;
		final String roleName;

		if (null == authHeader || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);
		userName 	= jwtService.extractSubject(jwt);
		roleName  	= jwtService.extractClaim(jwt,"role").toString();

		if (null != SecurityContextHolder.getContext().getAuthentication()){
			filterChain.doFilter(request,response);
			return;
		}

		// todo many to many yapılandırmasında boş sonuç döndüğü için böyle !!!!! Düzeltilmeli!!!!!!!
		Role role = roleRepository.findByName(roleName).orElseThrow(()->new ServerException("Role bilgisine ulaşılamadı"));
		Set<Permission> permissions = new HashSet<>(roleRepository.findPermissionsByName(roleName));
		role.setPermissions(permissions);

		if (JwtService.GUEST_USERNAME.equals(roleName) && jwtService.isGuestTokenValid(jwt)){

			User user = new User();
			user.setRole(role);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				jwt,
				null,
				user.getAuthorities()
			);
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);

			filterChain.doFilter(request,response);
			return;
		}

		User userDetails = (User) this.userDetailsService.loadUserByUsername(userName);


		var isTokenValid = tokenRepository.findByJwt(jwt)
			.map(t -> !t.isExpired() && !t.isRevoked())
			.orElse(false);

		if (jwtService.isTokenValid(jwt, userDetails) && Boolean.TRUE.equals(isTokenValid)) {

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				role.getPermissions()
			);
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);

		}

		filterChain.doFilter(request,response);
	}
}

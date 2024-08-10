package org.gelecekbilimde.scienceplatform.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.entity.PermissionEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.impl.JwtService;
import org.gelecekbilimde.scienceplatform.common.exception.ServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final InvalidTokenService invalidTokenService;
	private final RoleRepository roleRepository;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain filterChain)
		throws ServletException, IOException {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (null == authHeader || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String token = authHeader.substring(7);

		final Claims claims = jwtService.extractAllClaims(token);
		invalidTokenService.checkForInvalidityOfToken(claims.getId());

		final String roleId = claims.get(TokenClaims.ROLE_ID.getValue(), String.class);

		RoleEntity roleEntity = roleRepository.findById(roleId)
			.orElseThrow(() -> new ServerException("Role not found"));
		Set<PermissionEntity> permissionEntities = new HashSet<>(roleRepository.findPermissionsByRoleId(roleId));
		roleEntity.setPermissionEntities(permissionEntities);

		final Jwt jwt = new Jwt(
			token,
			Instant.ofEpochSecond(claims.getIssuedAt().getTime()),
			Instant.ofEpochSecond(claims.getExpiration().getTime()),
			Map.of(TokenClaims.ALGORITHM.getValue(), SignatureAlgorithm.RS512.getValue()),
			claims
		);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			jwt,
			null,
			roleEntity.getPermissionEntities()
		);
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
		filterChain.doFilter(request, response);
	}

}

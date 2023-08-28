package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query(value = """
		SELECT T
			FROM Token T
			INNER JOIN User U ON T.user.id = U.id
			WHERE U.id = :id
				AND (T.expired = false OR T.revoked = false)
		""")
	List<Token> findAllValidTokenByUser(Long id);

	Optional<Token> findByToken(String token);}

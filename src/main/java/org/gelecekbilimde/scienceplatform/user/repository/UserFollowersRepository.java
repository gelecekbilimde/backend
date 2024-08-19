package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserFollowersRepository extends JpaRepository<UserFollowerEntity, String> {
	Optional<UserFollowerEntity> findByFollowedUserIdAndFollowerUserId(String followedUserId, String followerUserId);

	@Query("SELECT uf.followerUserId FROM UserFollowerEntity uf WHERE uf.followedUserId = :userId")
	List<UserEntity> findFollowersByUserId(@Param("userId") String userId);

	@Query("SELECT uf.followedUserId FROM UserFollowerEntity uf WHERE uf.followerUserId = :userId")
	List<UserEntity> findFollowingsByUserId(@Param("userId") String userId);
}

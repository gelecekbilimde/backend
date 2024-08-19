package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserFollowersRepository extends JpaRepository<UserFollowerEntity, String> {
	Optional<UserFollowerEntity> findByFollowedUserIdAndFollowerUserId(String followedUserId, String followerUserId);
}

package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {

	Optional<UserFollowEntity> findByFollowedUserIdAndFollowerUserId(String followedUserId, String followerUserId);

}

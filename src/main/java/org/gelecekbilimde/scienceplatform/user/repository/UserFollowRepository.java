package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {

	List<UserFollowEntity> findAllByFollowed(UserEntity followed);

	List<UserFollowEntity> findAllByFollower(UserEntity follower);

	Optional<UserFollowEntity> findByFollowedAndFollower(UserEntity followed, UserEntity follower);

}

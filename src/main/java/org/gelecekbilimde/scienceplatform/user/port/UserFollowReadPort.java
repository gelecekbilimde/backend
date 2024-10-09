package org.gelecekbilimde.scienceplatform.user.port;

import org.gelecekbilimde.scienceplatform.user.model.UserFollow;

import java.util.Optional;

public interface UserFollowReadPort {

	Optional<UserFollow> findByFollowedUserIdAndFollowerUserId(String followedUserId, String followerUserId);

}

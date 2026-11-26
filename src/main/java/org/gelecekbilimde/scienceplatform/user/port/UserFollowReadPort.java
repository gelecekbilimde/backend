package org.gelecekbilimde.scienceplatform.user.port;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;

import java.util.List;
import java.util.Optional;

public interface UserFollowReadPort {

	List<UserFollow> findAllByFollowed(User followed);

	List<UserFollow> findAllByFollower(User follower);

	Optional<UserFollow> findByFollowedAndFollower(User followed, User follower);

}

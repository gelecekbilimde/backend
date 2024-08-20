package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.request.RemoveFollower;

import java.util.List;

public interface UserService {
	void followToggle(String id);
	List<User> getFollowings(String id);
	List<User> getFollowers(String id);
	void removeFollower(RemoveFollower request);
}

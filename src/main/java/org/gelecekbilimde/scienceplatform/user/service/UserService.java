package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import java.util.List;

public interface UserService {
	void followToggle(String id);
	List<UserEntity> getFollowings(String id);
	List<UserEntity> getFollowers(String id);
}

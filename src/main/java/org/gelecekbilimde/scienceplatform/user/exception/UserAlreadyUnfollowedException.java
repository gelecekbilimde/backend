package org.gelecekbilimde.scienceplatform.user.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public class UserAlreadyUnfollowedException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -5943822734840037155L;

	public UserAlreadyUnfollowedException(String followerUserId, String followedUserId) {
		super("user with id " + followerUserId + " already unfollowed user with id " + followedUserId);
	}

}

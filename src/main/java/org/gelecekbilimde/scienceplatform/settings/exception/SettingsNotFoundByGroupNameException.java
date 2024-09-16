package org.gelecekbilimde.scienceplatform.settings.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class SettingsNotFoundByGroupNameException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = 6365994753340820279L;

	public SettingsNotFoundByGroupNameException(String groupName) {
		super("settings not found! groupName: " + groupName);
	}

}

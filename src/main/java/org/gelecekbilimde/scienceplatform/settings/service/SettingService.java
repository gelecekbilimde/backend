package org.gelecekbilimde.scienceplatform.settings.service;

import org.gelecekbilimde.scienceplatform.settings.model.Settings;

import java.util.List;

public interface SettingService {

	List<Settings> getSettings(String groupName, String name);

}

package org.gelecekbilimde.scienceplatform.settings.service;

import org.gelecekbilimde.scienceplatform.settings.dto.domain.SettingsDomain;

import java.util.List;

public interface SettingService {

	List<SettingsDomain> getSettings(String groupName, String name);

}

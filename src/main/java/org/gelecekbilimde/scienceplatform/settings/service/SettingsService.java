package org.gelecekbilimde.scienceplatform.settings.service;

import org.gelecekbilimde.scienceplatform.settings.dto.domain.SettingsDomain;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SettingsService {

	List<SettingsDomain> getSettings(String groupName, @Nullable String name);
}

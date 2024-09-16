package org.gelecekbilimde.scienceplatform.settings.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.settings.exception.SettingsNotFoundByGroupNameException;
import org.gelecekbilimde.scienceplatform.settings.model.Settings;
import org.gelecekbilimde.scienceplatform.settings.model.entity.SettingsEntity;
import org.gelecekbilimde.scienceplatform.settings.model.mapper.SettingsEntityToSettingsMapper;
import org.gelecekbilimde.scienceplatform.settings.repository.SettingsRepository;
import org.gelecekbilimde.scienceplatform.settings.service.SettingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
class SettingServiceImpl implements SettingService {

	private final SettingsRepository settingsRepository;

	private final SettingsEntityToSettingsMapper settingsEntityToSettingsMapper = SettingsEntityToSettingsMapper.initialize();

	public List<Settings> getSettings(String groupName, String name) {

		List<SettingsEntity> settings = settingsRepository.findAllByGroupName(groupName);

		if (CollectionUtils.isEmpty(settings)) {
			throw new SettingsNotFoundByGroupNameException(groupName);
		}

		return settingsEntityToSettingsMapper.map(settings);
	}

}

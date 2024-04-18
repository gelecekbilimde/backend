package org.gelecekbilimde.scienceplatform.settings.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.settings.dto.domain.SettingsDomain;
import org.gelecekbilimde.scienceplatform.settings.mapper.SettingsModelToSettingsDomainMapper;
import org.gelecekbilimde.scienceplatform.settings.model.Settings;
import org.gelecekbilimde.scienceplatform.settings.repository.SettingsRepository;
import org.gelecekbilimde.scienceplatform.settings.service.SettingsService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class SettingsServiceImpl implements SettingsService {

	private final SettingsRepository settingsRepository;

	private static final SettingsModelToSettingsDomainMapper settingsModelToSettingsDomainMapper = SettingsModelToSettingsDomainMapper.initialize();


	public List<SettingsDomain> getSettings(String groupName, @Nullable String name) {
		List<Settings> settings = settingsRepository.getByGroupName(groupName)
			.orElseThrow(() -> new ClientException("Settings not found"));

		return settingsModelToSettingsDomainMapper.map(settings);
	}
}

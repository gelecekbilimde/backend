package org.gelecekbilimde.scienceplatform.settings.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.settings.dto.domain.SettingsDomain;
import org.gelecekbilimde.scienceplatform.settings.model.Settings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingsModelToSettingsDomainMapper extends BaseMapper<Settings, SettingsDomain> {
	@Override
    SettingsDomain map(Settings settings);

	static SettingsModelToSettingsDomainMapper initialize() {
		return Mappers.getMapper(SettingsModelToSettingsDomainMapper.class);
	}
}

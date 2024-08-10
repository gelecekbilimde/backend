package org.gelecekbilimde.scienceplatform.settings.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.settings.model.Settings;
import org.gelecekbilimde.scienceplatform.settings.model.entity.SettingsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingsEntityToSettingsMapper extends BaseMapper<SettingsEntity, Settings> {

	static SettingsEntityToSettingsMapper initialize() {
		return Mappers.getMapper(SettingsEntityToSettingsMapper.class);
	}

}

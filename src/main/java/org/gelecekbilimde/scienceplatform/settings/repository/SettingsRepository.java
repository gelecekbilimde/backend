package org.gelecekbilimde.scienceplatform.settings.repository;

import org.gelecekbilimde.scienceplatform.settings.model.entity.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SettingsRepository extends JpaRepository<SettingsEntity, Long> {

	Optional<List<SettingsEntity>> getByGroupName(String groupName);

}

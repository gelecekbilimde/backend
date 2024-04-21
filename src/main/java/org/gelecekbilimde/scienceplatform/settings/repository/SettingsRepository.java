package org.gelecekbilimde.scienceplatform.settings.repository;

import org.gelecekbilimde.scienceplatform.settings.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {

	Optional<List<Settings>> getByGroupName(String groupName);

	Optional<Settings> getByName(String name);
}

package org.gelecekbilimde.scienceplatform.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.auth.port.RoleReadPort;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class RoleAdapter implements RoleReadPort {

	private final RoleRepository roleRepository;


	private final RoleEntityToDomainMapper roleEntityToDomainMapper = RoleEntityToDomainMapper.initialize();


	@Override
	public Optional<Role> findByName(RoleName name) {
		return roleRepository.findByName(name.name())
			.map(roleEntityToDomainMapper::map);
	}

}

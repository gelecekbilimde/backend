package org.gelecekbilimde.scienceplatform.auth.port;

import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;

import java.util.Optional;

public interface RoleReadPort {

	Optional<Role> findByName(RoleName name);

}

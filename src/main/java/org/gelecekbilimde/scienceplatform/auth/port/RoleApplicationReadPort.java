package org.gelecekbilimde.scienceplatform.auth.port;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplicationFilter;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;

import java.util.Optional;

public interface RoleApplicationReadPort {

	BasePage<RoleApplication> findAll(BasePageable basePageable, RoleApplicationFilter filter);

	Optional<RoleApplication> findById(String id);

	Optional<RoleApplication> findByUserIdAndStatus(String userId, RoleApplicationStatus status);

	boolean existsByUserIdAndStatus(String userId, RoleApplicationStatus status);

}

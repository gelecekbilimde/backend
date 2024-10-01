package org.gelecekbilimde.scienceplatform.auth.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.EnumUtils;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleChangeStatusNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleChangeStatus;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class RoleChangeSpecification {
	public static Specification<RoleApplicationEntity> columnEqual(List<RoleChangeRequestsFilter> filters) {

		return new Specification<RoleApplicationEntity>() {

			@Serial
			private static final long serialVersionUID = -7913990347324068827L;

			@Override
			public Predicate toPredicate(Root<RoleApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				for (RoleChangeRequestsFilter filter : filters) {
					String columnName = filter.getColumnName();
					Object columnValue = filter.getColumnValue();
					if ("firstName".equalsIgnoreCase(columnName) || "lastName".equalsIgnoreCase(columnName) || "email".equalsIgnoreCase(columnName)) {
						Join<RoleApplicationEntity, UserEntity> userJoin = root.join("user");
						predicates.add(criteriaBuilder.equal(userJoin.get(columnName), columnValue));
					}else if ("status".equalsIgnoreCase(columnName)) {
						if (EnumUtils.isValidEnum(RoleChangeStatus.class, columnValue.toString())) {
							RoleChangeStatus statusEnum = RoleChangeStatus.valueOf(columnValue.toString());
							predicates.add(criteriaBuilder.equal(root.get("status"), statusEnum));
						} else {
							throw new RoleChangeStatusNotFoundException(columnValue.toString());
						}
					}else if ("requestRoleName".equalsIgnoreCase(columnName)){
						Join<RoleApplicationEntity, RoleEntity> roleJoin = root.join("role");
						predicates.add(criteriaBuilder.equal(roleJoin.get("name"),columnValue));
					}
					else {
						predicates.add(criteriaBuilder.equal(root.get(columnName), columnValue));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

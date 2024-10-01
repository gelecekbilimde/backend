package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationsResponse;
import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RoleApplicationDomainToRoleApplicationResponse extends BaseMapper<RoleApplication, RoleApplicationsResponse> {
	default List<RoleApplicationsResponse> toRoleApplicationResponseList(List<RoleApplication> roleApplications) {
		return roleApplications.stream().map(roleApplication -> {
			return RoleApplicationsResponse.builder()
				.id(roleApplication.getId())
				.requestRoleName(roleApplication.getRole().getName())
				.firstName(roleApplication.getUser().getFirstName())
				.lastName(roleApplication.getUser().getLastName())
				.email(roleApplication.getUser().getEmail())
				.status(roleApplication.getStatus().name())
				.build();
		}).collect(Collectors.toList());
	}
	static RoleApplicationDomainToRoleApplicationResponse initialize(){
		return Mappers.getMapper(RoleApplicationDomainToRoleApplicationResponse.class);
	}

}

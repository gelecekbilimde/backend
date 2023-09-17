package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.AdminPostListDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminPostListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPostListDomainToResponseMapper extends BaseMapper<AdminPostListDomain, AdminPostListResponse> {

	static AdminPostListDomainToResponseMapper initialize() {
		return Mappers.getMapper(AdminPostListDomainToResponseMapper.class);
	}
}

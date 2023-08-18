package org.gelecekbilimde.scienceplatform.mapper;


import org.gelecekbilimde.scienceplatform.dto.post.Domain.AdminPostListDomainDTO;
import org.gelecekbilimde.scienceplatform.dto.post.Response.AdminPostListResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainToResponseMapper extends BaseMapper<AdminPostListDomainDTO, AdminPostListResponseDTO> {

	static DomainToResponseMapper initialize() {
		return Mappers.getMapper(DomainToResponseMapper.class);
	}
}

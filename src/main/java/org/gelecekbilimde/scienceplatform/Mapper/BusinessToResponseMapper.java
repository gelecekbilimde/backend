package org.gelecekbilimde.scienceplatform.Mapper;


import org.gelecekbilimde.scienceplatform.dto.Post.Business.AdminPostListBusinessDTO;
import org.gelecekbilimde.scienceplatform.dto.Post.Response.AdminPostListResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusinessToResponseMapper extends BaseMapper<AdminPostListBusinessDTO, AdminPostListResponseDTO> {

	static BusinessToResponseMapper initialize() {
		return Mappers.getMapper(BusinessToResponseMapper.class);
	}
}

/**
 * 
 */
package com.radzol.host.web.authentication;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.radzol.host.model.User;
import com.radzol.host.security.UserAuthentication;

/**
 * @author pradeepan
 *
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper {
	
	UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

	@Mapping(target = "username", source="email")
	@Mapping(target = "tenantAlias", expression = "java(entity.getCompany()!=null?entity.getCompany().getTenantAlias():null)")
	UserDto toDto(User entity);
	
	UserDto toDto(UserAuthentication entity);
}

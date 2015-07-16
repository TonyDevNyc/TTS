package com.target.trak.system.security.context;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.target.trak.system.security.entity.Role;
import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.dto.security.RoleDto;
import com.target.trak.system.service.dto.security.UserDto;

public class UserContextImpl implements UserContext {

	private ConversionService conversionService;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getUserRoles() {
		List<Role> roles = (List<Role>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return convertRoleList(roles);
	}

	@Override
	public UserDto getCurrentUser() {
		User targetTrakUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return conversionService.convert(targetTrakUser, UserDto.class);
	}

	@Override
	public String getDisplayName() {
		UserDto user = getCurrentUser();
		StringBuilder builder = new StringBuilder();
		builder.append(user.getFirstName()).append(" ").append(user.getLastName());
		return builder.toString();
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	private List<RoleDto> convertRoleList(final List<Role> roles) {
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		for (Role role : roles) {
			roleDtos.add(conversionService.convert(role, RoleDto.class));
		}
		return roleDtos;
	}

}

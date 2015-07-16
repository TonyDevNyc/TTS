package com.target.trak.system.security.context;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.target.trak.system.service.dto.security.RoleDto;
import com.target.trak.system.service.dto.security.UserDto;

public interface UserContext {

	public Authentication getAuthentication();

	public List<RoleDto> getUserRoles();

	public UserDto getCurrentUser();
	
	public String getDisplayName();
}

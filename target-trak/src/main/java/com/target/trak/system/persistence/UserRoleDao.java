package com.target.trak.system.persistence;

import java.util.List;

import com.target.trak.system.security.entity.Role;

public interface UserRoleDao {

	public List<Role> getUserRoles(final String username);
}

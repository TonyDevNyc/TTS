package com.target.trak.system.persistence;

import java.util.List;

import com.target.trak.system.security.entity.Privilege;

public interface RolePrivilegesDao {

	public List<Privilege> getPrivilegesByRoles(final List<Long> roleIdList);

	public List<Privilege> getPrivilegesByRoleId(final Long roleId);
}

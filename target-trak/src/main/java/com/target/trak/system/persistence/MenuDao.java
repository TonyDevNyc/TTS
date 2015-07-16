package com.target.trak.system.persistence;

import java.util.List;

import com.target.trak.system.security.entity.Menu;

public interface MenuDao {

	public List<Menu> selectMenuItemsByPrivileges(final List<Long> privilegeIds);
}

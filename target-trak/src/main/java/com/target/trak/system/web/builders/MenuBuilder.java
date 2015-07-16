package com.target.trak.system.web.builders;

import java.util.List;

import com.target.trak.system.service.dto.security.menu.MenuDto;
import com.target.trak.system.web.views.MenuItem;

public interface MenuBuilder {

	public List<MenuItem> buildUserMenu(final List<MenuDto> permissableMenuItems);
}

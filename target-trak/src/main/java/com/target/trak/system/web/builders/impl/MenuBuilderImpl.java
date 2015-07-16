package com.target.trak.system.web.builders.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.target.trak.system.service.dto.security.menu.MenuDto;
import com.target.trak.system.web.builders.MenuBuilder;
import com.target.trak.system.web.views.MenuItem;

public class MenuBuilderImpl implements MenuBuilder {

	@Override
	public List<MenuItem> buildUserMenu(final List<MenuDto> permissableMenuItems) {
		return buildMenuItems(permissableMenuItems);
	}

	private List<MenuItem> buildMenuItems(final List<MenuDto> permissableMenuItems) {
		List<MenuItem> parentMenuItems = new ArrayList<MenuItem>();
		
		if (permissableMenuItems == null || permissableMenuItems.isEmpty()) {
			return parentMenuItems;
		}

		for (MenuDto permissableMenuItem : permissableMenuItems) {
			if (permissableMenuItem.getParentMenuId() == 0L) {
				MenuItem parentMenuItem = buildMenuItem(permissableMenuItem);
				Long parentId = parentMenuItem.getId();
				parentMenuItem.setChildMenuItems(buildChildMenuItems(parentId, permissableMenuItems));
				parentMenuItems.add(parentMenuItem);
			}
		}
		Collections.sort(parentMenuItems);
		return parentMenuItems;
	}
	
	private MenuItem buildMenuItem(final MenuDto menuDto) {
		MenuItem menuItem = new MenuItem();
		menuItem.setId(menuDto.getMenuId());
		menuItem.setName(menuDto.getText());
		menuItem.setDisplayOrder(menuDto.getDisplayOrder());
		menuItem.setParentId(menuDto.getParentMenuId());
		menuItem.setLink(menuDto.getLink());
		return menuItem;
	}
	
	private List<MenuItem> buildChildMenuItems(final Long parentId, List<MenuDto> permissableItems) {
		List<MenuItem> childMenuItems = new ArrayList<MenuItem>();
		
		for (MenuDto menuDto : permissableItems) {
			if (menuDto.getParentMenuId() == parentId) {
				childMenuItems.add(buildMenuItem(menuDto));
			}
		}
		Collections.sort(childMenuItems);
		return childMenuItems;
	}
}
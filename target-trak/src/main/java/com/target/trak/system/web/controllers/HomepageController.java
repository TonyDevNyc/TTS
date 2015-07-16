package com.target.trak.system.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.MenuService;
import com.target.trak.system.service.dto.security.UserDto;
import com.target.trak.system.service.dto.security.menu.MenuApiRequest;
import com.target.trak.system.service.dto.security.menu.MenuApiResponse;
import com.target.trak.system.web.builders.MenuBuilder;
import com.target.trak.system.web.views.MenuItem;
import com.target.trak.system.web.views.TargetTrakUserDetails;

@Controller
@SessionAttributes("userDetails")
public class HomepageController {

	private Logger logger = Logger.getLogger(getClass());

	private static final String INDEX_PAGE = "newProcessService";
	
	private MenuService menuService;
	
	private UserContext userContext;
	
	private MenuBuilder menuBuilder;

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView showHomepage(HttpSession session) {
		MenuApiRequest apiRequest = new MenuApiRequest();
		UserDto user = userContext.getCurrentUser();
		apiRequest.setCurrentUser(user);
		
		MenuApiResponse apiResponse = menuService.getMenuItemsForUser(apiRequest);
		List<MenuItem> menuItems = menuBuilder.buildUserMenu(apiResponse.getMenuItems());
		
		TargetTrakUserDetails userDetails = new TargetTrakUserDetails();
		userDetails.setDisplayName(userContext.getDisplayName());
		userDetails.setMenuItems(menuItems);
		
		session.setAttribute("userDetails", userDetails);
		
		logger.info("Forwarding to homescreen");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(INDEX_PAGE);
		mav.addObject("userDetails", userDetails);
		return mav;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public void setMenuBuilder(MenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
	}
}
package com.target.trak.system.web.controllers.security;

import org.apache.log4j.Logger;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.target.trak.system.security.entity.User;

@Controller
public class LogoutController {

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	public String handleLogoutRequest(@AuthenticationPrincipal User user) {
		logger.info("Logging out " + user.getUsername() + " of Target-Trak system");
		return "login";
	}
}

package com.target.trak.system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProcessServiceController {

	@RequestMapping(value = {"/newProcessService.htm"}, method = RequestMethod.GET)
	public String showProcessServiceScreen() {
		return "newProcessService";
	}
}

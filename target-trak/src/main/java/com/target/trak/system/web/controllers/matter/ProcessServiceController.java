package com.target.trak.system.web.controllers.matter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProcessServiceController {

	private static final String CREATE_PROCESS_SERVICE_VIEW = "matter/createProcessService";
	
	@RequestMapping(value = "/showCreateProcessService.htm", method = RequestMethod.GET)
	public ModelAndView showCreateProcessServiceScreen() {
		return new ModelAndView(CREATE_PROCESS_SERVICE_VIEW);
	}
	
}

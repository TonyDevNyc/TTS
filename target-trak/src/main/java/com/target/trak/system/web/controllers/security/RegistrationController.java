package com.target.trak.system.web.controllers.security;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.target.trak.system.service.RegistrationService;
import com.target.trak.system.service.dto.security.UserDto;
import com.target.trak.system.service.dto.security.registration.RegistrationApiRequest;
import com.target.trak.system.service.dto.security.registration.RegistrationApiResponse;
import com.target.trak.system.web.forms.UserRegistrationForm;
import com.target.trak.system.web.handlers.ExceptionHandler;

@Controller
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	private ExceptionHandler targetTrakExceptionHandler;

	@RequestMapping(value="/registration.htm", method=RequestMethod.GET)
	public ModelAndView showLoginScreen() {
		return new ModelAndView("registration/registration", "userRegistrationForm", new UserRegistrationForm());
	}
	
	@RequestMapping(value = "/registerNewUser.htm", method = RequestMethod.POST)
	public String registerNewUser(@ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		RegistrationApiResponse response = registrationService.registerUser(buildRegistrationApiRequest(userRegistrationForm));
		
		if (response.isSuccess()) {
			return "redirect:/registrationComplete.htm";
		} 
		else {
			targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "userRegistrationForm");
			model.addAttribute("errorMessage", response.getMessage());
			model.addAttribute("userRegistrationForm", userRegistrationForm);
			return "registration/registration";
		}
	}
	
	@RequestMapping(value = "/registrationComplete.htm", method = RequestMethod.GET)
	public String registrationComplete() {
		return "registration/registrationComplete";
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	public void setTargetTrakExceptionHandler(ExceptionHandler targetTrakExceptionHandler) {
		this.targetTrakExceptionHandler = targetTrakExceptionHandler;
	}

	private RegistrationApiRequest buildRegistrationApiRequest(final UserRegistrationForm form) {
		RegistrationApiRequest request = new RegistrationApiRequest();
		UserDto user = new UserDto();
		user.setEmail(form.getEmail());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setTelephoneNumber(form.getTelephoneNumber());
		user.setUsername(form.getUsername());
		user.setPassword(form.getPassword());
		user.setRepeatedPassword(form.getRepeatedPassword());
		user.setEnabled(true);
		user.setRegistrationDate(Calendar.getInstance());
		request.setUserRegistration(user);
		return request;
	}
}
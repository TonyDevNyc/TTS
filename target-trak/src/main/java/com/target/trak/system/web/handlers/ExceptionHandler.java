package com.target.trak.system.web.handlers;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.target.trak.system.service.dto.common.TargetTrakApiResponse;
import com.target.trak.system.validations.TargetTrakValidationError;

public interface ExceptionHandler {

	public void bindValidationErrors(final List<TargetTrakValidationError> validationErrors, final BindingResult result, String formName);
	
	public String bindErrorMessage(final String errorCode);
	
	public String bindValidationErrorMessage(final TargetTrakApiResponse apiResponse);
}

package com.target.trak.system.web.handlers.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.target.trak.system.service.dto.common.TargetTrakApiResponse;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.web.handlers.ExceptionHandler;

public class TargetTrakExceptionHandlerImpl implements ExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	public void bindValidationErrors(List<TargetTrakValidationError> validationErrors, BindingResult result, String formName) {
		if (validationErrors == null || validationErrors.isEmpty()) {
			return;
		}
		
		FieldError fieldError = null;
		for (TargetTrakValidationError validationError : validationErrors) {
			String errorMsg = bindErrorMessage(validationError.getErrorMessage());
			fieldError = new FieldError(formName, validationError.getFieldName(), errorMsg);
			result.addError(fieldError);
		}
	}

	@Override
	public String bindErrorMessage(String errorCode) {
		if (StringUtils.isEmpty(errorCode)) {
			return "";
		}
		return messageSource.getMessage(errorCode, new Object[] {}, Locale.US);
	}

	@Override
	public String bindValidationErrorMessage(final TargetTrakApiResponse apiResponse) {
		String apiErrorCode = getApiError(apiResponse.getErrors());

		if (apiErrorCode == null) {
			return apiResponse.getMessage();
		} else {
			return bindErrorMessage(apiErrorCode);
		}
	}
	
	private String getApiError(final List<TargetTrakValidationError> errors) {
		if (errors == null || errors.isEmpty()) {
			return null;
		}
		
		for (TargetTrakValidationError error : errors) {
			if ("api".equals(error.getFieldName())) {
				return error.getErrorMessage();
			}
		}
		return null;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
package com.target.trak.system.service;

import java.util.List;

import com.target.trak.system.service.dto.security.menu.MenuApiRequest;
import com.target.trak.system.service.dto.security.menu.MenuApiResponse;
import com.target.trak.system.validations.TargetTrakValidationError;

public interface MenuService {

	public List<TargetTrakValidationError> validateRequest(final MenuApiRequest request);
	
	public MenuApiResponse getMenuItemsForUser(final MenuApiRequest request);
}

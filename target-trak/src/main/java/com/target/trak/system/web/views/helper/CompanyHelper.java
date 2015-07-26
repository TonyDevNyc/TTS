package com.target.trak.system.web.views.helper;

import java.util.List;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.dto.company.CompanySearchCriteriaDto;
import com.target.trak.system.web.forms.SearchCompanyForm;
import com.target.trak.system.web.views.CompanyItem;

public interface CompanyHelper {

	public CompanySearchCriteriaDto buildSearchCriteria(final SearchCompanyForm form);
	
	public CompanyItem buildCompanyItem(final CompanyDto company);
	
	public List<CompanyItem> buildCompaniesList(final List<CompanyDto> companies);
	
	public CompanyItem getCompanyById(final long id);
	
	public CompanyDto buildCompanyDto(final CompanyItem company, final User user);
}

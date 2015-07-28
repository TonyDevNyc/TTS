package com.target.trak.system.web.views.helper.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.CompanyService;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.dto.company.CompanySearchCriteriaDto;
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.forms.SearchCompanyForm;
import com.target.trak.system.web.views.CompanyItem;
import com.target.trak.system.web.views.helper.CompanyHelper;

public class CompanyHelperImpl implements CompanyHelper {

	private CompanyService companyService;

	@Override
	public CompanySearchCriteriaDto buildSearchCriteria(final SearchCompanyForm form) {
		CompanySearchCriteriaDto searchCriteria = new CompanySearchCriteriaDto();
		searchCriteria.setText(form.getText());

		int page = form.getPage();
		searchCriteria.setPage(page == 0 ? 1 : page);
		int start = form.getStart();
		searchCriteria.setStart(start);
		searchCriteria.setEnd(10);
		return searchCriteria;
	}

	@Override
	public CompanyItem buildCompanyItem(final CompanyDto company) {
		CompanyItem item = new CompanyItem();
		item.setId(company.getId());
		item.setName(company.getName());
		item.setAddressLine1(company.getAddressLine1());
		item.setAddressLine2(company.getAddressLine2());
		item.setCity(company.getCity());
		item.setState(company.getState());
		item.setZipcode(company.getZipcode());
		item.setCountry(company.getCountry());
		item.setVersion(company.getVersion());
		item.setCreatedBy(company.getCreatedBy());
		item.setCreatedDate(DateUtil.convertDateToIso8601(company.getCreatedDate()));
		item.setLastUpdatedBy(company.getLastUpdatedBy());
		item.setLastUpdatedDate(DateUtil.convertDateToIso8601(company.getLastUpdatedDate()));
		item.setUrl("/target-trak/viewCompany.htm?id=" + company.getId());
		return item;
	}

	@Override
	public List<CompanyItem> buildCompaniesList(final List<CompanyDto> companies) {
		List<CompanyItem> list = new ArrayList<CompanyItem>();
		if (companies == null || companies.isEmpty()) {
			return list;
		}

		for (CompanyDto company : companies) {
			list.add(buildCompanyItem(company));
		}
		return list;
	}

	@Override
	public CompanyItem getCompanyById(final long id) {
		CompanyApiRequest request = new CompanyApiRequest();
		CompanyDto company = new CompanyDto();
		company.setId(id);
		request.setCompany(company);

		CompanyApiResponse response = companyService.getCompanyById(request);
		if (response.getCompany() != null)
			return buildCompanyItem(response.getCompany());
		else
			return null;
	}

	@Override
	public CompanyDto buildCompanyDto(CompanyItem company, User user) {
		CompanyDto dto = new CompanyDto();
		dto.setId(company.getId());
		dto.setName(company.getName());
		dto.setAddressLine1(company.getAddressLine1());
		dto.setAddressLine2(company.getAddressLine2());
		dto.setCity(company.getCity());
		dto.setState(company.getState());
		dto.setZipcode(company.getZipcode());
		dto.setCountry(company.getCountry());
		dto.setVersion(company.getVersion());
		dto.setLastUpdatedBy(user.getUsername());
		Calendar calendar = Calendar.getInstance();
		dto.setLastUpdatedDate(calendar);
		dto.setCreatedBy(user.getUsername());
		dto.setCreatedDate(calendar);
		return dto;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

}

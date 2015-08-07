package com.target.trak.system.web.views.helper.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.ContactService;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.dto.contact.ContactApiRequest;
import com.target.trak.system.service.dto.contact.ContactApiResponse;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.service.dto.contact.ContactSearchCriteriaDto;
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.forms.SearchContactForm;
import com.target.trak.system.web.views.ContactItem;
import com.target.trak.system.web.views.helper.ContactViewHelper;

public class ContactViewHelperImpl implements ContactViewHelper {

	private static final String ACTIVE = "Active";
	private static final String INACTIVE = "In-active";
	
	private ContactService contactService;
	
	@Override
	public ContactSearchCriteriaDto buildSearchCriteria(SearchContactForm form) {
		ContactSearchCriteriaDto searchCriteria = new ContactSearchCriteriaDto();
		searchCriteria.setText(form.getText());
		
		int page = form.getPage();
		searchCriteria.setPage(page == 0 ? 1 : page);
		int start = form.getStart();
		searchCriteria.setStart(start);
		searchCriteria.setEnd(10);
		return searchCriteria;
	}
	
	@Override
	public ContactItem buildContactItem(ContactDto contact) {
		ContactItem item = new ContactItem();
		item.setId(contact.getId());
		item.setTitle(contact.getTitle());
		item.setFirstName(contact.getFirstName());
		item.setLastName(contact.getLastName());
		item.setSuffix(contact.getSuffix());
		item.setMiddleInitial(contact.getMiddleInitial());
		item.setContactType(contact.getContactType());
		item.setTelephoneNumber(contact.getTelephoneNumber());
		item.setEmail(contact.getEmailAddress());
		item.setCompanyName(contact.getCompany().getName());
		item.setCompany(contact.getCompany().getId());
		item.setActiveAtCompany(contact.isActiveAtCompany() ? ACTIVE : INACTIVE);
		item.setCreatedDate(DateUtil.convertDateToIso8601(contact.getCreatedDate()));
		item.setCreatedBy(contact.getCreatedBy());
		item.setLastUpdatedBy(contact.getLastUpdatedBy());
		item.setLastUpdatedDate(DateUtil.convertDateToIso8601(contact.getLastUpdatedDate()));
		item.setVersion(contact.getVersion());
		item.setUrl("/target-trak/viewContact.htm?id=" + contact.getId());
		item.setDisplayName(buildDisplayName(contact));
		item.setVersion(contact.getVersion());
		return item;
	}
	
	private String buildDisplayName(ContactDto contact) {
		StringBuilder builder = new StringBuilder();
		if (!StringUtils.isEmpty(contact.getTitle())) {
			builder.append(contact.getTitle()).append(" ");
		}
		
		builder.append(contact.getFirstName()).append(" ");
		
		if (!StringUtils.isEmpty(contact.getMiddleInitial())) {
			builder.append(contact.getMiddleInitial()).append(" ");
		}
		
		builder.append(contact.getLastName()).append(" ");
		
		if (!StringUtils.isEmpty(contact.getSuffix())) {
			builder.append(contact.getSuffix());
		}
		return builder.toString();
	}

	@Override
	public List<ContactItem> buildContactsList(List<ContactDto> contacts) {
		List<ContactItem> list = new ArrayList<ContactItem>();
		if (contacts == null || contacts.isEmpty()) {
			return list;
		}
		
		for (ContactDto contact : contacts) {
			list.add(buildContactItem(contact));
		}
		return list;
	}

	@Override
	public ContactItem getContactById(long id) {
		ContactApiRequest request = new ContactApiRequest();
		ContactDto dto = new ContactDto();
		dto.setId(id);
		request.setContact(dto);
		ContactApiResponse response = contactService.getContactById(request);
		if (response.isSuccess()) {
			return buildContactItem(response.getContact());
		}
		return null;
	}

	@Override
	public ContactDto buildContactDto(ContactItem contact, User user) {
		ContactDto dto = new ContactDto();
		dto.setId(contact.getId());
		dto.setTitle(contact.getTitle());
		dto.setFirstName(contact.getFirstName());
		dto.setLastName(contact.getLastName());
		dto.setSuffix(contact.getSuffix());
		dto.setMiddleInitial(contact.getMiddleInitial());
		dto.setContactType(contact.getContactType());
		dto.setTelephoneNumber(contact.getTelephoneNumber());
		dto.setEmailAddress(contact.getEmail());
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(contact.getCompany());
		companyDto.setName(contact.getCompanyName());
		dto.setCompany(companyDto);
		dto.setActiveAtCompany(ACTIVE.equals(contact.getActiveAtCompany()) ? true : false);
		Calendar calendar = Calendar.getInstance();
		dto.setCreatedDate(calendar);
		dto.setCreatedBy(user.getUsername());
		dto.setLastUpdatedBy(user.getUsername());
		dto.setLastUpdatedDate(calendar);
		dto.setVersion(contact.getVersion());
		return dto;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

}
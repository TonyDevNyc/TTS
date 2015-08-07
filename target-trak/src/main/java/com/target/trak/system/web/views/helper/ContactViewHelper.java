package com.target.trak.system.web.views.helper;

import java.util.List;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.service.dto.contact.ContactSearchCriteriaDto;
import com.target.trak.system.web.forms.SearchContactForm;
import com.target.trak.system.web.views.ContactItem;

public interface ContactViewHelper {

	public ContactSearchCriteriaDto buildSearchCriteria(final SearchContactForm form);
	
	public ContactItem buildContactItem(final ContactDto contact);
	
	public List<ContactItem> buildContactsList(final List<ContactDto> contacts);
	
	public ContactItem getContactById(final long id);
	
	public ContactDto buildContactDto(final ContactItem contact, final User user);
}

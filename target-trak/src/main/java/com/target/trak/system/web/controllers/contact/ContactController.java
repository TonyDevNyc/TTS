package com.target.trak.system.web.controllers.contact;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.ContactService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.contact.ContactApiRequest;
import com.target.trak.system.service.dto.contact.ContactApiResponse;
import com.target.trak.system.web.builders.PaginationBuilder;
import com.target.trak.system.web.forms.SearchContactForm;
import com.target.trak.system.web.handlers.ExceptionHandler;
import com.target.trak.system.web.views.ContactItem;
import com.target.trak.system.web.views.PaginationBean;
import com.target.trak.system.web.views.helper.CompanyHelper;
import com.target.trak.system.web.views.helper.ContactViewHelper;
import com.target.trak.system.web.views.helper.ReferenceDataHelper;

@SessionAttributes("searchContactForm")
@Controller
public class ContactController {

	private static final String SEARCH_CONTACT_VIEW = "contactgroup/searchContact";
	private static final String VIEW_CONTACT_VIEW = "contactgroup/viewContact";
	private static final String EDIT_CONTACT_VIEW = "contactgroup/editContact";
	private static final String CREATE_CONTACT_VIEW = "contactgroup/createContact";
	private static final String PAGINATION_LINK = "getPagedContacts.htm";

	private ContactService contactService;
	private ContactViewHelper contactViewHelper;
	private PaginationBuilder pagingBuilder;
	private ExceptionHandler targetTrakExceptionHandler;
	private ReferenceDataHelper referenceDataViewHelper;
	private CompanyHelper companyViewHelper;

	@RequestMapping(value = "/showContactSearch.htm", method = RequestMethod.GET)
	public ModelAndView showContactSearchScreen() {
		ContactApiRequest request = new ContactApiRequest();
		SearchContactForm searchContactForm = new SearchContactForm();
		request.setSearchCriteria(contactViewHelper.buildSearchCriteria(searchContactForm));

		ContactApiResponse response = contactService.getContactsByCriteria(request);
		List<ContactItem> contacts = contactViewHelper.buildContactsList(response.getContacts());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);

		ModelAndView mav = new ModelAndView(SEARCH_CONTACT_VIEW, "searchContactForm", searchContactForm);
		mav.addObject("contactsList", contacts);
		mav.addObject("pagingList", paginationBean.getPagesDisplayed());
		return mav;
	}

	@RequestMapping(value = "/searchContactByCriteria.htm", method = RequestMethod.POST)
	public String searchContactByCriteria(@ModelAttribute("searchContactForm") SearchContactForm searchContactForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		ContactApiRequest request = new ContactApiRequest();
		request.setSearchCriteria(contactViewHelper.buildSearchCriteria(searchContactForm));

		ContactApiResponse response = contactService.getContactsByCriteria(request);
		List<ContactItem> contacts = contactViewHelper.buildContactsList(response.getContacts());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
		model.addAttribute("contactsList", contacts);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchContactForm", searchContactForm);
		return SEARCH_CONTACT_VIEW;
	}

	@RequestMapping(value = "/viewContact.htm", method = RequestMethod.GET)
	public ModelAndView viewContactById(@RequestParam Long id) {
		ContactItem contactItem = contactViewHelper.getContactById(id);
		return new ModelAndView(VIEW_CONTACT_VIEW, "contactItem", contactItem);
	}

	@RequestMapping(value = "/getPagedContacts.htm", method = RequestMethod.GET)
	public ModelAndView getPagedContacts(@ModelAttribute("searchContactForm") SearchContactForm searchContactForm, @RequestParam int page, @RequestParam int start) {
		searchContactForm.setPage(page);
		searchContactForm.setStart(start);

		ContactApiRequest request = new ContactApiRequest();
		request.setSearchCriteria(contactViewHelper.buildSearchCriteria(searchContactForm));

		ContactApiResponse response = contactService.getContactsByCriteria(request);
		ModelAndView mav = new ModelAndView(SEARCH_CONTACT_VIEW, "searchContactForm", new SearchContactForm());

		if (response.isSuccess()) {
			List<ContactItem> contacts = contactViewHelper.buildContactsList(response.getContacts());
			PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
			mav.addObject("contactsList", contacts);
			mav.addObject("pagingList", paginationBean.getPagesDisplayed());
			mav.addObject("searchContactForm", searchContactForm);
		}
		return mav;
	}

	@RequestMapping(value = "/backToContactSearchResults.htm", method = RequestMethod.GET)
	public String backToSearchResults(@ModelAttribute("searchContactForm") SearchContactForm searchContactForm, ModelMap model) {
		ContactApiRequest request = new ContactApiRequest();
		request.setSearchCriteria(contactViewHelper.buildSearchCriteria(searchContactForm));

		ContactApiResponse response = contactService.getContactsByCriteria(request);
		List<ContactItem> contacts = contactViewHelper.buildContactsList(response.getContacts());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
		model.addAttribute("contactsList", contacts);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchContactForm", searchContactForm);
		return SEARCH_CONTACT_VIEW;
	}

	@RequestMapping(value = "/showEditContactItem.htm", method = RequestMethod.GET)
	public ModelAndView showEditContactScreen(@RequestParam long id) {
		ContactItem contactItem = contactViewHelper.getContactById(id);

		ModelAndView mav = new ModelAndView(EDIT_CONTACT_VIEW, "contactItem", contactItem);
		mav.addObject("contactTypes", referenceDataViewHelper.getReferenceDataByType("contact_types"));
		mav.addObject("titles", referenceDataViewHelper.getReferenceDataByType("titles"));
		mav.addObject("suffixes", referenceDataViewHelper.getReferenceDataByType("suffixes"));
		mav.addObject("statuses", referenceDataViewHelper.getReferenceDataByType("status"));
		return mav;
	}

	@RequestMapping(value = "/cancelEditContact.htm", method = RequestMethod.POST)
	public String cancelUpdateReferenceData(@ModelAttribute("contactItem") ContactItem contactItem, RedirectAttributes attributes) {
		attributes.addAttribute("id", contactItem.getId());
		return "redirect:/viewContact.htm";
	}

	@RequestMapping(value = "/updateContact.htm", method = RequestMethod.POST)
	public String updateContact(@ModelAttribute("contactItem") ContactItem contactItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		ContactApiRequest request = new ContactApiRequest();
		request.setContact(contactViewHelper.buildContactDto(contactItem, user));

		ContactApiResponse response = contactService.updateContact(request);

		if (response.isSuccess()) {
			model.addAttribute("message", "Contact was updated successfully.");
			model.addAttribute("contactItem", contactViewHelper.getContactById(contactItem.getId()));
		} else {
			if (TargetTrakErrorTypeEnum.VALIDATION == response.getErrorType()) {
				targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "contactItem");
				model.addAttribute("contactItem", contactItem);
			} else if (TargetTrakErrorTypeEnum.ERROR == response.getErrorType()) {
				model.addAttribute("contactItem", contactViewHelper.getContactById(contactItem.getId()));
			}
			model.addAttribute("errorMessage", response.getMessage());
		}
		model.addAttribute("contactTypes", referenceDataViewHelper.getReferenceDataByType("contact_types"));
		model.addAttribute("titles", referenceDataViewHelper.getReferenceDataByType("titles"));
		model.addAttribute("suffixes", referenceDataViewHelper.getReferenceDataByType("suffixes"));
		model.addAttribute("statuses", referenceDataViewHelper.getReferenceDataByType("status"));
		return EDIT_CONTACT_VIEW;
	}

	@RequestMapping(value = "/showCreateContact.htm", method = RequestMethod.GET)
	public ModelAndView showCreateContactScreen() {
		ModelAndView mav = new ModelAndView(CREATE_CONTACT_VIEW, "contactItem", new ContactItem());
		mav.addObject("companies", companyViewHelper.getListOfAllCompanies());
		mav.addObject("contactTypes", referenceDataViewHelper.getReferenceDataByType("contact_types"));
		mav.addObject("titles", referenceDataViewHelper.getReferenceDataByType("titles"));
		mav.addObject("suffixes", referenceDataViewHelper.getReferenceDataByType("suffixes"));
		mav.addObject("statuses", referenceDataViewHelper.getReferenceDataByType("status"));
		return mav;
	}

	@RequestMapping(value = "/createContact.htm", method = RequestMethod.POST)
	public String createContact(@ModelAttribute("contactItem") ContactItem contactItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		ContactApiRequest request = new ContactApiRequest();
		request.setContact(contactViewHelper.buildContactDto(contactItem, user));

		ContactApiResponse response = contactService.createContact(request);

		if (response.isSuccess()) {
			attributes.addAttribute("id", response.getContact().getId());
			return "redirect:/viewContact.htm";
		}

		targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "contactItem");
		model.addAttribute("errorMessage", response.getMessage());
		model.addAttribute("companies", companyViewHelper.getListOfAllCompanies());
		model.addAttribute("contactTypes", referenceDataViewHelper.getReferenceDataByType("contact_types"));
		model.addAttribute("titles", referenceDataViewHelper.getReferenceDataByType("titles"));
		model.addAttribute("suffixes", referenceDataViewHelper.getReferenceDataByType("suffixes"));
		model.addAttribute("statuses", referenceDataViewHelper.getReferenceDataByType("status"));
		model.addAttribute("contactItem", contactItem);
		return CREATE_CONTACT_VIEW;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public void setContactViewHelper(ContactViewHelper contactViewHelper) {
		this.contactViewHelper = contactViewHelper;
	}

	public void setPagingBuilder(PaginationBuilder pagingBuilder) {
		this.pagingBuilder = pagingBuilder;
	}

	public void setTargetTrakExceptionHandler(ExceptionHandler targetTrakExceptionHandler) {
		this.targetTrakExceptionHandler = targetTrakExceptionHandler;
	}

	public void setReferenceDataViewHelper(ReferenceDataHelper referenceDataViewHelper) {
		this.referenceDataViewHelper = referenceDataViewHelper;
	}

	public void setCompanyViewHelper(CompanyHelper companyViewHelper) {
		this.companyViewHelper = companyViewHelper;
	}
}
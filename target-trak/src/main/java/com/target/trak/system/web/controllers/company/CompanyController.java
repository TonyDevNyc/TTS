package com.target.trak.system.web.controllers.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.target.trak.system.service.CompanyService;
import com.target.trak.system.service.UsersService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.security.UserDto;
import com.target.trak.system.service.dto.security.user.UserApiRequest;
import com.target.trak.system.service.dto.security.user.UserApiResponse;
import com.target.trak.system.web.builders.PaginationBuilder;
import com.target.trak.system.web.forms.SearchCompanyForm;
import com.target.trak.system.web.handlers.ExceptionHandler;
import com.target.trak.system.web.views.CompanyItem;
import com.target.trak.system.web.views.NameValuePair;
import com.target.trak.system.web.views.PaginationBean;
import com.target.trak.system.web.views.helper.CompanyHelper;
import com.target.trak.system.web.views.helper.ReferenceDataHelper;

@SessionAttributes("searchCompanyForm")
@Controller
public class CompanyController {

	//private static final Logger logger = Logger.getLogger(CompanyController.class);
	private static final String SEARCH_COMPANY_SCREEN = "contactgroup/searchCompany";
	private static final String VIEW_COMPANY_SCREEN = "contactgroup/viewCompany";
	private static final String EDIT_COMPANY_SCREEN = "contactgroup/editCompany";
	private static final String CREATE_COMPANY_SCREEN = "contactgroup/createCompany";
	private static final String PAGINATION_LINK = "getPagedCompanies.htm";

	private CompanyService companyService;
	private UsersService userService;
	private PaginationBuilder pagingBuilder;
	private ExceptionHandler targetTrakExceptionHandler;
	private ReferenceDataHelper viewHelper;
	private CompanyHelper companyViewHelper;

	@RequestMapping(value = "/showCompanySearchScreen.htm", method = RequestMethod.GET)
	public ModelAndView showCompanySearchScreen() {
		CompanyApiRequest request = new CompanyApiRequest();
		SearchCompanyForm searchCompanyForm = new SearchCompanyForm();
		request.setCompanySearchCriteria(companyViewHelper.buildSearchCriteria(searchCompanyForm));
		
		CompanyApiResponse response = companyService.getCompaniesByCriteria(request);
		List<CompanyItem> companies = companyViewHelper.buildCompaniesList(response.getCompanies());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
		
		ModelAndView mav = new ModelAndView(SEARCH_COMPANY_SCREEN, "searchCompanyForm", searchCompanyForm);
		mav.addObject("usersList", buildDistinctUsersList());
		mav.addObject("statesList", viewHelper.getReferenceDataByType("states"));
		mav.addObject("citiesList", viewHelper.getReferenceDataByType("cities"));
		mav.addObject("countriesList", viewHelper.getReferenceDataByType("countries"));
		mav.addObject("companiesList", companies);
		mav.addObject("pagingList", paginationBean.getPagesDisplayed());
		return mav;
	}

	@RequestMapping(value = "/searchCompanyByCriteria.htm", method = RequestMethod.POST)
	public String searchCompanyByCriteria(@ModelAttribute("searchCompanyForm") SearchCompanyForm searchCompanyForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompanySearchCriteria(companyViewHelper.buildSearchCriteria(searchCompanyForm));
		
		CompanyApiResponse response = companyService.getCompaniesByCriteria(request);
		List<CompanyItem> companies = companyViewHelper.buildCompaniesList(response.getCompanies());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
		model.addAttribute("usersList", buildDistinctUsersList());
		model.addAttribute("statesList", viewHelper.getReferenceDataByType("states"));
		model.addAttribute("citiesList", viewHelper.getReferenceDataByType("cities"));
		model.addAttribute("countriesList", viewHelper.getReferenceDataByType("countries"));
		model.addAttribute("companiesList", companies);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchCompanyForm", searchCompanyForm);
		return SEARCH_COMPANY_SCREEN;
	}
	
	@RequestMapping(value = "/viewCompany.htm", method = RequestMethod.GET)
	public ModelAndView viewCompanyById(@RequestParam long id) {
		CompanyItem company = companyViewHelper.getCompanyById(id);
		return new ModelAndView(VIEW_COMPANY_SCREEN, "companyItem", company);
	}
	
	@RequestMapping(value = "/getPagedCompanies.htm", method = RequestMethod.GET)
	public ModelAndView getPagedCompanies(HttpSession session, @RequestParam int page, @RequestParam int start) {
		SearchCompanyForm searchCompanyForm = (SearchCompanyForm) session.getAttribute("searchCompanyForm");
		searchCompanyForm.setPage(page);
		searchCompanyForm.setStart(start);

		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompanySearchCriteria(companyViewHelper.buildSearchCriteria(searchCompanyForm));
		CompanyApiResponse response = companyService.getCompaniesByCriteria(request);

		ModelAndView mav = new ModelAndView(SEARCH_COMPANY_SCREEN, "searchCompanyForm", new SearchCompanyForm());
		
		if (response.isSuccess()) {
			List<CompanyItem> companies = companyViewHelper.buildCompaniesList(response.getCompanies());
			PaginationBean paginationBean = pagingBuilder.buildPaginationBean(page, response.getTotalSize(), PAGINATION_LINK);
			mav.addObject("usersList", buildDistinctUsersList());
			mav.addObject("statesList", viewHelper.getReferenceDataByType("states"));
			mav.addObject("citiesList", viewHelper.getReferenceDataByType("cities"));
			mav.addObject("countriesList", viewHelper.getReferenceDataByType("countries"));
			mav.addObject("companiesList", companies);
			mav.addObject("pagingList", paginationBean.getPagesDisplayed());
			mav.addObject("searchCompanyForm", searchCompanyForm);
		}
		return mav;
	}
	
	@RequestMapping(value = "/backToCompanySearchResults.htm", method = RequestMethod.GET)
	public String backToSearchResults(@ModelAttribute("searchCompanyForm") SearchCompanyForm searchCompanyForm, ModelMap model) {
		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompanySearchCriteria(companyViewHelper.buildSearchCriteria(searchCompanyForm));
		CompanyApiResponse response = companyService.getCompaniesByCriteria(request);

		List<CompanyItem> companies = companyViewHelper.buildCompaniesList(response.getCompanies());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(PaginationBuilder.FIRST_PAGE, response.getTotalSize(), PAGINATION_LINK);
		model.addAttribute("usersList", buildDistinctUsersList());
		model.addAttribute("statesList", viewHelper.getReferenceDataByType("states"));
		model.addAttribute("citiesList", viewHelper.getReferenceDataByType("cities"));
		model.addAttribute("countriesList", viewHelper.getReferenceDataByType("countries"));
		model.addAttribute("companiesList", companies);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchCompanyForm", searchCompanyForm);
		return SEARCH_COMPANY_SCREEN;
	}

	@RequestMapping(value = "/showEditCompanyItem.htm", method = RequestMethod.GET)
	public ModelAndView showEditCompanyScreen(@RequestParam long id) {
		CompanyItem company = companyViewHelper.getCompanyById(id);
		ModelAndView mav = new ModelAndView(EDIT_COMPANY_SCREEN, "companyItem", company);
		mav.addObject("usersList", buildDistinctUsersList());
		mav.addObject("statesList", viewHelper.getReferenceDataByType("states"));
		mav.addObject("citiesList", viewHelper.getReferenceDataByType("cities"));
		mav.addObject("countriesList", viewHelper.getReferenceDataByType("countries"));
		return mav;
	}
	
	@RequestMapping(value = "/updateCompany.htm", method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute("companyItem") CompanyItem companyItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompany(companyViewHelper.buildCompanyDto(companyItem, user));

		CompanyApiResponse response = new CompanyApiResponse();
		response = companyService.updateCompany(request);

		if (response.isSuccess()) {
			model.addAttribute("message", "Company was updated successfully.");
			model.addAttribute("companyItem", companyViewHelper.getCompanyById(companyItem.getId()));			
		} 
		else {
			if (TargetTrakErrorTypeEnum.VALIDATION == response.getErrorType()) {
				targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "companyItem");
				model.addAttribute("companyItem", companyItem);
			} else if (TargetTrakErrorTypeEnum.ERROR == response.getErrorType()) {
				model.addAttribute("companyItem", companyViewHelper.getCompanyById(companyItem.getId()));
			}
			model.addAttribute("errorMessage", response.getMessage());
		}
		model.addAttribute("statesList", viewHelper.getReferenceDataByType("states"));
		model.addAttribute("citiesList", viewHelper.getReferenceDataByType("cities"));
		model.addAttribute("countriesList", viewHelper.getReferenceDataByType("countries"));
		return EDIT_COMPANY_SCREEN;
	}
	
	@RequestMapping(value = "/cancelEditCompany.htm", method = RequestMethod.POST)
	public String cancelUpdateReferenceData(@ModelAttribute("companyItem") CompanyItem companyItem, RedirectAttributes attributes) {
		attributes.addAttribute("id", companyItem.getId());
		return "redirect:/viewCompany.htm";
	}
	
	@RequestMapping(value = "/showCreateCompany.htm", method = RequestMethod.GET)
	public ModelAndView showCreateCompanyScreen() {
		ModelAndView mav = new ModelAndView(CREATE_COMPANY_SCREEN, "companyItem", new CompanyItem());
		mav.addObject("statesList", viewHelper.getReferenceDataByType("states"));
		mav.addObject("citiesList", viewHelper.getReferenceDataByType("cities"));
		mav.addObject("countriesList", viewHelper.getReferenceDataByType("countries"));
		return mav;
	}
	
	@RequestMapping(value = "/createCompany.htm", method = RequestMethod.POST)
	public String createCompany(@ModelAttribute("companyItem") CompanyItem companyItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompany(companyViewHelper.buildCompanyDto(companyItem, user));

		CompanyApiResponse response = new CompanyApiResponse();
		response = companyService.createCompany(request);
		
		if (response.isSuccess()) {
			attributes.addAttribute("id", response.getCompany().getId());
			return "redirect:/viewCompany.htm";
		}
		
		targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "companyItem");
		model.addAttribute("errorMessage", response.getMessage());
		model.addAttribute("statesList", viewHelper.getReferenceDataByType("states"));
		model.addAttribute("citiesList", viewHelper.getReferenceDataByType("cities"));
		model.addAttribute("countriesList", viewHelper.getReferenceDataByType("countries"));
		model.addAttribute("companyItem", companyItem);
		return CREATE_COMPANY_SCREEN;
	}
	
	private List<NameValuePair> buildDistinctUsersList() {
		UserApiResponse response = userService.getDistinctUsers(new UserApiRequest());
		List<UserDto> users = response.getUsers();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = null;
		for (UserDto user : users) {
			nvp = new NameValuePair(buildName(user), user.getUsername());
			list.add(nvp);
		}
		return list;
	}

	private String buildName(UserDto user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getFirstName()).append(" ").append(user.getLastName());
		return builder.toString();
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	public void setPagingBuilder(PaginationBuilder pagingBuilder) {
		this.pagingBuilder = pagingBuilder;
	}

	public void setTargetTrakExceptionHandler(ExceptionHandler targetTrakExceptionHandler) {
		this.targetTrakExceptionHandler = targetTrakExceptionHandler;
	}

	public void setViewHelper(ReferenceDataHelper viewHelper) {
		this.viewHelper = viewHelper;
	}

	public void setCompanyViewHelper(CompanyHelper companyViewHelper) {
		this.companyViewHelper = companyViewHelper;
	}
}
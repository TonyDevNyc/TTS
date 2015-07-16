package com.target.trak.system.web.controllers.refdata;

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
import com.target.trak.system.service.ReferenceDataService;
import com.target.trak.system.service.UsersService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.dto.security.UserDto;
import com.target.trak.system.service.dto.security.user.UserApiRequest;
import com.target.trak.system.service.dto.security.user.UserApiResponse;
import com.target.trak.system.web.builders.PaginationBuilder;
import com.target.trak.system.web.forms.SearchReferenceDataForm;
import com.target.trak.system.web.handlers.ExceptionHandler;
import com.target.trak.system.web.views.NameValuePair;
import com.target.trak.system.web.views.PaginationBean;
import com.target.trak.system.web.views.ReferenceDataItem;
import com.target.trak.system.web.views.helper.ReferenceDataViewHelper;

@SessionAttributes("searchReferenceDataForm")
@Controller
public class ReferenceDataController {

	private static final int FIRST_PAGE = 1;
	private static final String SEARCH_REFERENCE_DATA_SCREEN = "referencedata/searchReferenceData";
	private static final String VIEW_REFERENCE_DATA_ITEM_SCREEN = "referencedata/viewReferenceDataItem";
	private static final String EDIT_REFERENCE_DATA_ITEM_SCREEN = "referencedata/editReferenceDataItem";
	private static final String CREATE_REFERENCE_DATA_ITEM_SCREEN = "referencedata/createReferenceDataItem";

	private ReferenceDataService referenceDataService;

	private UsersService userService;

	private PaginationBuilder pagingBuilder;

	private ExceptionHandler targetTrakExceptionHandler;

	private ReferenceDataViewHelper viewHelper;

	@RequestMapping(value = "/searchReferenceData.htm", method = RequestMethod.GET)
	public ModelAndView showSearchReferenceDataScreen() {
		List<NameValuePair> typesList = getReferenceDataTypesList();
		List<NameValuePair> statusList = getReferenceDataByTypeList("status");

		ModelAndView mav = new ModelAndView(SEARCH_REFERENCE_DATA_SCREEN, "searchReferenceDataForm", new SearchReferenceDataForm());
		mav.addObject("referenceDataTypes", typesList);
		mav.addObject("statusList", statusList);
		mav.addObject("usersList", buildDistinctUsersList());
		return mav;
	}

	@RequestMapping(value = "/searchReferenceDataByCriteria.htm", method = RequestMethod.POST)
	public String searchReferenceDataByCriteria(@ModelAttribute("searchReferenceDataForm") SearchReferenceDataForm searchReferenceDataForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		ReferenceDataApiRequest request = viewHelper.buildSearchCriteriaRequest(searchReferenceDataForm);
		ReferenceDataApiResponse response = referenceDataService.getReferenceDataByCriteria(request);

		List<ReferenceDataItem> referenceDataList = viewHelper.buildReferenceDataList(response.getReferenceDataList());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(FIRST_PAGE, response.getTotalSize());

		model.addAttribute("referenceDataTypes", getReferenceDataTypesList());
		model.addAttribute("statusList", getReferenceDataByTypeList("status"));
		model.addAttribute("usersList", buildDistinctUsersList());
		model.addAttribute("referenceDataList", referenceDataList);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchReferenceDataForm", searchReferenceDataForm);
		return SEARCH_REFERENCE_DATA_SCREEN;
	}

	@RequestMapping(value = "/backToReferenceDataSearchResults.htm", method = RequestMethod.GET)
	public String backToSearchResults(@ModelAttribute("searchReferenceDataForm") SearchReferenceDataForm searchReferenceDataForm, ModelMap model) {
		ReferenceDataApiRequest request = viewHelper.buildSearchCriteriaRequest(searchReferenceDataForm);
		ReferenceDataApiResponse response = referenceDataService.getReferenceDataByCriteria(request);

		List<ReferenceDataItem> referenceDataList = viewHelper.buildReferenceDataList(response.getReferenceDataList());
		PaginationBean paginationBean = pagingBuilder.buildPaginationBean(FIRST_PAGE, response.getTotalSize());
		model.addAttribute("referenceDataTypes", getReferenceDataTypesList());
		model.addAttribute("statusList", getReferenceDataByTypeList("status"));
		model.addAttribute("usersList", buildDistinctUsersList());
		model.addAttribute("referenceDataList", referenceDataList);
		model.addAttribute("pagingList", paginationBean.getPagesDisplayed());
		model.addAttribute("searchReferenceDataForm", searchReferenceDataForm);
		return SEARCH_REFERENCE_DATA_SCREEN;
	}

	@RequestMapping(value = "/getPagedReferenceData.htm", method = RequestMethod.GET)
	public ModelAndView getPagedReferenceData(HttpSession session, @RequestParam int page, @RequestParam int start) {
		SearchReferenceDataForm searchReferenceDataForm = (SearchReferenceDataForm) session.getAttribute("referenceDataSearchCriteria");
		searchReferenceDataForm.setPage(page);
		searchReferenceDataForm.setStart(start);

		ReferenceDataApiRequest request = viewHelper.buildSearchCriteriaRequest(searchReferenceDataForm);
		ReferenceDataApiResponse response = referenceDataService.getReferenceDataByCriteria(request);

		ModelAndView mav = new ModelAndView(SEARCH_REFERENCE_DATA_SCREEN, "searchReferenceDataForm", searchReferenceDataForm);
		if (response.isSuccess()) {
			List<ReferenceDataItem> referenceDataList = viewHelper.buildReferenceDataList(response.getReferenceDataList());
			PaginationBean paginationBean = pagingBuilder.buildPaginationBean(page, response.getTotalSize());
			mav.addObject("referenceDataTypes", getReferenceDataTypesList());
			mav.addObject("statusList", getReferenceDataByTypeList("status"));
			mav.addObject("usersList", buildDistinctUsersList());
			mav.addObject("referenceDataList", referenceDataList);
			mav.addObject("pagingList", paginationBean.getPagesDisplayed());
		}
		return mav;
	}

	@RequestMapping(value = "/viewReferenceDataItem.htm", method = RequestMethod.GET)
	public ModelAndView viewReferenceDataItem(@RequestParam long id) {
		ModelAndView mav = new ModelAndView(VIEW_REFERENCE_DATA_ITEM_SCREEN, "referenceDataItem", getReferenceDataItem(id));
		mav.addObject("statusList", getReferenceDataByTypeList("status"));
		return mav;
	}

	@RequestMapping(value = "/showEditReferenceDataItem.htm", method = RequestMethod.GET)
	public ModelAndView showEditReferenceDataItem(@RequestParam long id) {
		ModelAndView mav = new ModelAndView(EDIT_REFERENCE_DATA_ITEM_SCREEN, "referenceDataItem", getReferenceDataItem(id));
		mav.addObject("statusList", getReferenceDataByTypeList("status"));
		return mav;
	}

	@RequestMapping(value = "/updateReferenceDataItem.htm", method = RequestMethod.POST)
	public String updateReferenceDataItem(@ModelAttribute("referenceDataItem") ReferenceDataItem referenceDataItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(viewHelper.buildReferenceDataDto(referenceDataItem, user));

		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		response = referenceDataService.updateReferenceDataItem(request);

		if (response.isSuccess()) {
			model.addAttribute("statusList", getReferenceDataByTypeList("status"));
			model.addAttribute("message", "Reference Data Item updated successfully.");
			model.addAttribute("referenceDataItem", getReferenceDataItem(referenceDataItem.getId()));
			return EDIT_REFERENCE_DATA_ITEM_SCREEN;
		} else {
			if (TargetTrakErrorTypeEnum.VALIDATION == response.getErrorType()) {
				targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "referenceDataItem");
				model.addAttribute("referenceDataItem", referenceDataItem);
			} else if (TargetTrakErrorTypeEnum.ERROR == response.getErrorType()) {
				model.addAttribute("referenceDataItem", getReferenceDataItem(referenceDataItem.getId()));
			}
			model.addAttribute("errorMessage", response.getMessage());
			model.addAttribute("statusList", getReferenceDataByTypeList("status"));
			return EDIT_REFERENCE_DATA_ITEM_SCREEN;
		}
	}

	@RequestMapping(value = "/cancelEditReferenceData.htm", method = RequestMethod.POST)
	public String cancelUpdateReferenceData(@ModelAttribute("referenceDataItem") ReferenceDataItem referenceDataItem, RedirectAttributes attributes) {
		attributes.addAttribute("id", referenceDataItem.getId());
		return "redirect:/viewReferenceDataItem.htm";
	}

	@RequestMapping(value = "/showCreateReferenceData.htm", method = RequestMethod.GET)
	public ModelAndView showCreateReferenceData() {
		List<NameValuePair> statusList = getReferenceDataByTypeList("status");
		ModelAndView mav = new ModelAndView(CREATE_REFERENCE_DATA_ITEM_SCREEN, "createReferenceDataForm", new ReferenceDataItem());
		mav.addObject("statusList", statusList);
		return mav;
	}

	@RequestMapping(value = "/createReferenceDataItem.htm", method = RequestMethod.POST)
	public String createReferenceData(@ModelAttribute("createReferenceDataForm") ReferenceDataItem referenceDataItem, BindingResult result, ModelMap model, RedirectAttributes attributes, @AuthenticationPrincipal UsernamePasswordAuthenticationToken authentication) {
		User user = (User) authentication.getPrincipal();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(viewHelper.buildReferenceDataDto(referenceDataItem, user));
		ReferenceDataApiResponse response = referenceDataService.createReferenceData(request);
		
		if (response.isSuccess()) {
			attributes.addAttribute("id", response.getReferenceData().getId());
			return "redirect:/viewReferenceDataItem.htm";
		} else {
			targetTrakExceptionHandler.bindValidationErrors(response.getErrors(), result, "createReferenceDataForm");
			model.addAttribute("errorMessage", targetTrakExceptionHandler.bindValidationErrorMessage(response));
			model.addAttribute("createReferenceDataForm", referenceDataItem);
			model.addAttribute("statusList", getReferenceDataByTypeList("status"));
			return CREATE_REFERENCE_DATA_ITEM_SCREEN;
		}
	}
	
	private ReferenceDataItem getReferenceDataItem(final long id) {
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(id);
		request.setReferenceDataDto(dto);

		ReferenceDataApiResponse response = referenceDataService.getReferenceDataItemById(request);
		return viewHelper.buildReferenceDataItem(response.getReferenceData());
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

	private List<NameValuePair> getReferenceDataByTypeList(String referenceDataType) {
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setType(referenceDataType);
		request.setReferenceDataDto(dto);

		ReferenceDataApiResponse response = referenceDataService.getReferenceDataByType(request);
		return viewHelper.buildNameValuePairList(response.getReferenceDataList());
	}

	private List<NameValuePair> getReferenceDataTypesList() {
		ReferenceDataApiResponse response = referenceDataService.getReferenceDataTypes(new ReferenceDataApiRequest());
		return viewHelper.buildReferenceDataTypesList(response.getReferenceDataList());
	}

	public void setReferenceDataService(ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
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

	public void setViewHelper(ReferenceDataViewHelper viewHelper) {
		this.viewHelper = viewHelper;
	}
}
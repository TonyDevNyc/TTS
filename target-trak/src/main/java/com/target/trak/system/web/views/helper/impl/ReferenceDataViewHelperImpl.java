package com.target.trak.system.web.views.helper.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.dto.referencedata.ReferenceDataSearchCriteriaDto;
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.forms.SearchReferenceDataForm;
import com.target.trak.system.web.views.NameValuePair;
import com.target.trak.system.web.views.ReferenceDataItem;
import com.target.trak.system.web.views.helper.ReferenceDataViewHelper;

public class ReferenceDataViewHelperImpl implements ReferenceDataViewHelper {

	@Override
	public List<ReferenceDataItem> buildReferenceDataList(final List<ReferenceDataDto> referenceDataList) {
		List<ReferenceDataItem> list = new ArrayList<ReferenceDataItem>();
		if (referenceDataList != null && !referenceDataList.isEmpty()) {
			for (ReferenceDataDto referenceData : referenceDataList) {
				list.add(buildReferenceDataItem(referenceData));
			}
		}
		return list;
	}

	@Override
	public ReferenceDataDto buildReferenceDataDto(final ReferenceDataItem item, final User user) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(item.getId());
		dto.setType(item.getType());
		dto.setLabel(item.getLabel());
		dto.setValue(item.getValue());
		dto.setStatus(item.getStatus());
		Calendar currentTime = Calendar.getInstance();
		dto.setLastUpdatedBy(user.getUsername());
		dto.setLastUpdatedDateTime(currentTime);
		dto.setVersion(item.getVersion());
		dto.setCreatedBy(user.getUsername());
		dto.setCreatedDateTime(currentTime);
		return dto;
	}

	@Override
	public ReferenceDataApiRequest buildSearchCriteriaRequest(final SearchReferenceDataForm searchReferenceDataForm) {
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataSearchCriteriaDto searchCriteria = new ReferenceDataSearchCriteriaDto();
		searchCriteria.setReferenceDataType(searchReferenceDataForm.getReferenceDataType());
		searchCriteria.setReferenceDataLabel(searchReferenceDataForm.getReferenceDataLabel());
		searchCriteria.setStatus(searchReferenceDataForm.getStatus());
		searchCriteria.setCreatedBy(searchReferenceDataForm.getCreatedBy());
		searchCriteria.setLastUpdatedBy(searchReferenceDataForm.getLastUpdatedBy());

		int page = searchReferenceDataForm.getPage();
		searchCriteria.setPage(page == 0 ? 1 : page);
		int start = searchReferenceDataForm.getStart();
		searchCriteria.setStart(start == 0 ? 1 : start);
		searchCriteria.setEnd(10);
		request.setSearchCriteria(searchCriteria);
		return request;
	}

	@Override
	public List<NameValuePair> buildReferenceDataTypesList(final List<ReferenceDataDto> referenceDataList) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (referenceDataList != null && !referenceDataList.isEmpty()) {
			for (ReferenceDataDto referenceData : referenceDataList) {
				list.add(new NameValuePair(referenceData.getType(), referenceData.getType()));
			}
		}
		return list;
	}

	@Override
	public ReferenceDataItem buildReferenceDataItem(final ReferenceDataDto referenceData) {
		ReferenceDataItem item = new ReferenceDataItem();
		item.setId(referenceData.getId());
		item.setType(referenceData.getType());
		item.setLabel(referenceData.getLabel());
		item.setValue(referenceData.getValue());
		item.setStatus(referenceData.getStatus());
		item.setCreatedBy(referenceData.getCreatedBy());
		item.setCreatedDate(DateUtil.convertDateToIso8601(referenceData.getCreatedDateTime()));
		item.setLastUpdatedBy(referenceData.getLastUpdatedBy());
		item.setLastUpdateDate(DateUtil.convertDateToIso8601(referenceData.getLastUpdatedDateTime()));
		item.setUrl("/target-trak/viewReferenceDataItem.htm?id=" + referenceData.getId());
		item.setVersion(referenceData.getVersion());
		return item;
	}

	@Override
	public List<NameValuePair> buildNameValuePairList(final List<ReferenceDataDto> referenceDataList) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (referenceDataList != null && !referenceDataList.isEmpty()) {
			NameValuePair nvp = null;
			for (ReferenceDataDto dto : referenceDataList) {
				nvp = new NameValuePair(dto.getLabel(), dto.getValue());
				list.add(nvp);
			}
		}
		return list;
	}
}
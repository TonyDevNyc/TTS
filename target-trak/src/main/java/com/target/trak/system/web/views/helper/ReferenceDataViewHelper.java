package com.target.trak.system.web.views.helper;

import java.util.List;

import com.target.trak.system.security.entity.User;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.web.forms.SearchReferenceDataForm;
import com.target.trak.system.web.views.NameValuePair;
import com.target.trak.system.web.views.ReferenceDataItem;

public interface ReferenceDataViewHelper {

	public List<ReferenceDataItem> buildReferenceDataList(final List<ReferenceDataDto> dtos);
	
	public ReferenceDataDto buildReferenceDataDto(final ReferenceDataItem item, final User user);
	
	public ReferenceDataApiRequest buildSearchCriteriaRequest(final SearchReferenceDataForm searchReferenceDataForm);
	
	public List<NameValuePair> buildReferenceDataTypesList(final List<ReferenceDataDto> referenceDataList);
	
	public ReferenceDataItem buildReferenceDataItem(final ReferenceDataDto referenceData);
	
	public List<NameValuePair> buildNameValuePairList(final List<ReferenceDataDto> referenceDataList);
}

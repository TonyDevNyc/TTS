package com.target.trak.system.service;

import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;

public interface ReferenceDataService {

	public ReferenceDataApiResponse createReferenceData(final ReferenceDataApiRequest request);
	
	public ReferenceDataApiResponse deleteReferenceData(final ReferenceDataApiRequest request);
	
	public ReferenceDataApiResponse getReferenceDataTypes(final ReferenceDataApiRequest request);

	public ReferenceDataApiResponse getReferenceDataByCriteria(final ReferenceDataApiRequest request);
	
	public ReferenceDataApiResponse getReferenceDataItemById(final ReferenceDataApiRequest request);
	
	public ReferenceDataApiResponse getReferenceDataByType(final ReferenceDataApiRequest request);
	
	public ReferenceDataApiResponse updateReferenceDataItem(final ReferenceDataApiRequest request);
}
package com.target.trak.system.service.dto.converters;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.entity.ReferenceData;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.util.DateUtil;

public class ReferenceDataDtoToEntityConverter implements Converter<ReferenceDataDto, ReferenceData> {

	@Override
	public ReferenceData convert(ReferenceDataDto dto) {
		ReferenceData entity = new ReferenceData();
		entity.setId(dto.getId());
		entity.setReferenceDataType(dto.getType());
		entity.setReferenceDataLabel(dto.getLabel());
		entity.setReferenceDataValue(dto.getValue());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setStatus(dto.getStatus());
		entity.setCreatedTimestamp(DateUtil.convertCalendarToTimestamp(dto.getCreatedDateTime()));
		entity.setLastUpdatedBy(dto.getLastUpdatedBy());
		entity.setLastUpdatedTimestamp(DateUtil.convertCalendarToTimestamp(dto.getLastUpdatedDateTime()));
		entity.setVersion(dto.getVersion());
		return entity;
	}

}

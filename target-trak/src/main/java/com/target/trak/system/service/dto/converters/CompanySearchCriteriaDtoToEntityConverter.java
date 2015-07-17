package com.target.trak.system.service.dto.converters;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.entity.criteria.CompanySearchCriteria;
import com.target.trak.system.service.dto.company.CompanySearchCriteriaDto;

public class CompanySearchCriteriaDtoToEntityConverter implements Converter<CompanySearchCriteriaDto, CompanySearchCriteria> {

	@Override
	public CompanySearchCriteria convert(CompanySearchCriteriaDto dto) {
		CompanySearchCriteria criteria = new CompanySearchCriteria();
		criteria.setName(dto.getName());
		criteria.setCity(dto.getCity());
		criteria.setState(dto.getState());
		criteria.setCountry(dto.getCountry());
		criteria.setLastUpdatedBy(dto.getLastUpdatedBy());
		criteria.setCreatedBy(dto.getCreatedBy());
		criteria.setPage(dto.getPage());
		criteria.setStart(dto.getStart());
		criteria.setEnd(dto.getEnd());
		criteria.setSortDirection(dto.getSortDirection());
		criteria.setSortField(dto.getSortField());
		return criteria;
	}

}

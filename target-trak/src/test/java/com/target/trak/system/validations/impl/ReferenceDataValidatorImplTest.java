package com.target.trak.system.validations.impl;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.target.trak.system.service.dto.common.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.ReferenceDataRules;

public class ReferenceDataValidatorImplTest {

	private Mockery mockery;
	private ReferenceDataRules referenceDataRulesMock;
	private ReferenceDataValidatorImpl validator;

	@Before
	public void setup() {
		mockery = new JUnit4Mockery();
		referenceDataRulesMock = mockery.mock(ReferenceDataRules.class);
		validator = new ReferenceDataValidatorImpl(referenceDataRulesMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateForNullRequest() {
		final ReferenceDataApiRequest request = null;
		validator.validate(request);
		Assert.fail("Test should fail due to exception thrown");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateForNullDto() {
		final ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		validator.validate(request);
		Assert.fail("Test should fail due to exception thrown");
	}

	@Test
	public void validateCreateNoErrors() {
		final ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setRequestType(TargetTrakRequestTypeEnum.CREATE);
		ReferenceDataDto dto = buildReferenceDataDto(null, "Test", "msgs", "Test", "Active");
		request.setReferenceDataDto(dto);

		referenceDataRulesTypeNoErrors(dto.getType());
		referenceDataRulesLabelNoErrors(dto.getLabel());
		referenceDataRulesValueNoErrors(dto.getValue());
		referenceDataRulesStatusNoErrors(dto.getStatus());
		referenceAlreadyExistsNoErrors(dto.getType(), dto.getLabel(), dto.getValue());
		
		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors list is not null", errors);
		Assert.assertTrue("Validation Errors is empty", errors.isEmpty());
	}
	
	@Test
	public void validateDeleteNoErrors() {
		final ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setRequestType(TargetTrakRequestTypeEnum.DELETE);
		ReferenceDataDto dto = buildReferenceDataDto(1L, "Test", "msgs", "Test", "Active");
		request.setReferenceDataDto(dto);
		referenceDataRulesIdNoErrors(dto.getId());
		
		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors list is not null", errors);
		Assert.assertTrue("Validation Errors is empty", errors.isEmpty());
	}
	
	private void referenceDataRulesIdNoErrors(final Long id) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).isIdEmpty(with(any(Long.class)));
				will(returnValue(null));
			}
		});
	}
	
	private void referenceDataRulesStatusNoErrors(final String status) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).isStatusEmpty(with(equal(status)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).isStatusValidLength(with(equal(status)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).statusContainsAllowableChars(with(equal(status)));
				will(returnValue(null));
			}
		});
	}

	private void referenceAlreadyExistsNoErrors(final String type, final String label, final String value) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).referenceDataAlreadyExists(with(equal(type)), with(equal(label)), with(equal(value)));
				will(returnValue(null));
			}
		});
	}

	private void referenceDataRulesValueNoErrors(final String value) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).isValueEmpty(with(equal(value)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).isValueValidLength(with(equal(value)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).valueContainsAllowableChars(with(equal(value)));
				will(returnValue(null));
			}
		});
	}

	private void referenceDataRulesLabelNoErrors(final String label) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).isLabelEmpty(with(equal(label)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).isLabelValidLength(with(equal(label)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).labelContainsAllowableChars(with(equal(label)));
				will(returnValue(null));
			}
		});
	}

	private void referenceDataRulesTypeNoErrors(final String type) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(referenceDataRulesMock).isTypeEmpty(with(equal(type)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).isTypeValidLength(with(equal(type)));
				will(returnValue(null));

				oneOf(referenceDataRulesMock).typeContainsAllowableChars(with(equal(type)));
				will(returnValue(null));
			}
		});
	}

	private ReferenceDataDto buildReferenceDataDto(Long id, String label, String type, String value, String status) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(id);
		dto.setLabel(label);
		dto.setType(type);
		dto.setValue(value);
		dto.setStatus(status);
		return dto;
	}
}

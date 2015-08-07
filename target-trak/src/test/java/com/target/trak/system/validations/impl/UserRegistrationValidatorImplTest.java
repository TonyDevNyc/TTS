package com.target.trak.system.validations.impl;

import java.util.Calendar;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.target.trak.system.service.dto.security.UserDto;
import com.target.trak.system.service.dto.security.registration.RegistrationApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.EmailRules;
import com.target.trak.system.validations.rules.FirstNameRules;
import com.target.trak.system.validations.rules.LastNameRules;
import com.target.trak.system.validations.rules.PasswordRules;
import com.target.trak.system.validations.rules.PhoneNumberRules;
import com.target.trak.system.validations.rules.UsernameRules;

public class UserRegistrationValidatorImplTest {

	private Mockery mockery;
	private UserRegistrationValidatorImpl validator;
	private UsernameRules usernameRulesMock;
	private PasswordRules passwordRulesMock;
	private FirstNameRules firstNameRulesMock;
	private LastNameRules lastNameRulesMock;
	private PhoneNumberRules phoneNumberRulesMock;
	private EmailRules emailRulesMock;

	@Before
	public void setup() {
		mockery = new JUnit4Mockery();
		validator = new UserRegistrationValidatorImpl();
		usernameRulesMock = mockery.mock(UsernameRules.class);
		passwordRulesMock = mockery.mock(PasswordRules.class);
		firstNameRulesMock = mockery.mock(FirstNameRules.class);
		lastNameRulesMock = mockery.mock(LastNameRules.class);
		phoneNumberRulesMock = mockery.mock(PhoneNumberRules.class);
		emailRulesMock = mockery.mock(EmailRules.class);

		validator.setUsernameRules(usernameRulesMock);
		validator.setPasswordRules(passwordRulesMock);
		validator.setFirstNameRules(firstNameRulesMock);
		validator.setLastNameRules(lastNameRulesMock);
		validator.setPhoneNumberRules(phoneNumberRulesMock);
		validator.setEmailRules(emailRulesMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateForNullRequest() {
		final RegistrationApiRequest request = null;
		validator.validate(request);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateForNullDto() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		validator.validate(request);
	}

	@Test
	public void validateForEmptyUsername() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("", "password", "password", "Tony", "Jameson", "tony.jameson@gmail.com", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(usernameRulesMock).isUsernameEmpty(with(equal("")));
				will(returnValue(new TargetTrakValidationError("username", "USERNAME_ERROR")));
			}
		});
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		firstNameRulesMockNoErrors(user.getFirstName());
		lastNameRulesMockNoErrors(user.getLastName());
		emailRulesMockNoErrors(user.getEmail());
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());
		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
	}

	@Test
	public void validateForValidUsername() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("user123", "password", "password", "Tony", "Jameson", "tony.jameson@gmail.com", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		firstNameRulesMockNoErrors(user.getFirstName());
		lastNameRulesMockNoErrors(user.getLastName());
		emailRulesMockNoErrors(user.getEmail());
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is empty", errors.isEmpty());
	}

	@Test
	public void validateForEmptyPassword() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("", "", "password", "Tony", "Jameson", "tony.jameson@gmail.com", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(passwordRulesMock).isPasswordEmpty(with(equal(user.getPassword())));
				will(returnValue(new TargetTrakValidationError("password", "USERNAME_ERROR")));

				oneOf(passwordRulesMock).repeatedPasswordEmpty(with(equal(user.getRepeatedPassword())));
				will(returnValue(null));
			}
		});
		firstNameRulesMockNoErrors(user.getFirstName());
		lastNameRulesMockNoErrors(user.getLastName());
		emailRulesMockNoErrors(user.getEmail());
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is not empty", !errors.isEmpty());
	}

	@Test
	public void validateForEmptyFirstName() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("user", "password", "password", "", "Jameson", "tony.jameson@gmail.com", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(user.getFirstName())));
				will(returnValue(new TargetTrakValidationError("firstName", "FIRST_NAME")));
			}
		});
		lastNameRulesMockNoErrors(user.getLastName());
		emailRulesMockNoErrors(user.getEmail());
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is not empty", !errors.isEmpty());
	}

	@Test
	public void validateForEmptyLastName() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("user", "password", "password", "Tony", "", "tony.jameson@gmail.com", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		firstNameRulesMockNoErrors(user.getFirstName());
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(lastNameRulesMock).isLastNameEmpty(with(equal(user.getLastName())));
				will(returnValue(new TargetTrakValidationError("lastName", "LAST_NAME")));
			}
		});

		emailRulesMockNoErrors(user.getEmail());
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is not empty", !errors.isEmpty());
	}

	@Test
	public void validateForEmptyEmail() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("user", "password", "password", "Tony", "Jameson", "", "212-123-1234", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		firstNameRulesMockNoErrors(user.getFirstName());
		lastNameRulesMockNoErrors(user.getLastName());
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(emailRulesMock).isEmailEmpty(with(equal(user.getEmail())));
				will(returnValue(new TargetTrakValidationError("email", "EMAIL")));
			}
		});
		telephoneNumberRulesMockNoErrors(user.getTelephoneNumber());

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is not empty", !errors.isEmpty());
	}

	@Test
	public void validateForTelephoneNumber() {
		final RegistrationApiRequest request = new RegistrationApiRequest();
		final UserDto user = buildUserDto("user", "password", "password", "Tony", "Jameson", "jameson@google.com", "", Calendar.getInstance());
		request.setUserRegistration(user);

		usernameRulesMockNoErrors(user.getUsername());
		passwordRulesMockNoErrors(user.getPassword(), user.getRepeatedPassword());
		firstNameRulesMockNoErrors(user.getFirstName());
		lastNameRulesMockNoErrors(user.getLastName());
		emailRulesMockNoErrors(user.getEmail());
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(phoneNumberRulesMock).isPhoneNumberEmpty(with(equal(user.getTelephoneNumber())));
				will(returnValue(new TargetTrakValidationError("telephoneNumber", "PHONE_NUMBER")));
			}
		});

		List<TargetTrakValidationError> errors = validator.validate(request);
		Assert.assertNotNull("Validation Errors are not null", errors);
		Assert.assertTrue("Errors is not empty", !errors.isEmpty());
	}

	private void usernameRulesMockNoErrors(final String username) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(usernameRulesMock).isUsernameEmpty(with(equal(username)));
				will(returnValue(null));

				oneOf(usernameRulesMock).isUsernameValidLength(with(equal(username)));
				will(returnValue(null));

				oneOf(usernameRulesMock).usernameAlreadyExists(with(equal(username)));
				will(returnValue(null));

				oneOf(usernameRulesMock).usernameIsAlphabeticOnly(with(equal(username)));
				will(returnValue(null));
			}
		});
	}

	private void telephoneNumberRulesMockNoErrors(final String telephoneNumber) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(phoneNumberRulesMock).isPhoneNumberEmpty(with(equal(telephoneNumber)));
				will(returnValue(null));

				oneOf(phoneNumberRulesMock).isPhoneNumberValidLength(with(equal(telephoneNumber)));
				will(returnValue(null));

				oneOf(phoneNumberRulesMock).phoneContainsDigitsAndDashOnly(with(equal(telephoneNumber)));
				will(returnValue(null));
			}
		});
	}

	private void emailRulesMockNoErrors(final String email) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(emailRulesMock).isEmailEmpty(with(equal(email)));
				will(returnValue(null));

				oneOf(emailRulesMock).isEmailValidLength(with(equal(email)));
				will(returnValue(null));

				oneOf(emailRulesMock).isEmailValid(with(equal(email)));
				will(returnValue(null));
			}
		});
	}

	private void lastNameRulesMockNoErrors(final String lastName) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(lastNameRulesMock).isLastNameEmpty(with(equal(lastName)));
				will(returnValue(null));

				oneOf(lastNameRulesMock).isLastNameValidLength(with(equal(lastName)));
				will(returnValue(null));

				oneOf(lastNameRulesMock).lastNameIsAlphabeticOnly(with(equal(lastName)));
				will(returnValue(null));
			}
		});
	}

	private void passwordRulesMockNoErrors(final String password, final String repeatedPassword) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(passwordRulesMock).isPasswordEmpty(with(equal(password)));
				will(returnValue(null));

				oneOf(passwordRulesMock).isPasswordValidLength(with(equal(password)));
				will(returnValue(null));

				oneOf(passwordRulesMock).repeatedPasswordEmpty(with(equal(repeatedPassword)));
				will(returnValue(null));

				oneOf(passwordRulesMock).passwordEqualsRepeatedPassword(with(equal(repeatedPassword)), with(equal(repeatedPassword)));
				will(returnValue(null));

			}
		});
	}

	private void firstNameRulesMockNoErrors(final String firstName) {
		mockery.checking(new org.jmock.Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(firstName)));
				will(returnValue(null));

				oneOf(firstNameRulesMock).isFirstNameValidLength(with(equal(firstName)));
				will(returnValue(null));

				oneOf(firstNameRulesMock).firstNameIsAlphabeticOnly(with(equal(firstName)));
				will(returnValue(null));
			}
		});
	}

	private UserDto buildUserDto(String un, String pw, String repeatPw, String fName, String lName, String email, String number, Calendar regDate) {
		UserDto dto = new UserDto();
		dto.setUsername(un);
		dto.setPassword(pw);
		dto.setRepeatedPassword(repeatPw);
		dto.setFirstName(fName);
		dto.setLastName(lName);
		dto.setEmail(email);
		dto.setTelephoneNumber(number);
		dto.setRegistrationDate(regDate);
		return dto;
	}
}

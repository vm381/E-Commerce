package ecommerce.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if (user.getUsername().equals("")) {
			errors.rejectValue("username", "Username can't be empty.");
		}
		if (user.getPassword().equals("")) {
			errors.rejectValue("password", "Password can't be empty.");
		}
		if (user.getFirstName().equals("")) {
			errors.rejectValue("firstName", "First name can't be empty.");
		}
		if (user.getLastName().equals("")) {
			errors.rejectValue("lastName", "Last name can't be empty.");
		}
		if (user.getBirthday() == null) {
			errors.rejectValue("birthday", "Birthday can't be empty.");
		}
		if (user.getAddress().equals("")) {
			errors.rejectValue("address", "Address can't be empty.");
		}
		if (user.getCity().equals("")) {
			errors.rejectValue("city", "City can't be empty.");
		}
		if (user.getCountry().equals("")) {
			errors.rejectValue("country", "Country can't be empty.");
		}
		if (user.getPhone().equals("") || !user.getPhone().matches("[0-9]+")) {
			errors.rejectValue("phone", "Phone number must be valid.");
		}
	}

}

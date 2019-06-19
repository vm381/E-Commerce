package ecommerce.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Category;

public class CategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category cat = (Category)target;
		if (cat.getName().equals("")) {
			errors.rejectValue("name", "Category name can't be empty.");
		}
	}

}

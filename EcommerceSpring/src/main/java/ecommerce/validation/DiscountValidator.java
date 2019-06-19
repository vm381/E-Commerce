package ecommerce.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Discount;

public class DiscountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Discount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Discount d = (Discount)target;
		if (d.getPercent() < 0 || d.getPercent() > 99) {
			errors.rejectValue("percent", "Percent off must be between 0 and 99");
		}
		if (d.getItems().isEmpty()) {
			errors.rejectValue("items", "You must select at least one product.");
		}
		if (d.getDate() == null) {
			errors.rejectValue("date", "Discount date must be set.");
		}
	}

}

package ecommerce.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Item;

public class ItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Item.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Item i = (Item)target;
		if (i.getName().equals("")) {
			errors.rejectValue("name", "Product name can't be empty.");
		}
		if (i.getDescription().equals("")) {
			errors.rejectValue("description", "Description can't be empty.");
		}
		if (i.getPrice() <= 0) {
			errors.rejectValue("price", "Price must be positive number.");
		}
		if (i.getStock() < 0) {
			errors.rejectValue("stock", "Stock can't be negative number.");
		}
		if (i.getCategory() == null) {
			errors.rejectValue("category", "You must select a category.");
		}
	}

}

package ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.CategoryRepo;
import ecommerce.repository.ItemRepo;
import ecommerce.validation.CategoryValidator;
import model.Category;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ItemRepo itemRepo;

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String categories(Model model) {
		List<Category> categories = categoryRepo.findAll();

		model.addAttribute("categories", categories);

		return "admin/categories";
	}

	@RequestMapping(value = "admin/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		model.addAttribute("category", new Category());
		
		return "admin/insertCategory";
	}

	@RequestMapping(value = "admin/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("category") Category category, Model model, BindingResult result) {
		CategoryValidator validator = new CategoryValidator();
		validator.validate(category, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("category", category);
			return "admin/editCategory";
		}
		
		List<Category> categories = categoryRepo.findByName(category.getName());

		if (!categories.isEmpty()) {
			model.addAttribute("errMsg", "Category with that name already exist.");
			return "admin/insertCategory";
		}

		try {
			categoryRepo.save(category);

			model.addAttribute("success", "Successfully inserted new category.");
			return "admin/insertCategory";
		} catch (Exception e) {
			model.addAttribute("errMsg", "Failed to insert category.");
			return "admin/insertCategory";
		}
	}
	
	@RequestMapping(value = "admin/edit", method=RequestMethod.GET)
	public String edit(int idCategory, Model model) {
		Optional<Category> cat = categoryRepo.findById(idCategory);
		
		if (!cat.isPresent()) {
			model.addAttribute("errMsg", "Category with given ID does not exists.");
			return "admin/editCategory";
		}
		
		model.addAttribute("category", cat.get());
		
		return "admin/editCategory";
	}
	
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("category") Category category, Model model, BindingResult result) {
		CategoryValidator validator = new CategoryValidator();
		validator.validate(category, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("category", category);
			return "admin/editCategory";
		}
		
		try {
			categoryRepo.save(category);
			model.addAttribute("success", "Successfully edited category name.");
			model.addAttribute("category", category);
			return "admin/editCategory";
		}
		catch (Exception e) {
			model.addAttribute("errMsg", "Failed to edit category.");
			model.addAttribute("category", category);
			return "admin/editCategory";
		}
	}
	
	@RequestMapping(value = "admin/confirmDelete", method = RequestMethod.GET)
	public String confirmDelete(int idCategory, Model model) {
		Optional<Category> cat = categoryRepo.findById(idCategory);
		
		if (!cat.isPresent()) {
			model.addAttribute("errMsg", "Category with given ID does not exist.");
			return "admin/deleteCategory";
		}
		
		model.addAttribute("category", cat.get());
		
		return "admin/deleteCategory";
	}
	
	@RequestMapping(value = "admin/delete", method = RequestMethod.GET)
	public String delete(int idCategory) {
		Category c = categoryRepo.findById(idCategory).get();
		itemRepo.deleteByCategory(c);
		categoryRepo.deleteById(idCategory);
		
		return "redirect:/category/admin/";
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String userCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		
		return "user/categories";
	}

}

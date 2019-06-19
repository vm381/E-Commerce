package ecommerce.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.DiscountRepo;
import ecommerce.repository.ItemRepo;
import ecommerce.validation.DiscountValidator;
import model.Discount;
import model.Item;

@Controller
@RequestMapping(value = "discount")
public class DiscountController {

	@Autowired
	private DiscountRepo discountRepo;
	@Autowired
	private ItemRepo itemRepo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String discounts(Model model) {
		List<Discount> discounts = discountRepo.findAll();

		model.addAttribute("discounts", discounts);
		model.addAttribute("products", itemRepo.findAll());

		return "admin/discounts";
	}

	@RequestMapping(value = "admin/add", method = RequestMethod.GET)
	public String addDiscount(Model model) {
		model.addAttribute("products", itemRepo.findAll());
		model.addAttribute("discount", new Discount());

		return "admin/addDiscount";
	}

	@RequestMapping(value = "admin/add", method = RequestMethod.POST)
	public String addDiscount(@ModelAttribute("discount") Discount discount, Model model, BindingResult result) {
		DiscountValidator validator = new DiscountValidator();
		validator.validate(discount, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("products", itemRepo.findAll());
			return "admin/addDiscount";
		}
		
		for (Item i : discount.getItems()) {
			i.addDiscount(discount);
		}

		discountRepo.save(discount);

		model.addAttribute("success", "Discount successfully added.");
		model.addAttribute("products", itemRepo.findAll());

		return "admin/addDiscount";
	}

	@RequestMapping(value = "admin/details", method = RequestMethod.GET)
	public String details(int idDiscount, Model model) {
		Optional<Discount> discount = discountRepo.findById(idDiscount);

		if (!discount.isPresent()) {
			model.addAttribute("errMsg", "Discount with given ID does not exist.");
			return "admin/discountDetails";
		}

		model.addAttribute("discount", discount.get());
		model.addAttribute("products", itemRepo.findByDiscounts(discount.get()));

		return "admin/discountDetails";
	}
	
	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public String edit(int idDiscount, Model model) {
		Optional<Discount> discount = discountRepo.findById(idDiscount);
		
		if (!discount.isPresent()) {
			model.addAttribute("errMsg", "Discount with given ID does not exist.");
			return "admin/editDiscount";
		}
		
		model.addAttribute("discount", discount.get());
		model.addAttribute("products", itemRepo.findAll());
				
		return "admin/editDiscount";
	}
	
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("discount") Discount discount, Model model, BindingResult result) {
		DiscountValidator validator = new DiscountValidator();
		validator.validate(discount, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("discount", discount);
			return "admin/editDiscount";
		}
		
		for (Item i : itemRepo.findAll()) {
			if (!discount.getItems().contains(i)) {
				i.getDiscounts().remove(discount);
			}
		}
		
		discountRepo.save(discount);
		
		model.addAttribute("discount", discount);
		model.addAttribute("products", itemRepo.findAll());
		model.addAttribute("success", "Discount successfully edited.");
		return "admin/editDiscount";
	}
	
	@RequestMapping(value = "admin/delete", method=RequestMethod.GET)
	public String delete(int idDiscount, Model model) {
		Optional<Discount> discount = discountRepo.findById(idDiscount);
		
		if (!discount.isPresent()) {
			model.addAttribute("errMsg", "Discount with given ID does not exist.");
			return "admin/deleteDiscount";
		}
		
		model.addAttribute("discount", discount.get());
		
		return "admin/deleteDiscount";
	}
	
	@RequestMapping(value = "admin/confirm", method = RequestMethod.GET)
	public String delete(int idDiscount) {
		Discount discount = discountRepo.findById(idDiscount).get();
		
		for (Item i : discount.getItems()) {
			i.getDiscounts().remove(discount);
		}
		
		discountRepo.delete(discount);
		
		return "redirect:/discount/admin/";
	}
	
	@RequestMapping(value = "user/discount", method = RequestMethod.GET)
	public String showDiscounts(Model model) {
		List<Discount> discounts = discountRepo.findAllByDate(new Date());
		Map<Item, Integer> itemsOnDiscount = new HashMap<>();
		
		for (Discount d : discounts) {
			for (Item i : d.getItems()) {
				itemsOnDiscount.put(i, d.getPercent());
			}
		}
		
		model.addAttribute("idems", itemsOnDiscount);
		
		return "home";
	}

}

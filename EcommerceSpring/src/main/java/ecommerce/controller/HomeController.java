package ecommerce.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.DiscountRepo;
import model.Discount;
import model.Item;

@Controller
@RequestMapping(value = "home")
public class HomeController {
	
	@Autowired
	private DiscountRepo discountRepo;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) {
		List<Discount> discounts = discountRepo.findAllByDate(new Date());
		System.out.println("SIZE: " + discounts.size());
		Map<Item, Integer> itemsOnDiscount = new HashMap<>();
		
		for (Discount d : discounts) {
			for (Item i : d.getItems()) {
				itemsOnDiscount.put(i, d.getPercent());
			}
		}
		
		model.addAttribute("items", itemsOnDiscount);
		
		return "home";
	}
	
	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String account() {
		return "account";
	}

}

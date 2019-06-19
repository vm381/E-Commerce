package ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ecommerce.repository.CategoryRepo;
import ecommerce.repository.ItemRepo;
import ecommerce.repository.UserRepo;
import ecommerce.repository.WishlistRepo;
import ecommerce.validation.ItemValidator;
import model.Category;
import model.Item;
import model.User;

@Controller
@RequestMapping(value = "product")
public class ProductController {

	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private WishlistRepo wishRepo;
	@Autowired
	private UserRepo userRepo;

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String products(Model model) {
		List<Item> items = itemRepo.findAll();

		model.addAttribute("items", items);

		return "admin/products";
	}

	@RequestMapping(value = "admin/insert", method = RequestMethod.GET)
	public String insert(Model model) {

		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("item", new Item());

		return "admin/insertItem";
	}

	@RequestMapping(value = "admin/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("item") Item item, BindingResult result, MultipartFile image, Model model,
			HttpServletRequest req) throws IOException {
		String fileName = image.getOriginalFilename();
		String filePath = req.getServletContext().getRealPath("/") + "images/";
		String relativePath = req.getServletContext().getContextPath() + "/images/";

		item.setPicture(relativePath + fileName);

		ItemValidator validator = new ItemValidator();
		validator.validate(item, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("item", item);
			model.addAttribute("categories", categoryRepo.findAll());

			return "admin/insertItem";
		}

		try {
			File imageFile = new File(filePath, fileName);
			MultipartFile file = image;
			file.transferTo(imageFile);

			itemRepo.save(item);
			model.addAttribute("success", "Product successfully inserted.");
			model.addAttribute("categories", categoryRepo.findAll());
			return "admin/insertItem";
		} catch (Exception e) {
			model.addAttribute("errMsg", "Failed to insert product.");
			model.addAttribute("categories", categoryRepo.findAll());
			return "admin/insertItem";
		}
	}

	@RequestMapping(value = "admin/details", method = RequestMethod.GET)
	public String details(int idItem, Model model, HttpServletRequest req) {
		Optional<Item> item = itemRepo.findById(idItem);

		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product with given ID does not exist.");
			return "admin/itemDetails";
		}

		model.addAttribute("item", item.get());

		return "admin/itemDetails";
	}

	@RequestMapping(value = "admin/confirm", method = RequestMethod.GET)
	public String confirm(int idItem, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);

		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product with given ID does not exist.");
			return "admin/removeItem";
		}

		model.addAttribute("item", item.get());

		return "admin/removeItem";
	}

	@RequestMapping(value = "admin/remove", method = RequestMethod.GET)
	public String remove(int idItem, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);

		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product with given ID does not exist.");
			return "admin/removeItem";
		}

		Item i = item.get();
		i.setStock(0);
		itemRepo.save(i);

		return "redirect:/product/admin/";
	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public String edit(int idItem, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);

		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product with given ID does not exist.");
			return "admin/editProduct";
		}

		model.addAttribute("item", item.get());
		model.addAttribute("categories", categoryRepo.findAll());

		return "admin/editProduct";
	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.POST)
	public String save(@ModelAttribute("item") Item item, BindingResult result, Model model) {
		System.out.println(item.getPicture());
		ItemValidator validator = new ItemValidator();
		validator.validate(item, result);
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errMsg", msg);
			model.addAttribute("item", item);
			model.addAttribute("categories", categoryRepo.findAll());

			return "admin/editProduct";
		}
		
		try {
			itemRepo.save(item);
			model.addAttribute("success", "Product successfully edited.");
			model.addAttribute("categories", categoryRepo.findAll());
			return "admin/editProduct";
		} catch (Exception e) {
			model.addAttribute("errMsg", "Failed to edit product.");
			return "admin/editProduct";
		}
	}
	
	@RequestMapping(value = "user/showProducts", method = RequestMethod.GET)
	public String showProducts(int idCategory, Model model) {
		Optional<Category> category = categoryRepo.findById(idCategory);
		if (!category.isPresent()) {
			model.addAttribute("errMsg", "Category not found.");
			
			return "user/products";
		}
		
		List<Item> items = itemRepo.findByCategory(category.get());
		
		model.addAttribute("items", items);
		model.addAttribute("category", category.get());
		
		return "user/products";
	}
	
	@RequestMapping(value = "user/details", method = RequestMethod.GET)
	public String productDetails(int idItem, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);
		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product not found.");
			return "user/productDetails";
		}
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();
		
		model.addAttribute("item", item.get());
		model.addAttribute("wish", wishRepo.findByItemAndUser(item.get(), user));
				
		return "user/productDetails";
	}

}

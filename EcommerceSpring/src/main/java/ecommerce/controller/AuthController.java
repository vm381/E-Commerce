package ecommerce.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.RoleRepo;
import ecommerce.repository.UserRepo;
import ecommerce.validation.UserValidator;
import model.Item;
import model.Role;
import model.User;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRepo userRepo;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest req) {
		req.getSession().setAttribute("cart", new ArrayList<Item>());
		
		return "login";
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "home";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();

		model.addAttribute("user", user);

		return "register";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, Model model, BindingResult result) {
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, result);
		
		if (result.hasErrors()) {
			String msg = "";
			for (FieldError err : result.getFieldErrors()) {
				msg += err.getCode() + "<br>";
			}
			model.addAttribute("errorMsg", msg);
			return "register";
		}
		
		Optional<User> u = userRepo.findById(user.getUsername());
		if (u.isPresent()) {
			model.addAttribute("errorMsg", "Username already exist.");
			return "register";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Role role = null;
		if (user.getUsername().equals("admin")) {
			role = roleRepo.findByName("ADMIN");
		}
		else {
			role = roleRepo.findByName("USER");
		}
		role.addUser(user);
		user.addRole(role);
		System.out.println(user.getUsername());
		//try {
			userRepo.save(user);
			return "login"; /*
		}
		catch (Exception e) {
			model.addAttribute("errorMsg", "Registration failed.");
			return "register";
		}*/
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		
		return "redirect:/auth/login";
	}

}

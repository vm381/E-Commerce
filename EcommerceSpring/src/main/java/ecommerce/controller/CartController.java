package ecommerce.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecommerce.repository.ItemRepo;
import ecommerce.repository.PurchaseRepo;
import ecommerce.repository.UserRepo;
import ecommerce.repository.WishlistRepo;
import model.Discount;
import model.Item;
import model.Purchase;
import model.User;
import model.Wishlist;

@Controller
@RequestMapping(value = "cart")
public class CartController {

	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private WishlistRepo wishRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PurchaseRepo purRepo;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String cart(HttpServletRequest req, Model model) {
		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) req.getSession().getAttribute("cart");

		model.addAttribute("cartItems", getTodayDiscounts(cart));

		return "user/cart";
	}

	private Map<Item, Integer> getTodayDiscounts(List<Item> items) {
		Map<Item, Integer> discounts = new HashMap<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateToday = sdf.format(new Date());
			Date today = sdf.parse(dateToday);
			for (Item i : items) {
				discounts.put(i, 0);

				for (Discount d : i.getDiscounts()) {
					Date date = d.getDate();

					if (date.equals(today)) {
						discounts.put(i, d.getPercent());
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return discounts;
	}

	@RequestMapping(value = "addToWishlist", method = RequestMethod.GET)
	public String addToWishlist(int idItem, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);
		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Product not found.");
			return "user/productDetails";
		}

		UserDetails currentUser = (UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String username = currentUser.getUsername();
		User user = userRepo.findById(username).get();

		Wishlist wish = new Wishlist();
		wish.setDateAdded(new Date());
		wish.setItem(item.get());
		wish.setUser(user);

		wish = wishRepo.save(wish);

		model.addAttribute("item", item.get());
		model.addAttribute("wish", wish);

		return "user/productDetails";
	}

	@RequestMapping(value = "removeFromWishlist", method = RequestMethod.GET)
	public String removeFromWishlist(int idItem, String calledFrom, Model model) {
		Optional<Item> item = itemRepo.findById(idItem);
		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Item not found.");
			if (calledFrom.equals("details")) {
				return "user/productDetails";
			} else if (calledFrom.equals("wishlist")) {
				return "user/wishlist";
			} else {
				return "home";
			}
		}

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();

		wishRepo.deleteByUserAndItem(user, item.get());

		if (calledFrom.equals("details")) {
			model.addAttribute("item", item.get());

			return "user/productDetails";
		} else if (calledFrom.equals("wishlist")) {
			model.addAttribute("wishlist", wishRepo.findByUser(user));

			return "user/wishlist";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "wishlist", method = RequestMethod.GET)
	public String wishlist(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();

		model.addAttribute("wishlist", wishRepo.findByUser(user));

		return "user/wishlist";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(int idItem, String calledFrom, Model model, HttpServletRequest req) {
		Optional<Item> item = itemRepo.findById(idItem);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();
		List<Wishlist> wishlist = wishRepo.findByUser(user);

		if (!item.isPresent() || item.get().getStock() < 1) {
			model.addAttribute("errMsg", "Item is not on stock.");
			if (calledFrom.equals("details")) {
				return "user/productDetails";
			} else if (calledFrom.equals("wishlist")) {
				model.addAttribute("wishlist", wishlist);
				return "user/wishlist";
			} else {
				return "home";
			}
		}

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) req.getSession().getAttribute("cart");
		cart.add(item.get());
		req.getSession().setAttribute("cart", cart);

		if (calledFrom.equals("details")) {
			model.addAttribute("item", item.get());
			return "user/productDetails";
		} else if (calledFrom.equals("wishlist")) {
			model.addAttribute("wishlist", wishlist);
			return "user/wishlist";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String remove(int idItem, String calledFrom, Model model, HttpServletRequest req) {
		Optional<Item> item = itemRepo.findById(idItem);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();
		List<Wishlist> wishlist = wishRepo.findByUser(user);

		if (!item.isPresent()) {
			model.addAttribute("errMsg", "Item not found.");

			if (calledFrom.equals("details")) {
				return "user/productDetails";
			} else if (calledFrom.equals("cart")) {
				return "user/cart";
			} else if (calledFrom.equals("wishlist")) {
				model.addAttribute("wishlist", wishlist);
				return "user/wishlist";
			}
		}

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) req.getSession().getAttribute("cart");
		cart.remove(item.get());
		req.getSession().setAttribute("cart", cart);

		if (calledFrom.equals("details")) {
			model.addAttribute("item", item.get());
			return "user/productDetails";
		} else if (calledFrom.equals("wishlist")) {
			model.addAttribute("wishlist", wishlist);
			return "user/wishlist";
		} else if (calledFrom.equals("cart")) {
			return "user/cart";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkout(HttpServletRequest req, Model model) {
		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) req.getSession().getAttribute("cart");
		
		if (cart.isEmpty()) {
			model.addAttribute("errMsg", "Your cart is empty.");
			return "user/cart";
		}
		
		Map<Item, Integer> discounts = getTodayDiscounts(cart);
		
		Purchase checkout = new Purchase();
		double total = 0;
		for (Entry<Item, Integer> entry : discounts.entrySet()) {
			Item current = entry.getKey();
			total += current.getPrice();
			current.setStock(current.getStock() - 1);
			current = itemRepo.save(current);
			current.addPurchase(checkout);
			checkout.addItem(current);
			cart.remove(current);
		}
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findById(userDetails.getUsername()).get();
		
		checkout.setUser(user);
		checkout.setTotal(total);
		checkout.setDate(new Date());
		
		purRepo.save(checkout);
		
		model.addAttribute("success", "Checkout is sent to administrators.");
				
		return "user/cart";
	}

}

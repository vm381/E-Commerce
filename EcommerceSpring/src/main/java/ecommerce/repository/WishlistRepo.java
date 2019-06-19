package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import model.Item;
import model.User;
import model.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
	Wishlist findByItemAndUser(Item i, User u);
	@Transactional
	Integer deleteByUserAndItem(User u, Item i);
	List<Wishlist> findByUser(User u);
}

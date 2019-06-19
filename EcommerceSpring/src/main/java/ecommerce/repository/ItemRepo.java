package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import model.Category;
import model.Discount;
import model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {
	
	List<Item> findByDiscounts(Discount d);
	@Transactional
	Integer deleteByCategory(Category c);
	List<Item> findByCategory(Category c);

}

package ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Discount;

public interface DiscountRepo extends JpaRepository<Discount, Integer> {

	List<Discount> findAllByDate(Date d);
	
}

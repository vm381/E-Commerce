package ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

	List<Purchase> findAllByDate(Date d);
	
}

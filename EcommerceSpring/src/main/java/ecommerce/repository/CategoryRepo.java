package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	List<Category> findByName(String name);

}

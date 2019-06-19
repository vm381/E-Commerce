package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepo extends JpaRepository<User, String> {

}

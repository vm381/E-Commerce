package ecommerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecommerce.repository.UserRepo;
import model.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findById(username);
		
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		
		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUsername(user.get().getUsername());
		userDetails.setPassword(user.get().getPassword());
		userDetails.setRoles(user.get().getRoles());
		userDetails.setFirstName(user.get().getFirstName());
		userDetails.setLastName(user.get().getLastName());
		userDetails.setBirthday(user.get().getBirthday());
		userDetails.setAddress(user.get().getAddress());
		userDetails.setCity(user.get().getCity());
		userDetails.setCountry(user.get().getCountry());
		userDetails.setPhone(user.get().getPhone());
		
		return userDetails;
	}

}

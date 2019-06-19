package ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/auth/**")
			.permitAll()
			.antMatchers("/home/**")
			.hasAnyRole("USER", "ADMIN")
			.antMatchers("/category/admin/**", "/product/admin/**", "/discount/admin/**", "/report/admin/**")
			.hasRole("ADMIN")
			.antMatchers("/category/user/**", "/product/user/**", "/cart/**", "/discount/user/**", "/report/user/**")
			.hasRole("USER")
			.and()
			.formLogin()
			.loginPage("/auth/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home");
	}

}

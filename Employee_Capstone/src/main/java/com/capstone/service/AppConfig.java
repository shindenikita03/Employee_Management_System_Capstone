package com.capstone.service;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
 
@Configuration
public class AppConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder().
                username("Nikita@gmail.com")
                .password(passwordEncoder().encode("Nikita")).roles("ADMIN").
                build();
		UserDetails user2 = User.builder().
                username("aditya@gmail.com")
                .password(passwordEncoder().encode("aditya")).roles("EMPLOYEE").
                build();
		UserDetails user3 = User.builder().
                username("rahul@gmail.com")
                .password(passwordEncoder().encode("rahul")).roles("ADMIN").
                build();

 
		UserDetails user4 = User.builder().
                username("adi@gmail.com")
                .password(passwordEncoder().encode("adi")).roles("ADMIN").
                build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}
 
	
}
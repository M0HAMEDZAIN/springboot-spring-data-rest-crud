package com.spring.employee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager detailsManager() {
		UserDetails zain = User.builder().username("zain").password("{noop}1234567").roles("Employee").build();

		return new InMemoryUserDetailsManager(zain);
	}
}

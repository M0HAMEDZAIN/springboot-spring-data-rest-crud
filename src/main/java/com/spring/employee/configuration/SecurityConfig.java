package com.spring.employee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager detailsManager() {
		UserDetails zain = User.builder().username("zain").password("{noop}1234567").roles("EMPLOYEE").build();
		return new InMemoryUserDetailsManager(zain);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//set authorization roles
		http.authorizeHttpRequests(configurer -> configurer
				.requestMatchers(HttpMethod.GET, "spring/api/employees").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "spring/api/employees/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.PUT, "spring/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.POST, "spring/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "spring/api/employees/**").hasRole("ADMIN"));

		//use HTTP basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		//disable Cross Site Request Forgery (CSRf)
		http.csrf(csrf->csrf.disable());
		
		return http.build();
	}
}

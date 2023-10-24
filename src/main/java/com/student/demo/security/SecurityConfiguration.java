package com.student.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/std/insert"))
		.hasRole("principle")
		.requestMatchers(new AntPathRequestMatcher("/std/del/{id}"))
		.hasRole("principle")
		.requestMatchers(new AntPathRequestMatcher("/std/put/{id}"))
		.hasAnyRole("teacher","principle")
		.requestMatchers(new AntPathRequestMatcher("/std/get/{id}"))
		.hasAnyRole("teacher","principle")
		.requestMatchers(new AntPathRequestMatcher("/std/getAdd/{add}"))
		.hasRole("principle")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		
		return httpSecurity.build();
		
	}
	
    @Bean
	public UserDetailsService users() {
		UserDetails staff = User.builder()
				.username("teaching")
				.password(passwordEncoder().encode("test123"))
				.roles("teacher")
				.build();
		UserDetails Admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("test456"))
				.roles("principle")
				.build();
		return new InMemoryUserDetailsManager(staff,Admin);
	}
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}

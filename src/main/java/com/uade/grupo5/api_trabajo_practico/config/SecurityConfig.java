package com.uade.grupo5.api_trabajo_practico.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uade.grupo5.api_trabajo_practico.repositories.entities.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private final JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers("/auth/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.DELETE, "/buy/**").hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.DELETE, "/product/**").hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.PUT, "/product/**").hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.POST, "/product/**").hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers("/category/**").hasAnyAuthority(Role.ADMIN.name())
						.anyRequest()
						.authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}

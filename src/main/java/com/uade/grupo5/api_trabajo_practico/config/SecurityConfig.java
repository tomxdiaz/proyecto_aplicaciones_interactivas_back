package com.uade.grupo5.api_trabajo_practico.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
				.authorizeHttpRequests(req -> req
						// El orden es importante -> se verifican en orden de definicion
						// Auth
						.requestMatchers("/auth/**").permitAll()
						// Mockup
						.requestMatchers("/mockup/**").authenticated()
						// User
						.requestMatchers("/user/**").authenticated()
						// Product
						.requestMatchers(HttpMethod.GET, "/product/**").permitAll()
						.requestMatchers("/product/**").hasAuthority(Role.ADMIN.name())
						// Category
						.requestMatchers("/category/**").permitAll()

						// El resto de rutas aca
						// Cart
						.requestMatchers("/cart/**").authenticated()
						// WishList
						.requestMatchers("/wishlist/**").authenticated()
						// Searches
						.requestMatchers("/search/**").authenticated()
						// Default
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// Configuracion de CORS
	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(List.of("http://localhost:3000")); // Origen permitido
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
		corsConfig.setAllowCredentials(true); // Si necesitas enviar cookies o autenticación
		corsConfig.addAllowedHeader("*"); // Permite todos los headers

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

}

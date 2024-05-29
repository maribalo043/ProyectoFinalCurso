package com.mario.proyect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(
			HttpSecurity http,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) throws Exception {

		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}

	/* Acceso a Rutas */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers("/").permitAll()
				.requestMatchers("/jugador/del/**", "/equipo/del/**", "/categoria/del/**", "/partido/del/**", "/jugador/edit/**", "/equipo/edit/**", "/categoria/edit/**", "/partido/edit/**")
				.hasAuthority("ADMIN")
				.and()
				.formLogin();
		// .and()
		// .formLogin()
		// .loginPage("/login") // Especificar la ruta de la página de inicio de sesión
		// personalizada
		// .permitAll()
		// .defaultSuccessUrl("/ruta-deseada-al-iniciar-sesion") // Redirigir al usuario
		// después de iniciar sesión
		// .and()
		// .logout()
		// .logoutUrl("/logout") // Especificar la ruta de la página de cierre de sesión
		// personalizada
		// .permitAll()
		// .logoutSuccessUrl("/ruta-deseada-al-cerrar-sesion"); // Redirigir al usuario
		// después de cerrar sesión
		return http.build();
	}

}

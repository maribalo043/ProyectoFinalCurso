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
	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests(requests -> requests
                .requestMatchers("/","/mailForm","/nuestrosEquipos","/links","/torneo/**","/formContacto","/login","/registrer").permitAll()
				.requestMatchers("/equipo/jugador/**","/jugadorTorneo/save","/inscripcion/equipo","/equipoTorneo/save","/enviar","/cambioContrasenia","/inscrito","/logout","/home","/cambioContrasenia","/formularioCambioContraseña","/guardarModificacionUser").authenticated()
                .requestMatchers("/categorias","/categoria/**","/jugadores","/jugador/**","/equipos","/equipo/**","/partidos","/partido/**","/estadisticas","/usuarios","/usuario/**")
                .hasAuthority("ADMIN"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll());
		return http.build();
	}

}
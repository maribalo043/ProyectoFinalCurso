package com.mario.proyect.usuario;

import java.util.ArrayList;

import jakarta.persistence.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Id;

@Entity
public class Usuario implements UserDetails{
    
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String usuario;
	private String password;

	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		ArrayList<SimpleGrantedAuthority> permisos = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority permiso;
		if(usuario.compareTo("mario")==0) {
			permiso = new SimpleGrantedAuthority("ADMIN");
		}
		else {
			permiso = new SimpleGrantedAuthority("USER");
		}
		permisos.add(permiso);
		
		return permisos;
	}
	
	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}

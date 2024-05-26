package com.mario.proyect.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mario.proyect.usuario.Usuario;
import com.mario.proyect.usuario.UsuarioDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
	private UsuarioDAO usuarioDao;
	
	@SuppressWarnings("null")
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioDao.findById(username);
		if(usuario.isPresent()) {
			return (UserDetails)usuario.get();
		}
		throw new UsernameNotFoundException(username);
	}
}

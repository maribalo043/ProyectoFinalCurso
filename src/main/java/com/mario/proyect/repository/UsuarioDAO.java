package com.mario.proyect.repository;

import org.springframework.data.repository.CrudRepository;

import com.mario.proyect.entity.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario,String>{

}

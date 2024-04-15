package com.mario.proyect.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDAO extends CrudRepository<Categoria,Long>{

    @Query(value = "SELECT * FROM categorias WHERE activa=true",nativeQuery = true)
    List<Categoria> categoriasActive();
    
}

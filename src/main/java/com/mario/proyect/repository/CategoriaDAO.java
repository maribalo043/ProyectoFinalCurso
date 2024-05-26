package com.mario.proyect.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.equipo.Equipo;

public interface CategoriaDAO extends CrudRepository<Categoria,Long>{

    @Query(value = "SELECT * FROM categorias WHERE activa=true",nativeQuery = true)
    List<Categoria> categoriasActive();
    
    @Query(value = "SELECT e.* FROM equipos e where e.categoria = :nombreCategoria",nativeQuery = true)
    List<Equipo> findEquiposByNombreCategoria(@Param("nombreCategoria") String nombreCategoria);
}

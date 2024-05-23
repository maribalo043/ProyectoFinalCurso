package com.mario.proyect.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.partido.Partido;

public interface CategoriaDAO extends CrudRepository<Categoria,Long>{

    @Query(value = "SELECT * FROM categorias WHERE activa=true",nativeQuery = true)
    List<Categoria> categoriasActive();

    @Query("SELECT p FROM Partido p " +
    "JOIN Equipo eqLocal ON p.equipoLocal.id = eqLocal.id " +
    "JOIN Categoria c ON eqLocal.categoria.id = c.id " +
    "WHERE c.nombre = :nombreCategoria")
    List<Partido> findPartidosByNombreCategoria(@Param("nombreCategoria") String nombreCategoria);


    
}

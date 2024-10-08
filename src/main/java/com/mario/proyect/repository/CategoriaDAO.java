package com.mario.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.entity.Categoria;
import com.mario.proyect.entity.Equipo;

public interface CategoriaDAO extends CrudRepository<Categoria,Long>{

    @Query(value = "SELECT * FROM categorias WHERE activa=true and torneo_sara = true",nativeQuery = true)
    List<Categoria> categoriasActiveSara();

    @Query(value = "SELECT * FROM categorias WHERE activa=true",nativeQuery = true)
    List<Categoria> categoriasActive();
    
    @Query(value = "SELECT e.* FROM equipos e where e.categoria = :nombreCategoria",nativeQuery = true)
    List<Equipo> findEquiposByNombreCategoria(@Param("nombreCategoria") String nombreCategoria);

    @Query("SELECT COUNT(c) FROM Categoria c WHERE (SELECT COUNT(e) FROM c.equipos e) > 5")
    long countCategoriasConMasDeCincoEquipos();

    @Query("SELECT c FROM Categoria c WHERE (SELECT COUNT(e) FROM c.equipos e) > 5")
    List<Categoria> selectCategoriasConMasDeCincoEquipos();

    @Query("SELECT c FROM Categoria c")
    List<Categoria> findAllCategorias();

    @Query(value = "Select * from categorias where torneo_sara = false order by id", nativeQuery = true)
    List<Categoria> findAllCategoriasSeptiembre();
}

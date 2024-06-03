package com.mario.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.entity.Equipo;


public interface EquipoDAO extends CrudRepository<Equipo,Long>{

    @Query(value = "SELECT * FROM equipos ORDER BY categoria_id", nativeQuery = true)
    List<Equipo> findAllByCategoria();

    List<Equipo> findByCategoria_Nombre(String nombre);

    @Query("SELECT COUNT(e) FROM Equipo e")
    long countTotalEquipos();

    @Query("SELECT COUNT(e) FROM Equipo e WHERE e.categoria.id = :categoriaId")
    long countEquiposPorCategoria(@Param("categoriaId") Long categoriaId);

    @Query("SELECT e FROM Equipo e WHERE e.categoria.id = :categoriaId")
    List<Equipo> selectEquiposPorCategoria(@Param("categoriaId") Long categoriaId);
}

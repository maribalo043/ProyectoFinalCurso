package com.mario.proyect.equipo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface EquipoDAO extends CrudRepository<Equipo,Long>{

    @Query(value = "SELECT * FROM equipos ORDER BY categoria_id", nativeQuery = true)
    List<Equipo> findAllByCategoria();

}

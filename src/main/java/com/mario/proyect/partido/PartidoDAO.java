package com.mario.proyect.partido;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.equipo.Equipo;

public interface PartidoDAO extends CrudRepository<Partido,PartidoKey>{

    List<Partido> findByEquipoLocalOrEquipoVisitante(Equipo equipoLocal, Equipo equipoVisitante);

    List<Partido> findByEquipoLocalInOrEquipoVisitanteIn(List<Equipo> equipos, List<Equipo> equipos2);

    @Query(value = "SELECT * FROM partidos WHERE equipo_Local = :equipoId OR equipo_Visitante = :equipoId", nativeQuery = true)
    List<Partido> obtenerPartidosPorEquipo(@Param("equipoId") long equipoId);
    
}

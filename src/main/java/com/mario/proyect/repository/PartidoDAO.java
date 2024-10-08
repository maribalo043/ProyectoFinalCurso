package com.mario.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.entity.Equipo;
import com.mario.proyect.entity.Partido;

public interface PartidoDAO extends CrudRepository<Partido,Long>{

    List<Partido> findByEquipoLocalOrEquipoVisitante(Equipo equipoLocal, Equipo equipoVisitante);

    List<Partido> findByEquipoLocalInOrEquipoVisitanteIn(List<Equipo> equipos, List<Equipo> equipos2);

    @Query(value = "SELECT * FROM partidos WHERE equipo_Local_id = :equipoId OR equipo_Visitante_id = :equipoId", nativeQuery = true)
    List<Partido> obtenerPartidosPorEquipo(@Param("equipoId") long equipoId);
    
    @Query(value = "SELECT p FROM Partido p WHERE (p.id.idEquipoLocal = :idEquipo1 AND p.id.idEquipoVisitante = :idEquipo2) OR (p.id.idEquipoLocal = :idEquipo2 AND p.id.idEquipoVisitante = :idEquipo1)", nativeQuery = true)
    Partido findPartidoEntreEquipos(long idEquipo1, long idEquipo2);

    @Query("SELECT p FROM Partido p WHERE p.equipoLocal.categoria.nombre = :categoria")
    List<Partido> findPartidosByCategoria(@Param("categoria") String categoria);

    @Query("SELECT COUNT(p) FROM Partido p WHERE p.golesLocal = p.golesVisitante")
    long countPartidosEmpatados();

    @Query("SELECT p FROM Partido p WHERE p.golesLocal = p.golesVisitante")
    List<Partido> selectPartidosEmpatados();

    @Query("SELECT COUNT(p) FROM Partido p WHERE p.golesLocal > p.golesVisitante")
    long countPartidosGanadosLocal();

    @Query("SELECT p FROM Partido p WHERE p.golesLocal > p.golesVisitante")
    List<Partido> selectPartidosGanadosLocal();

    @Query("SELECT COUNT(p) FROM Partido p WHERE p.golesVisitante > p.golesLocal")
    long countPartidosGanadosVisitante();

    @Query("SELECT p FROM Partido p WHERE p.golesVisitante > p.golesLocal")
    List<Partido> selectPartidosGanadosVisitante();

    @Query(value="select DISTINCT p.* from Partidos p join Equipos e on p.equipo_local_id = e.id or p.equipo_visitante_id = e.id join Categorias c on e.categoria_id = c.id where c.id = :categoria",nativeQuery = true)
    List<Partido> findAllPartidosSeptiembreByCategoria (long categoria);
}

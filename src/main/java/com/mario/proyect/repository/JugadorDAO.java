package com.mario.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mario.proyect.entity.Jugador;

public interface JugadorDAO extends CrudRepository<Jugador,Long>{
    
    @Query(value = "SELECT * FROM jugadores ORDER BY equipo_id", nativeQuery = true)
    List<Jugador> findAllPorEquipo();

    @Query(value = "SELECT * FROM jugadores ORDER BY talla_Camiseta", nativeQuery = true)
    List<Jugador> findAllPorTallaCamiseta();

    @Query("SELECT COUNT(j) FROM Jugador j WHERE j.edad < :edad")
    long countJugadoresMenoresDeEdad(@Param("edad") int edad);

    @Query("SELECT j FROM Jugador j WHERE j.edad < :edad")
    List<Jugador> selectJugadoresMenoresDeEdad(@Param("edad") int edad);

    @Query(value = "select j.* from jugadores j join equipos e on j.equipo_id = e.id join categorias c on c.id = e.id where j.torneo_sara = false and e.categoria_id = :categoria", nativeQuery = true)
    List<Jugador> findJugadoresByCategoriaSeptiembre(long categoria);

    @Query(value = "select j.* from jugadores j join equipos e on j.equipo_id = e.id where j.activa = true and j.torneo_sara = false and j.goles > 0 and equipo.categoria_id = :categoria", nativeQuery = true)
    List<Jugador> findJugadoresActivoSeptiembreByCategoria(int categoria);

}
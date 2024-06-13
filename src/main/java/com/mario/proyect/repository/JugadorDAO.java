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

}
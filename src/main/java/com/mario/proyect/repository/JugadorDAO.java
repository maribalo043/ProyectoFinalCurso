package com.mario.proyect.jugador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JugadorDAO extends CrudRepository<Jugador,String>{
    
    @Query(value = "SELECT * FROM jugadores ORDER BY equipo_id", nativeQuery = true)
    List<Jugador> findAllPorEquipo();

    @Query(value = "SELECT * FROM jugadores ORDER BY talla_Camiseta", nativeQuery = true)
    List<Jugador> findAllPorTallaCamiseta();

}
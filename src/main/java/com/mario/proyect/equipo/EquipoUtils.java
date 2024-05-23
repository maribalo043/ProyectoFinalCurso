package com.mario.proyect.equipo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import com.mario.proyect.partido.Partido;
import com.mario.proyect.partido.PartidoDAO;

public class EquipoUtils {
    
    public static void ordenarEquipos(Equipo[] equipos, PartidoDAO partidoDao) {
        Arrays.sort(equipos, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo e1, Equipo e2) {
                // Ordenar por puntos de mayor a menor
                if (e1.getPuntos() != e2.getPuntos()) {
                    return Integer.compare(e2.getPuntos(), e1.getPuntos());
                }
                // Si los puntos son iguales, ordenar por gol average
                if (e1.getGolAverage() != e2.getGolAverage()) {
                    return Integer.compare(e2.getGolAverage(), e1.getGolAverage());
                }
                // Si el gol average también es igual, ordenar por el resultado del partido entre ellos
                Optional<Partido> partido = partidoDao.findByIdEquipoLocalAndIdEquipoVisitante(e1.getId(), e2.getId());
                if (partido != null) {
                    if (partido.get().getEquipoLocal().equals(e1) && partido.get().getGolesLocal() > partido.get().getGolesVisitante()) {
                        return -1; // e1 ganó
                    } else if (partido.get().getEquipoVisitante().equals(e1) && partido.get().getGolesVisitante() > partido.get().getGolesLocal()) {
                        return -1; // e1 ganó
                    } else {
                        return 1; // e2 ganó o empate
                    }
                }
                return 0; // No se pudo determinar el ganador
            }
        });
    }
}


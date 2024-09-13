package app;

import modelo.Equipo;
import modelo.Posicion;

import java.time.LocalDate;

public class EquipoApp {

    public static void main(String[] args) throws Exception {

        Equipo equipo = new Equipo();
        equipo.contratarJugador("Juan", Posicion.ARQ, 1000000, 5, LocalDate.now());
        equipo.contratarJugador("Pedro", Posicion.ARQ, 1000000, 2, LocalDate.now());
        equipo.contratarJugador("Lucas", Posicion.ARQ, 1000000, 2, LocalDate.now());
        equipo.contratarJugador("Pedro", Posicion.ARQ, 2000000, 7, LocalDate.now());
        equipo.contratarJugador("Camilo", Posicion.DEF, 2000000, 2, LocalDate.now());

        System.out.println(equipo.despedirJugadores());

        System.out.println(equipo.getValorPlantilla());

        System.out.println(equipo.calcularPorcentajes());

    }

}

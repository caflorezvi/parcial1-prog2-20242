import modelo.Equipo;
import modelo.Jugador;
import modelo.Posicion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipoTest {

    @Test
    public void rebajarSueldoTest() throws Exception{

        //Se crea un equipo
        Equipo equipo = new Equipo();
        //Se contrata un jugador, con las caracteristicas necesarias para que sea candidato a que le rebajen el sueldo
        Jugador jugador = equipo.contratarJugador(
                "pepito",
                Posicion.ARQ,
                100,
                4,
                LocalDate.of(2019, 10, 12)
        );

        //Se rebaja el sueldo del jugador
        equipo.rebajarSueldos();
        //Se espera que el salario del jugador sea 75 ya que se le rebajo un 25%
        assertEquals(75,jugador.getSalario() );
    }
}

package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Equipo {

    private final List<Jugador> jugadores;
    private float valorPlantilla;
    private final static float SALARIO_TOTAL = 300000000;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    /**
     * Punto 1 del parcial
     * @return Lista de jugadores despedidos
     */
    public List<Jugador> despedirJugadores() {

        //Se crea una lista de jugadores donde se almacenarán los jugadores despedidos
        List<Jugador> jugadoresDespedidos = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (jugador.getCalificacion() <= 3) {
                //Se actualiza el valor de la plantilla
                valorPlantilla -= jugador.getSalario();
                //Se agrega el jugador a la lista de jugadores despedidos
                jugadoresDespedidos.add(jugador);
            }
        }

        //Se eliminan los jugadores despedidos de la lista de jugadores del equipo
        jugadores.removeAll(jugadoresDespedidos);
        //Se retorna la lista de jugadores despedidos
        return jugadoresDespedidos;
    }

    /**
     * Punto 2 del parcial
     * @param nombre del jugador
     * @param posicion del jugador
     * @param salario del jugador
     * @param calificacion del jugador
     * @param fechaContratacion del jugador
     * @throws Exception si no se puede contratar al jugador
     */
    public Jugador contratarJugador(String nombre, Posicion posicion, float salario, float calificacion, LocalDate fechaContratacion) throws Exception{

        //Se valida que el salario total no se exceda
        if (valorPlantilla + salario >= SALARIO_TOTAL) {
            throw new Exception("No se puede contratar al jugador");
        }

        //Se valida si es necesario contratar un jugador en la posicion
        boolean esNecesario = esNecesario(posicion);
        //Se crea el jugador a contratar
        Jugador jugador = new Jugador(nombre, genearNumeroCamiseta(), posicion, salario, calificacion, fechaContratacion);

        if (esNecesario) {
            //Si es necesario se contrata al jugador
            jugadores.add(jugador);
            valorPlantilla += salario;
        } else {
            //Si no es necesario, se busca el jugador peor calificado en la posicion y se compara con el jugador a contratar
            Jugador jugadorPeorCalificado = obtenerJugadorPeorCalificado(posicion);

            //Si el jugador a contratar es mejor que el peor calificado, se despide al peor calificado y se contrata al nuevo jugador
            if(jugadorPeorCalificado.getCalificacion() < calificacion){
                //Se despide al jugador peor calificado
                jugadores.remove(jugadorPeorCalificado);
                //Se actualiza el valor de la plantilla
                valorPlantilla -= jugadorPeorCalificado.getSalario();
                //Se contrata al nuevo jugador
                jugadores.add(jugador);
                //Se actualiza el valor de la plantilla
                valorPlantilla += salario;
            }else{
                throw new Exception("No se puede contratar al jugador");
            }

        }

        return jugador;
    }

    /**
     * Verifica si es necesario contratar un jugador en una posicion determinada
     * @param posicion del jugador
     * @return true si es necesario contratar un jugador, false en caso contrario
     */
    private boolean esNecesario(Posicion posicion) {

        int cantidadJugadores = 0;

        for (Jugador jugador : jugadores) {
            if (jugador.getPosicion().equals(posicion)) {
                cantidadJugadores++;
            }
        }

        //Se valida si la cantidad de jugadores en la posicion es menor al maximo permitido
        return cantidadJugadores < posicion.maximo;

    }

    /**
     * Obtiene el jugador peor calificado en una posicion determinada del equipo
     * @param posicion del jugador
     * @return Jugador peor calificado
     */
    private Jugador obtenerJugadorPeorCalificado(Posicion posicion) {

        //Se inicializa el jugador peor calificado con el primer jugador de la lista
        Jugador peorCalificado = jugadores.get(0);

        for (Jugador jugador : jugadores) {
            //Se compara la calificación del jugador con la calificación del peor calificado
            if (jugador.getPosicion() == posicion && jugador.getCalificacion() < peorCalificado.getCalificacion()) {
                peorCalificado = jugador;
            }
        }

        return peorCalificado;
    }

    /**
     * Genera un numero de camiseta aleatorio para el jugador a contratar
     * @return Numero de camiseta
     */
    private int genearNumeroCamiseta(){

        //Se genera un numero de camiseta aleatorio
        int numCamiseta = new Random().nextInt(40)+1;

        //Se valida que el numero de camiseta no exista en la lista de jugadores
        while(buscarJugador(numCamiseta)){
            numCamiseta = new Random().nextInt(40)+1;
        }

        return numCamiseta;

    }

    /**
     * Busca un jugador en la lista de jugadores por su numero de camiseta
     * @param numCamiseta del jugador
     * @return true si el jugador existe, false en caso contrario
     */
    private boolean buscarJugador(int numCamiseta){
        for (Jugador jugador : jugadores) {
            if(jugador.getNumCamiseta() == numCamiseta){
                return true;
            }
        }
        return false;
    }

    /**
     * Punto 3 del parcial
     * @return RespuestaPorcentajes con los porcentajes de salario por posicion
     */
    public RespuestaPorcentajes calcularPorcentajes() {

        //Se calcula el total de salarios del equipo
        float total = calcularTotalSalarios();

        //Se calcula el porcentaje de salario por posicion
        float porcentajeARQ = calcularPorcentaje(Posicion.ARQ, total);
        float porcentajeDEF = calcularPorcentaje(Posicion.DEF, total);
        float porcentajeMED = calcularPorcentaje(Posicion.MED, total);
        float porcentajeDEL = calcularPorcentaje(Posicion.DEL, total);

        //Se retorna la respuesta en un objeto con los porcentajes de cada posición
        return new RespuestaPorcentajes(porcentajeARQ, porcentajeDEF, porcentajeMED, porcentajeDEL);

    }

    /**
     * Calcula el porcentaje de salario dado una posicion del jugador
     * @param posicion del jugador
     * @param total de salarios del equipo
     * @return Porcentaje de salario
     */
    private float calcularPorcentaje(Posicion posicion, float total){

        float suma = 0;

        for (Jugador jugador : jugadores) {
            if(jugador.getPosicion() == posicion){
                suma += jugador.getSalario();
            }
        }

        return (suma / total) * 100;
    }

    /**
     * Calcula el total de salarios del equipo
     * @return Total de salarios
     */
    private float calcularTotalSalarios(){
        float total = 0;
        for (Jugador jugador : jugadores) {
            total += jugador.getSalario();
        }
        return total;
    }

    /**
     * Punto 4 del parcial
     */
    public void rebajarSueldos(){

        for (Jugador jugador : jugadores) {
            //Si la calificación del jugador es mayor a 3 y menor a 5 y la fecha de contratación es mayor a 2 años
            if( jugador.getCalificacion()>3 && jugador.getCalificacion()<5 && (jugador.getFechaContratacion().isBefore(LocalDate.now().minusYears(2)) )){
                //Se rebaja el salario en un 25%
                float rebajaSalario = jugador.getSalario() * 0.25f;
                //Se calcula el nuevo salario
                float nuevoSalario = jugador.getSalario() - rebajaSalario;
                //Se actualiza el salario del jugador
                jugador.setSalario(nuevoSalario);
                //Se actualiza el valor de la plantilla
                valorPlantilla -= rebajaSalario;
            }
        }

    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public float getValorPlantilla() {
        return valorPlantilla;
    }

}

package modelo;

import java.time.LocalDate;

public class Jugador {

    private String nombre;
    private int numCamiseta;
    private Posicion posicion;
    private float salario;
    private float calificacion;
    private LocalDate fechaContratacion;

    public Jugador(String nombre, int numCamiseta, Posicion posicion, float salario, float calificacion, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.numCamiseta = numCamiseta;
        this.posicion = posicion;
        this.salario = salario;
        this.calificacion = calificacion;
        this.fechaContratacion = fechaContratacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumCamiseta() {
        return numCamiseta;
    }

    public void setNumCamiseta(int numCamiseta) {
        this.numCamiseta = numCamiseta;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", numCamiseta=" + numCamiseta +
                ", posicion=" + posicion +
                ", salario=" + salario +
                ", calificacion=" + calificacion +
                ", fechaContratacion=" + fechaContratacion +
                '}';
    }
}



package modelo;

public enum Posicion {

    ARQ(3), DEF(8), MED(8), DEL(6);

    final int maximo;

    Posicion(int maximo) {
        this.maximo = maximo;
    }
}

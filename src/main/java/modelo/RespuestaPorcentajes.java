package modelo;

public class RespuestaPorcentajes {
    private final float porcentajeArquero;
    private final float porcentajeDefensor;
    private final float porcentajeMediocampista;
    private final float porcentajeDelantero;

    public RespuestaPorcentajes(float porcentajeArquero, float porcentajeDefensor, float porcentajeMediocampista, float porcentajeDelantero) {
        this.porcentajeArquero = porcentajeArquero;
        this.porcentajeDefensor = porcentajeDefensor;
        this.porcentajeMediocampista = porcentajeMediocampista;
        this.porcentajeDelantero = porcentajeDelantero;
    }

    @Override
    public String toString() {
        return "RespuestaPorcentajes{" +
                "porcentajeArquero=" + porcentajeArquero +
                ", porcentajeDefensor=" + porcentajeDefensor +
                ", porcentajeMediocampista=" + porcentajeMediocampista +
                ", porcentajeDelantero=" + porcentajeDelantero +
                '}';
    }
}

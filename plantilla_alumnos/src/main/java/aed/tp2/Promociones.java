package aed.tp2;

public class Promociones {
    String nombre;
    double factor;
    double tope;
    double acumuladas;
    boolean activa;

    public Promociones(String nombre, double factor, double tope) {
        this.nombre = nombre;
        this.factor = factor;
        this.tope = tope;
        this.acumuladas = 0;
        this.activa = true;
    }
}

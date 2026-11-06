package aed.tp2;

public class Promocion {
    String nombre;
    double factor;
    double tope;
    double acumuladas;
    boolean activa;

    public Promocion(double factor, double tope) {
        this.factor = factor;
        this.tope = tope;
        this.acumuladas = 0;
        this.activa = true;
    }
}

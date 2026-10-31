package aed.tp2;

public class Usuarios implements Comparable<Usuarios>{
    private int usuarioId;
    public double millasTotales;
    public double millasDisponibles;
    private double canjeadas; // canjeadas y transferidas
    private int contacto; // a quien transfirio o de quien recibio
    private boolean multiplesContactos; //le transfirio/recibio a varios 


    public Usuarios(int usuarioId) {
        this.usuarioId = usuarioId;
        this.millasTotales = 0;
        this.millasDisponibles = 0;
        this.canjeadas = 0;
        this.multiplesContactos = false;
        this.contacto = -1;
    }

   
    // Si empatan, "gana" el de id menor.
    public int compareTo(Usuarios otro) {
        if (this.millasDisponibles > otro.millasDisponibles) {
            return 1;
        }
        else if (this.millasDisponibles < otro.millasDisponibles) {
            return -1;
        }
        if (this.usuarioId < otro.usuarioId) {
            return 1;
        }
        else if (this.usuarioId > otro.usuarioId) {
            return -1;   
        }
        return 0;
    }

    public String categoria() {
        if (millasTotales >= 1000000) {
            return "Platino";
        }
        else if (millasTotales >= 50000) {
            return "Oro";
        }
        else if (millasTotales >= 10000) {
            return "Plata";
        }
        else{
            return "Bronce";
        }
    }

    public double factorCategoria() {
        if (millasTotales >= 1000000) {
            return 0;
        }
        else if (millasTotales >= 50000) {
            return 2;
        }
        if (millasTotales >= 10000) {
            return 1.5;
        }
        else {
            return 1;
        }
    }

    public void agregarContacto(Usuarios otro) {
        if (contacto == -1) {
            contacto = otro.usuarioId;
        } 
        else if (contacto != otro.usuarioId) {
            multiplesContactos = true; 
        }
    }

    public boolean exclusivoCon(Usuarios otro) {
        return contacto == -1 && !multiplesContactos && contacto == otro.usuarioId;
    }
}

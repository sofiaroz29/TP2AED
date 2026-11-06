package aed.tp2;

public class Usuario implements Comparable<Usuario>{
    public int usuarioId;
    public double millasTotales;
    public double millasDisponibles;
    public double canjeadas; // canjeadas y transferidas
    public  Usuario contacto; // a quien transfirio o de quien recibio
    public  boolean multiplesContactos; //le transfirio/recibio a varios 

    public MaxHeap<Usuario>.Handle handleHeap;


    public Usuario(int usuarioId) {
        this.usuarioId = usuarioId;
        this.millasTotales = 0;
        this.millasDisponibles = 0;
        this.canjeadas = 0;
        this.multiplesContactos = false;
        this.contacto = null;
        this.handleHeap = null;
    }


    public int compareTo(Usuario otro) {
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

    public void agregarContacto(Usuario otro) {
        if (contacto == null) {
            contacto = otro;
        } 
        else if (contacto != otro) {
            multiplesContactos = true; 
        }
    }

   public boolean exclusivoCon(Usuario otro) {
    return !multiplesContactos && !otro.multiplesContactos && contacto == otro &&  otro.contacto == this;
    }
}

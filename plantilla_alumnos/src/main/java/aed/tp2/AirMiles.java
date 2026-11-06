package aed.tp2;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> dictUsuarios;   
    private ABB<String, Promociones> promos;     
    private Usuario maxAcumulador;            // el que más totales tiene
    private Usuario maxCanjeador;             // el que más gastadas tiene

    public AirMiles() {
        dictUsuarios = new ABB<>();
        promos = new ABB<>(); 

        promos.insertar("NoPromo", new Promociones("NoPromo", 1, 0));
    }


    public void registrarUsuario(int id) {
        Usuario u = new Usuario(id);  
        dictUsuarios.insertar(id, u);              
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        registrarUsuario(id);          
        Usuario nuevo = dictUsuarios.obtener(id);
        Usuario referidor = dictUsuarios.obtener(ref);

        nuevo.millasTotales = nuevo.millasTotales + 500;
        nuevo.millasDisponibles = nuevo.millasDisponibles + 500;
        referidor.millasDisponibles = referidor.millasDisponibles +500;
        referidor.millasTotales = referidor.millasTotales+500;
        
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Usuario user = dictUsuarios.obtener(id);
        Promociones promo = promos.obtener(nombrePromo);  


        double factorPromo;
        if (promo != null && promo.activa) {
            factorPromo = promo.factor;
        }
        else {
            factorPromo = 1;
        }

        double millas = distancia * user.factorCategoria() * factorPromo;

        user.millasTotales = user.millasTotales + millas;
        user.millasDisponibles = user.millasDisponibles + millas;

        if (user.millasTotales > maxAcumulador.millasTotales) {
            maxAcumulador = user;
        }

        if (promo != null && promo.activa && promo.tope > 0) {
            promo.acumuladas = promo.acumuladas + millas;
            if (promo.acumuladas >= promo.tope) {
                promo.activa = false;
            }
        }
    }

    public void canjearMillas(int id, double cantidad) {
        Usuario user = dictUsuarios.obtener(id);

        user.millasDisponibles = user.millasDisponibles - cantidad;   
        user.millasTotales = user.canjeadas + cantidad;        

        if (user.canjeadas > maxCanjeador.canjeadas) {
            maxCanjeador = user;
        }

        
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        Usuario userOrigen = dictUsuarios.obtener(origen);
        Usuario userDestino = dictUsuarios.obtener(destino);

        // if (userOrigen.millasDisponibles < cantidad){
        //     throw new Error();
        // } ????

        userOrigen.millasDisponibles = userOrigen.millasDisponibles - cantidad;
        userOrigen.canjeadas = userOrigen.canjeadas + cantidad;
        userDestino.millasDisponibles = userDestino.millasDisponibles + cantidad;
        userDestino.millasTotales = userDestino.millasTotales + cantidad;

        if (userOrigen.canjeadas > maxCanjeador.canjeadas) {
            maxCanjeador = userOrigen;
        }

        if (userDestino.millasTotales > maxAcumulador.millasTotales) {
            maxCanjeador = userDestino;
        }
        
    }

    public double consultarSaldo(int id) {
        Usuario user = dictUsuarios.obtener(id);
        return user.millasDisponibles;
       
    }

    public String consultarCategoria(int id) {
        Usuario user = dictUsuarios.obtener(id);
        return user.categoria();
    }

    public int[] rankingMillas(int n) {
        //heap 
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocion(String nombre, double factor) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void finalizarPromocion(String nombre) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasAcumulador() {
        return maxAcumulador.usuarioId;
        //throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasCanjeador() {
        return maxCanjeador.usuarioId;
        //throw new UnsupportedOperationException("Sin implementar");
    }

    public int[][] paresTransferidoresExclusivos() { 
        throw new UnsupportedOperationException("Sin implementar");
    }
}

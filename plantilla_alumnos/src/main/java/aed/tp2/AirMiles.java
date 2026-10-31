package aed.tp2;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuarios> usuarios;   
    private ABB<String, Promociones> promos;     
    // private MaxHeap<Usuario> ranking;         // usuarios ordenados por saldo
    // private Usuario maxAcumulador;            // el que más "totales" tiene
    // private Usuario maxCanjeador;             // el que más "gastadas" tiene

    public AirMiles() {
        usuarios = new ABB<>();
        promos = new ABB<>();
        // ranking = new MaxHeap<>();
        // maxAcumulador = null;   // todavía no hay usuarios
        // maxCanjeador = null;

        promos.insertar("NoPromo", new Promociones("NoPromo", 1, 0));
    }


    public void registrarUsuario(int id) {
        Usuarios u = new Usuarios(id);              // usuario nuevo, todo en 0
        usuarios.insertar(id, u);                 // lo guardo en el ABB para encontrarlo por id
        // u.handleRanking = ranking.agregar(u);     // lo meto al heap y me guardo su Handle

        // // si es el primer usuario del sistema, por ahora es el que "va ganando"
        // if (maxAcumulador == null) {
        //     maxAcumulador = u;
        //     maxCanjeador = u;
        // }
        // throw new UnsupportedOperationException("Sin implementar");
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        registrarUsuario(id);          
        Usuarios nuevo = usuarios.obtener(id);
        Usuarios referidor = usuarios.obtener(ref);

        nuevo.millasTotales = nuevo.millasTotales + 500;
        nuevo.millasDisponibles = nuevo.millasDisponibles + 500;
        referidor.millasDisponibles = referidor.millasDisponibles +500;
        referidor.millasTotales = referidor.millasTotales+500;
        


        // nuevo.handleRanking.modificar(nuevo);       // su saldo cambió: reacomodo el heap
        // if (nuevo.totales > maxAcumulador.totales) {
        //     maxAcumulador = nuevo;
        // }

        // // el referidor gana 500
        // referidor.totales = referidor.totales + 500;
        // referidor.disponibles = referidor.disponibles + 500;
        // referidor.handleRanking.modificar(referidor);
        // if (referidor.totales > maxAcumulador.totales) {
        //     maxAcumulador = referidor;
        // }
        // throw new UnsupportedOperationException("Sin implementar");
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public String consultarCategoria(int id) {
       throw new UnsupportedOperationException("Sin implementar");
    }

    public int[] rankingMillas(int n) {
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
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasCanjeador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}

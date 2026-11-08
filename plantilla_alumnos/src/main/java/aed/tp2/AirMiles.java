package aed.tp2;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> dictUsuarios;   
    private Trie<Promocion> promos;     
    private Usuario maxAcumulador;            // el que más totales tiene
    private Usuario maxCanjeador;             // el que más gastadas tiene
    private ListaEnlazada<Usuario> listaUsuarios;
    private MaxHeap<Usuario> maxHeap;

    public AirMiles() {
        dictUsuarios = new ABB<>();
        listaUsuarios = new ListaEnlazada<>();
        promos = new Trie<>(); 
        maxAcumulador = null;
        maxCanjeador = null;
        maxHeap = new MaxHeap<>();
        promos.insertar("NoPromo", new Promocion(1, 0));
    }


    public void registrarUsuario(int id) {
        Usuario user = new Usuario(id);  
        dictUsuarios.insertar(id, user);
        listaUsuarios.agregarAtras(user);
        user.handleHeap = maxHeap.agregar(user);

        
        if (maxAcumulador == null) {
            maxAcumulador = user;
        }

        if (maxCanjeador == null) {
            maxCanjeador = user;
        }
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        registrarUsuario(id);          
        Usuario nuevo = dictUsuarios.obtener(id);
        Usuario referidor = dictUsuarios.obtener(ref);

        nuevo.millasTotales += 500;
        nuevo.millasDisponibles += 500;
        nuevo.handleHeap.modificar(nuevo);

        referidor.millasTotales += 500;
        referidor.millasDisponibles += 500;
        referidor.handleHeap.modificar(referidor);


        if (nuevo.millasTotales > maxAcumulador.millasTotales) {
            maxAcumulador = nuevo;
        }
        if (referidor.millasTotales > maxAcumulador.millasTotales) {
            maxAcumulador = referidor;
        }
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Usuario user = dictUsuarios.obtener(id);
        Promocion promo = promos.obtener(nombrePromo);  


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

        user.handleHeap.modificar(user);

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
        user.canjeadas = user.canjeadas + cantidad;
        
        user.handleHeap.modificar(user);

        if (user.canjeadas > maxCanjeador.canjeadas) {
            maxCanjeador = user;
        }

        
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        Usuario userOrigen = dictUsuarios.obtener(origen);
        Usuario userDestino = dictUsuarios.obtener(destino);

        userOrigen.millasDisponibles = userOrigen.millasDisponibles - cantidad;
        userOrigen.canjeadas = userOrigen.canjeadas + cantidad;
        userOrigen.handleHeap.modificar(userOrigen);
        userDestino.millasDisponibles = userDestino.millasDisponibles + cantidad;
        userDestino.handleHeap.modificar(userDestino);


        userOrigen.agregarContacto(userDestino);
        userDestino.agregarContacto(userOrigen);


        if (userOrigen.canjeadas > maxCanjeador.canjeadas) {
            maxCanjeador = userOrigen;
        }

        if (userDestino.millasTotales > maxAcumulador.millasTotales) {
            maxAcumulador = userDestino;
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
       MaxHeap<Usuario> copia = maxHeap.copia();

        if (n > copia.longitud()) {
            n = copia.longitud();
        }

        int[] rankingN = new int[n];

        for (int i=0; i<n; i++) {
            Usuario user = copia.eliminarMaximo();
            rankingN[i] = user.usuarioId;
        }

        return rankingN;
    }

    public void iniciarPromocion(String nombre, double factor) {
        promos.insertar(nombre, new Promocion(factor, 0)); 
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        promos.insertar(nombre, new Promocion(factor, tope));
    }

    public void finalizarPromocion(String nombre) {
        promos.obtener(nombre).activa = false;
    }

    public int elMasAcumulador() {
        return maxAcumulador.usuarioId;
    }

    public int elMasCanjeador() {
        return maxCanjeador.usuarioId;
    }

    public int[][] paresTransferidoresExclusivos() {
        int[][] pares = new int[listaUsuarios.longitud() / 2][2];
        int cantidad = 0;

        ListaEnlazada<Usuario>.ListaIterador it = listaUsuarios.iterador();

        while (it.haySiguiente()) {
            Usuario user = it.siguiente();

            if (user.contacto != null && user.usuarioId < user.contacto.usuarioId &&user.exclusivoCon(user.contacto)) {

                pares[cantidad][0] = user.usuarioId;
                pares[cantidad][1] = user.contacto.usuarioId;
                cantidad++;
            }
        }

        int[][] paresFinal = new int[cantidad][2];

        for (int i = 0; i < cantidad; i++) {
            paresFinal[i][0] = pares[i][0];
            paresFinal[i][1] = pares[i][1];
        }

        return paresFinal;
    }
}

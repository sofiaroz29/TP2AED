package aed.tp2;


public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> dictUsuarios;
    private Trie<Promocion> promos;
    private Usuario maxAcumulador;            
    private Usuario maxCanjeador;             
    private ListaEnlazada<Usuario> listaUsuarios;
    private MaxHeap<Usuario> maxHeap;

    // Requiere: O(1)
    // Nuestro codigo da: O(1)
    public AirMiles() {
        dictUsuarios = new ABB<>();                          // O(1)
        listaUsuarios = new ListaEnlazada<>();               // O(1)
        promos = new Trie<>();                               // O(1)
        maxAcumulador = null;                                // O(1)
        maxCanjeador = null;                                 // O(1)
        maxHeap = new MaxHeap<>();                           // O(1)
        promos.insertar("NoPromo", new Promocion(1, 0));     // O(|"NoPromo"|) = O(1)
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public void registrarUsuario(int id) {
        Usuario user = new Usuario(id);                      // O(1)
        dictUsuarios.insertar(id, user);                     // O(log U) prom, O(U) peor (ABB desbalanceado)
        listaUsuarios.agregarAtras(user);                    // O(1)
        user.handleHeap = maxHeap.agregar(user);             // O(log U) (siempre, el heap está balanceado)

        if (maxAcumulador == null) {                         // O(1)
            maxAcumulador = user;                            // O(1)
        }

        if (maxCanjeador == null) {                          // O(1)
            maxCanjeador = user;                             // O(1)
        }
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public void registrarUsuarioConReferidor(int id, int ref) {
        registrarUsuario(id);                                // O(log U) prom, O(U) peor
        Usuario nuevo = dictUsuarios.obtener(id);            // O(log U) prom, O(U) peor
        Usuario referidor = dictUsuarios.obtener(ref);       // O(log U) prom, O(U) peor

        nuevo.millasTotales += 500;                          // O(1)
        nuevo.millasDisponibles += 500;                      // O(1)
        nuevo.handleHeap.modificar(nuevo);                   // O(log U)

        referidor.millasTotales += 500;                      // O(1)
        referidor.millasDisponibles += 500;                  // O(1)
        referidor.handleHeap.modificar(referidor);           // O(log U)

        if (nuevo.millasTotales > maxAcumulador.millasTotales) {         // O(1)
            maxAcumulador = nuevo;                                       // O(1)
        }
        if (referidor.millasTotales > maxAcumulador.millasTotales) {     // O(1)
            maxAcumulador = referidor;                                   // O(1)
        }
    }

    // Requiere: O(log U + |L|) promedio, O(U + |L|) peor caso
    // Nuestro codigo da: O(log U + |L|) promedio, O(U + |L|) peor caso 
    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Usuario user = dictUsuarios.obtener(id);             // O(log U) prom, O(U) peor
        Promocion promo = promos.obtener(nombrePromo);       // O(|L|)

        double factorPromo;                                  // O(1)
        if (promo != null && promo.activa) {                 // O(1)
            factorPromo = promo.factor;                      // O(1)
        }
        else {
            factorPromo = 1;                                 // O(1)
        }

        double millas = distancia * user.factorCategoria() * factorPromo;   // O(1)

        user.millasTotales = user.millasTotales + millas;                   // O(1)
        user.millasDisponibles = user.millasDisponibles + millas;           // O(1)

        user.handleHeap.modificar(user);                     // O(log U)

        if (user.millasTotales > maxAcumulador.millasTotales) {             // O(1)
            maxAcumulador = user;                                           // O(1)
        }

        if (promo != null && promo.activa && promo.tope > 0) {              // O(1)
            promo.acumuladas = promo.acumuladas + millas;                   // O(1)
            if (promo.acumuladas >= promo.tope) {                           // O(1)
                promo.activa = false;                                       // O(1)
            }
        }
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public void canjearMillas(int id, double cantidad) {
        Usuario user = dictUsuarios.obtener(id);                            // O(log U) prom, O(U) peor

        user.millasDisponibles = user.millasDisponibles - cantidad;         // O(1)
        user.canjeadas = user.canjeadas + cantidad;                         // O(1)

        user.handleHeap.modificar(user);                                    // O(log U)

        if (user.canjeadas > maxCanjeador.canjeadas) {                      // O(1)
            maxCanjeador = user;                                            // O(1)
        }
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public void transferirMillas(int origen, int destino, double cantidad) {
        Usuario userOrigen = dictUsuarios.obtener(origen);                  // O(log U) prom, O(U) peor
        Usuario userDestino = dictUsuarios.obtener(destino);                // O(log U) prom, O(U) peor

        userOrigen.millasDisponibles = userOrigen.millasDisponibles - cantidad;   // O(1)
        userOrigen.canjeadas = userOrigen.canjeadas + cantidad;                   // O(1)
        userOrigen.handleHeap.modificar(userOrigen);                              // O(log U)
        userDestino.millasDisponibles = userDestino.millasDisponibles + cantidad; // O(1)
        userDestino.handleHeap.modificar(userDestino);                            // O(log U)

        userOrigen.agregarContacto(userDestino);             // O(1)
        userDestino.agregarContacto(userOrigen);             // O(1)

        if (userOrigen.canjeadas > maxCanjeador.canjeadas) {                // O(1)
            maxCanjeador = userOrigen;                                      // O(1)
        }

        
        if (userDestino.millasTotales > maxAcumulador.millasTotales) {      // O(1)
            maxAcumulador = userDestino;                                    // O(1)
        }
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public double consultarSaldo(int id) {
        Usuario user = dictUsuarios.obtener(id);             // O(log U) prom, O(U) peor
        return user.millasDisponibles;                       // O(1)
    }

    // Requiere: O(log U) promedio, O(U) peor caso
    // Nuestro codigo da: O(log U) promedio, O(U) peor caso 
    public String consultarCategoria(int id) {
        Usuario user = dictUsuarios.obtener(id);             // O(log U) prom, O(U) peor
        return user.categoria();                             // O(1)
    }

    // Requiere: O(N log U)
    // Nuestro codigo da: O(N log U)
    public int[] rankingMillas(int n) {
        if (n > maxHeap.longitud()) {                        // O(1)
            n = maxHeap.longitud();                          // O(1)
        }

        int[] rankingN = new int[n];                         // O(N)
        Usuario[] extraidos = new Usuario[n];                // O(N)

        for (int i = 0; i < n; i++) {                        // N iteraciones
            Usuario user = maxHeap.eliminarMaximo();         // O(log U)
            rankingN[i] = user.usuarioId;                    // O(1)
            extraidos[i] = user;                             // O(1)
        }                                                    // total del ciclo: O(N log U)

        for (int i = 0; i < n; i++) {                        // N iteraciones
            extraidos[i].handleHeap = maxHeap.agregar(extraidos[i]);   // O(log U), se renueva el handle
        }                                                    // total del ciclo: O(N log U)

        return rankingN;                                     // O(1)
    }

    // Requiere: O(|L|)
    // Nuestro codigo da: O(|L|)
    public void iniciarPromocion(String nombre, double factor) {
        promos.insertar(nombre, new Promocion(factor, 0));   // O(|L|)
    }

    // Requiere: O(|L|)
    // Nuestro codigo da: O(|L|)
    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        promos.insertar(nombre, new Promocion(factor, tope));   // O(|L|)
    }

    // Requiere: O(|L|)
    // Nuestro codigo da: O(|L|)
    public void finalizarPromocion(String nombre) {
        promos.obtener(nombre).activa = false;               // O(|L|) la búsqueda + O(1) la asignación
    }

    // Requiere: O(1)
    // Nuestro codigo da: O(1) 
    public int elMasAcumulador() {
        return maxAcumulador.usuarioId;                      // O(1)
    }

    // Requiere: O(1)
    // Nuestro codigo da: O(1)  
    public int elMasCanjeador() {
        return maxCanjeador.usuarioId;                       // O(1)
    }

    // Requiere: O(U)
    // Nuestro codigo da: O(U)  
    public int[][] paresTransferidoresExclusivos() {
        int[][] pares = new int[listaUsuarios.longitud() / 2][2];   // O(U)
        int cantidad = 0;                                           // O(1)

        ListaEnlazada<Usuario>.ListaIterador it = listaUsuarios.iterador();   // O(1)

        while (it.haySiguiente()) {                          // U iteraciones, cada una O(1)
            Usuario user = it.siguiente();                   // O(1)

            if (user.contacto != null
                    && user.usuarioId < user.contacto.usuarioId
                    && user.exclusivoCon(user.contacto)) {   // O(1)

                pares[cantidad][0] = user.usuarioId;         // O(1)
                pares[cantidad][1] = user.contacto.usuarioId;   // O(1)
                cantidad++;                                  // O(1)
            }
        }                                                    // total del ciclo: O(U)

        int[][] paresFinal = new int[cantidad][2];           // O(cantidad) <= O(U)

        for (int i = 0; i < cantidad; i++) {                 // cantidad <= U/2 iteraciones
            paresFinal[i][0] = pares[i][0];                  // O(1)
            paresFinal[i][1] = pares[i][1];                  // O(1)
        }                                                    // total: O(U)

        return paresFinal;                                   // O(1)
    }
}
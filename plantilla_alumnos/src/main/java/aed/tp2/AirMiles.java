package aed.tp2;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> dictUsuarios;   
    private Trie<Promocion> promos;     
    private Usuario maxAcumulador;            // el que más totales tiene
    private Usuario maxCanjeador;             // el que más gastadas tiene
    private ListaEnlazada<Usuario> usuarios;

    public AirMiles() {
        dictUsuarios = new ABB<>();
        usuarios = new ListaEnlazada<>();
        promos = new Trie<>(); 
        maxAcumulador = null;
        maxCanjeador = null;
        promos.insertar("NoPromo", new Promocion(1, 0));
    }


    public void registrarUsuario(int id) {
        Usuario u = new Usuario(id);  
        dictUsuarios.insertar(id, u);
        usuarios.agregarAtras(u);     
        
        if (maxAcumulador == null) {
            maxAcumulador = u;
        }

        if (maxCanjeador == null) {
            maxCanjeador = u;
        }
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

        
        // if (userOrigen.multiplesContactos == false && userOrigen.contacto != destino && userOrigen.contacto != -1){
        //     userOrigen.multiplesContactos = true;
        // }

        // if (userOrigen.contacto == -1){
        //     userOrigen.contacto = destino;
        // }

        // if (userDestino.multiplesContactos == false && userDestino.contacto != origen && userDestino.contacto != -1){
        //     userDestino.multiplesContactos = true;
        // }

        // if (userDestino.contacto == -1){
        //     userDestino.contacto = origen;
        // }

        userOrigen.agregarContacto(userDestino);
        userDestino.agregarContacto(userOrigen);


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
        int[][] resultado = new int[usuarios.longitud() / 2][2];
        int cantidad = 0;

        ListaEnlazada<Usuario>.ListaIterador it = usuarios.iterador();

        while (it.haySiguiente()) {
            Usuario u = it.siguiente();

            if (u.contacto != null && u.usuarioId < u.contacto.usuarioId &&u.exclusivoCon(u.contacto)) {

                resultado[cantidad][0] = u.usuarioId;
                resultado[cantidad][1] = u.contacto.usuarioId;
                cantidad++;
            }
        }

        int[][] res = new int[cantidad][2];

        for (int i = 0; i < cantidad; i++) {
            res[i][0] = resultado[i][0];
            res[i][1] = resultado[i][1];
        }

        return res;
    }
}

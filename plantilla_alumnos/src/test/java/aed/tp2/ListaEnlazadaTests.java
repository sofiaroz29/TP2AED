package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListaEnlazadaTests {

    @Test
    void agregarYObtenerElementos() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();

        lista.agregarAdelante(3);
        lista.agregarAdelante(2);
        lista.agregarAtras(4);

        assertEquals(3, lista.longitud());
        assertEquals(2, lista.obtener(0));
        assertEquals(3, lista.obtener(1));
        assertEquals(4, lista.obtener(2));
        assertEquals("[2, 3, 4]", lista.toString());
    }

    @Test
    void modificarYEliminarElementos() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();

        lista.agregarAtras("A");
        lista.agregarAtras("B");
        lista.agregarAtras("C");

        lista.modificarPosicion(1, "Z");
        lista.eliminar(0);

        assertEquals(2, lista.longitud());
        assertEquals("Z", lista.obtener(0));
        assertEquals("C", lista.obtener(1));
        assertEquals("[Z, C]", lista.toString());
    }

    @Test
    void iteradorRecorreEnAmbosSentidos() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregarAtras(1);
        lista.agregarAtras(2);
        lista.agregarAtras(3);

        ListaEnlazada<Integer>.ListaIterador iterador = lista.iterador();

        assertTrue(iterador.haySiguiente());
        assertEquals(1, iterador.siguiente());
        assertTrue(iterador.hayAnterior());

        assertEquals(1, iterador.anterior());
        assertEquals(1, iterador.siguiente());
        assertEquals(2, iterador.siguiente());
        assertEquals(3, iterador.siguiente());
        assertFalse(iterador.haySiguiente());
    }
}

package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ABBTests {

    @Test
    void insertarYConsultarValores() {
        ABB<Integer, String> abb = new ABB<>();

        abb.insertar(10, "diez");
        abb.insertar(5, "cinco");
        abb.insertar(15, "quince");
        abb.insertar(3, "tres");
        abb.insertar(7, "siete");

        assertEquals(5, abb.cardinal());
        assertEquals(3, abb.minimo());
        assertEquals(15, abb.maximo());
        assertTrue(abb.pertenece(7));
        assertEquals("quince", abb.obtener(15));
        assertNull(abb.obtener(11));
    }

    @Test
    void eliminarNodoConDosHijos() {
        ABB<Integer, String> abb = new ABB<>();

        int[] valores = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        for (int valor : valores) {
            abb.insertar(valor, "v" + valor);
        }

        abb.eliminar(8);

        assertFalse(abb.pertenece(8));
        assertEquals(14, abb.cardinal());
        assertEquals(1, abb.minimo());
        assertEquals(15, abb.maximo());
        assertEquals("v10", abb.obtener(10));
    }

    @Test
    void iteradorRecorreEnOrden() {
        ABB<Integer, String> abb = new ABB<>();

        for (int valor : new int[] {5, 3, 8, 1, 4, 7, 9}) {
            abb.insertar(valor, "v" + valor);
        }

        ABB<Integer, String>.ABB_Iterador iterador = abb.iterador();
        List<Integer> recorrido = new ArrayList<>();

        while (iterador.haySiguiente()) {
            recorrido.add(iterador.siguiente());
        }

        assertEquals(List.of(1, 3, 4, 5, 7, 8, 9), recorrido);
    }
}

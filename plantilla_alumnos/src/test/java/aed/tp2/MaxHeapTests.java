package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaxHeapTests {

    @Test
    void agregarYEliminarMaximo() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        heap.agregar(10);
        heap.agregar(5);
        heap.agregar(15);
        heap.agregar(7);

        assertEquals(4, heap.longitud());
        assertEquals(Integer.valueOf(15), heap.maximo(0));
        assertEquals(Integer.valueOf(15), heap.eliminarMaximo());
        assertEquals(Integer.valueOf(10), heap.maximo(0));
        assertEquals(3, heap.longitud());
    }

    @Test
    void modificarHandleReordenaElHeap() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        MaxHeap<Integer>.Handle h1 = heap.agregar(4);
        MaxHeap<Integer>.Handle h2 = heap.agregar(9);
        MaxHeap<Integer>.Handle h3 = heap.agregar(2);

        h1.modificar(15);
        h2.modificar(1);

        assertEquals(Integer.valueOf(15), heap.maximo(0));
        assertEquals(Integer.valueOf(15), heap.eliminarMaximo());
        assertEquals(Integer.valueOf(2), heap.maximo(0));
        assertEquals(2, heap.longitud());
    }

    @Test
    void copiaMantieneLosElementosOriginales() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        heap.agregar(8);
        heap.agregar(3);
        heap.agregar(10);

        MaxHeap<Integer> copia = heap.copia();
        heap.eliminarMaximo();

        assertEquals(Integer.valueOf(10), copia.maximo(0));
        assertEquals(Integer.valueOf(8), heap.maximo(0));
        assertEquals(2, heap.longitud());
    }
}

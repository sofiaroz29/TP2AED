package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrieTests {

    @Test
    void insertarYBuscarPalabras() {
        Trie<Integer> trie = new Trie<>();

        trie.insertar("casa", 1);
        trie.insertar("caso", 2);
        trie.insertar("cat", 3);
        trie.insertar("car", 4);

        assertEquals(4, trie.cardinal());
        assertTrue(trie.pertenece("casa"));
        assertEquals(2, trie.obtener("caso"));
        assertEquals(4, trie.obtener("car"));
        assertNull(trie.obtener("ca"));
    }

    @Test
    void reemplazaValorCuandoLaClaveYaExiste() {
        Trie<String> trie = new Trie<>();

        trie.insertar("hola", "uno");
        trie.insertar("hola", "dos");

        assertEquals(1, trie.cardinal());
        assertEquals("dos", trie.obtener("hola"));
        assertTrue(trie.pertenece("hola"));
    }

    @Test
    void clavesConDistintosPrefijos() {
        Trie<Integer> trie = new Trie<>();

        trie.insertar("a", 10);
        trie.insertar("ab", 20);
        trie.insertar("abc", 30);
        trie.insertar("abd", 40);

        assertEquals(4, trie.cardinal());
        assertEquals(10, trie.obtener("a"));
        assertEquals(20, trie.obtener("ab"));
        assertEquals(30, trie.obtener("abc"));
        assertEquals(40, trie.obtener("abd"));
    }
}

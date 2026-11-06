package aed.tp2;


public class Trie <T>{
    private Nodo raiz;
    private int cardinal;

    private class Nodo {
        private char letra;
        private T valor;
        private Nodo hijo;
        private Nodo hermano;

        public Nodo(char letra) {
            this.letra = letra;
            this.valor = null;
            this.hijo = null;
            this.hermano = null;
        }
    }

    public Trie() {
        this.raiz = new Nodo(' ');
        this.cardinal = 0;
    }

    public int cardinal() {
        return cardinal;
    }

    private Nodo buscarHijo(Nodo padre, char letra){
        Nodo actual = padre.hijo;
        while(actual != null){
            if(actual.letra == letra){
                return actual;
            }
            actual = actual.hermano;
        }
        return null;
    }

    public void insertar(String clave, T valor){
        Nodo actual = raiz;
        for(int i = 0; i < clave.length(); i++){
            char letra = clave.charAt(i);
            Nodo siguiente = buscarHijo(actual, letra);
            if(siguiente == null){
                siguiente = new Nodo(letra);
                siguiente.hermano = actual.hijo;
                actual.hijo = siguiente;
            }
            actual = siguiente;
        }

        if(actual.valor == null){
            cardinal += 1;
        }
        actual.valor = valor;
    }

  
    public T obtener(String clave){
        Nodo actual = raiz;
        for(int i = 0; i < clave.length(); i++){
            actual = buscarHijo(actual, clave.charAt(i));
            if(actual == null){
                return null;
            }
        }
        return actual.valor; 
    }

    public boolean pertenece(String clave){
        return obtener(clave) != null;
    }
}

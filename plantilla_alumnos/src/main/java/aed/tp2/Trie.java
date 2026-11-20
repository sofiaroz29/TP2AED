package aed.tp2;


public class Trie <T>{
    private Nodo raiz;
    private int cardinal;


    private class Nodo {
        private T valor;
        private ArrayList<Nodo> hijos;

        public Nodo( ) {
            this.valor = null;
            this.hijos = new ArrayList<>(256);
            for (int i = 0; i < 256; i++){
                hijos.add(null);
            }
        }
    }

    public Trie() {
        this.raiz = new Nodo();
        this.cardinal = 0;
    }

    public int cardinal() {
        return cardinal;
    }


    private Nodo buscarHijo(Nodo padre, char letra){
        return padre.hijos.get(letra);
    }

    public void insertar(String nombre, T valor){
        Nodo actual = raiz;
        Nodo padre; 

        for (int i = 0; i < nombre.length(); i++ ){
            char letra = nombre.charAt(i);
            if (actual.hijos.get(letra)==null){
                actual.hijos.set(letra, new Nodo());
            }
                actual = actual.hijos.get(letra);
            
        }

        if (actual.valor == null){
                cardinal +=1;
            }

        actual.valor = valor; 

    }

  
    public T obtener(String nombre){
        Nodo actual = raiz;
        for(int i = 0; i < nombre.length(); i++){
            char letra = nombre.charAt(i);
            actual = buscarHijo(actual, letra);
            if(actual == null){
                return null;
            }
        }
        return actual.valor; 
    }


    public void eliminar (String nombre){
        Nodo actual = raiz;
        for(int i = 0; i < nombre.length(); i++){
            char letra = nombre.charAt(i);
            actual = buscarHijo(actual, letra);
            if (actual == null){
                return;
            }
        } //final de la palabra

        if (actual.valor != null){
            actual.valor = null;
            cardinal-=1;
        } //saco valor de la ultima letra
        

    }


    public boolean pertenece(String clave){
        return obtener(clave) != null;
    }
}

error id: file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java:_empty_/valheap#
file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java
empty definition using pc, found symbol in pc: _empty_/valheap#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 956
uri: file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java
text:
```scala
package aed.tp2;

import java.util.ArrayList;


public class MaxHeap <T extends Comparable<T>>{
    private ArrayList<T> heap;


   public class Handle <T>{
        private T valor;
        private int indice;
        
        public Handle(int indice, T valor){
            this.valor = valor;
            this.indice = indice;
        }
        public T valor(){
            return valor;
        }

        public void modificar(){
            
        }

        public boolean equals(Handle<T> h){
            
        }

    }

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }
    private int padre(int i){
        return (i-1)/2;
    } 

    private int izq(int i){
        return 2*i+1;
    } 

    private int der(int i){
        return 2*i+1;
    }  

    public Handle agregar (T valor){
        //fijarnos que posicion esta libre --> cant elementos +1
        //agregar
        //siftupear
        Handle h = new Handle<>(val@@heap.size()-1)or, ;
        heap.add(valor);
        siftUp(heap.size()-1, valor);
        Handle.valor=
        return 
    }

    public void siftUp(int i, T valor){

        int padreIndice = padre(heap.size()-1);
        int indiceValor= i;
        T padre= heap.get(padreIndice);


        while (padre.compareTo(valor) < 0){
                
            heap.set(padreIndice, valor);
            heap.set(indiceValor, padre);

            indiceValor= padreIndice;
            padreIndice=padre(padreIndice);
            padre=heap.get(padreIndice);
        }

    }

    public T maximo (){
        return heap.get(0);
    }

    // public void siftDown(int i, T valor){
    //     heap.set (i, valor);
    // }




}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/valheap#
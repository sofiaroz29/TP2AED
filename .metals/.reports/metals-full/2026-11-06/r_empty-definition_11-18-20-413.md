error id: file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java:java/util/ArrayList#get#
file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java
empty definition using pc, found symbol in pc: java/util/ArrayList#get#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 860
uri: file://<WORKSPACE>/plantilla_alumnos/src/main/java/aed/tp2/MaxHeap.java
text:
```scala
package aed.tp2;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxHeap <T extends Comparable<T>>{
   private ArrayList<T> heap;

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

    public void agregar (T valor){
        //fijarnos que posicion esta libre --> cant elementos +1
        //agregar
        //siftupear

        heap.add(valor);
        int padreIndice = padre(heap.size()-1);
        int indiceValor= (heap.size()-1);
        T padre= heap.get(padreIndice);

        while (padre.compareTo(valor) < 0){
                
            siftUp(padreIndice, valor);
            siftDown(indiceValor, padre);
            padre=heap.g@@et()
            indiceValor= padreIndice;
            padreIndice=padre(padreIndice);
        }


    }

    public void siftUp(int i, T valor){
        heap.set(i, valor);
    }

    public void siftDown(int i, T valor){
        heap.set (i, valor);
    }




}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/ArrayList#get#
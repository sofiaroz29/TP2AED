package aed.tp2;

import java.util.ArrayList;


public class MaxHeap <T extends Comparable<T>>{
    private ArrayList<Handle> heap;


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

    public Handle <T> agregar (T valor){
        //fijarnos que posicion esta libre --> cant elementos +1
        //agregar
        //siftupear
        Handle <T> h = new Handle<T>(heap.size()-1, valor) ;
        heap.add(h);
        siftUp(heap.size()-1);
        return h;
    }

    public void siftUp(int i){

        int padreIndice = padre(heap.size()-1);
        int indiceValor= i;
        Handle padre= heap.get(padreIndice);
        Handle actual = heap.get(i);


        while (padre.valor.compareTo(actual.valor) < 0){
                
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

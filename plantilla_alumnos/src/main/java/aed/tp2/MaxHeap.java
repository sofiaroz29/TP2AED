package aed.tp2;

import java.util.ArrayList;


public class MaxHeap <T extends Comparable<T>>{
    private ArrayList<Handle> heap;


   public class Handle {
        private T valor;
        private int indice;
        
        public Handle(int indice, T valor){
            this.valor = valor;
            this.indice = indice;
        }
        public T valor(){
            return valor;
        }

        public void modificar(T nuevoValor){
            valor = nuevoValor;
            siftUp(indice);
            siftDown(indice);
        }

        public boolean equals(Handle<T> h){
            return this == h;
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
        return 2*i+2;
    }  

    public Handle agregar (T valor){
        //fijarnos que posicion esta libre --> cant elementos +1
        //agregar
        //siftupear
        Handle h = new Handle(heap.size(), valor) ;
        heap.add(h);
        siftUp(heap.size()-1);
        return h;
    }

    public void siftUp(int i){

        int indiceValor = i;

        while (indiceValor > 0) {
            int padreIndice = padre(indiceValor);

            Handle actual = heap.get(indiceValor);
            Handle padre = heap.get(padreIndice);

            if (actual.valor.compareTo(padre.valor) > 0) {
                heap.set(padreIndice, actual);
                heap.set(indiceValor, padre);

                actual.indice = padreIndice;
                padre.indice = indiceValor;
                indiceValor = padreIndice;
            } 
            else {
                break;
            }
        }
    }

    public void siftDown(int i) {

        int indiceValor = i;
        int hijoIzq = izq(i);
        int hijoDer = der(i);
    
        if (hijoIzq < heap.size() && heap.get(hijoIzq).valor.compareTo(heap.get(indiceValor).valor) > 0) {
            indiceValor = hijoIzq;
        }
    
        if (hijoDer < heap.size() && heap.get(hijoDer).valor.compareTo(heap.get(indiceValor).valor) > 0) {
            indiceValor = hijoDer;
        }
    
        if (indiceValor != i) {
    
            Handle actual = heap.get(i);
            Handle hijo = heap.get(indiceValor);
    
            heap.set(i, hijo);
            heap.set(indicevalor, actual);
    
            hijo.indice = i;
            actual.indice = indiceValor;
    
            siftDown(indiceValor);
        }
    }

    public T maximo (){
        //DEVOLVER LOS N MAS GRANDES
    }




}

package aed.tp2;

import java.util.NoSuchElementException;

public class ListaEnlazada<T> {
    // Completar atributos privados

    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo(T v) { valor = v; }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public int longitud() {
        int contador = 0;
        Nodo actual = primero; 
        while (actual != null) {
            contador++;             
            actual = actual.sig;
        }

        return contador; 
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null){
            primero = nuevo;
            ultimo = nuevo;
        }
        else {
            nuevo.sig = primero;
            primero.ant = nuevo;
            primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null){
            primero = nuevo;
            ultimo = nuevo;
        }
        else {
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }
    }

    public T obtener(int i) {

        Nodo actual = primero;

        for (int j = 0; j<i; j++){
            actual = actual.sig;
        }
        return actual.valor;

    }

    public void eliminar(int i) {
        Nodo actual = primero;
        Nodo prev = primero;
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }
        if (i == 0) {
            primero = actual.sig;
            if (primero != null) {
                primero.ant = null;
            } 
            else {
                ultimo = null; 
            }
        } 
        
        else {
            prev.sig = actual.sig;
            if (actual.sig != null) {
                actual.sig.ant = prev;
            } 
            
            else {
                ultimo = prev; 
            }
        }

    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        for (int j = 0; j < indice; j++){
            if (actual != null){
                actual = actual.sig;
            }

        }

        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {

        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }
    
    @Override
    public String toString() {
        String result = "[";
        Nodo actual = primero;
        while (actual!=null){
            result = result+actual.valor.toString();
            if (actual.sig != null){
                result= result +  ", ";
            }
            actual = actual.sig;
        }
        result = result + "]";

        return result;
    }

    public class ListaIterador{
        private Nodo actual;
        private Nodo ultimo;

        public boolean haySiguiente() {
            return actual != null;
        }
        
        public boolean hayAnterior() {
            if (actual == null) {
                return ultimo != null;
            }
            return actual.ant != null;
        }

        public T siguiente() {
            if (actual == null) {
                throw new NoSuchElementException("No hay siguiente elemento en la lista");
            }

            T resultado = actual.valor;
            actual = actual.sig;
            return resultado;
        }
        
        public T anterior() {
            if (actual == null) {
                if (ultimo == null) {
                    throw new NoSuchElementException("No hay anterior elemento en la lista");
                }
                actual = ultimo;
                return actual.valor;
            }

            if (actual.ant == null) {
                throw new NoSuchElementException("No hay anterior elemento en la lista");
            }

            actual = actual.ant;
            return actual.valor;
        }
    }

    public ListaIterador iterador() {
        ListaIterador it = new ListaIterador();
        it.actual = primero;
        it.ultimo = ultimo;
        return it;
    }

}

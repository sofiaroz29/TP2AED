package aed.tp2;
import java.util.NoSuchElementException;


public class ABB <C extends Comparable<C>, V>{
    private Nodo raiz;
    private int cardinal;

    private class Nodo {
        private C clave;
        private V valor;
        private Nodo izq;
        private Nodo der;
        private Nodo padre;

        public Nodo(C clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.izq = null;
            this.der = null;
            this.padre = null;
        }
    }

    public ABB() {
        this.raiz=null;
        this.cardinal=0;
    }

    public int cardinal() {
        return cardinal;
    }

    public C minimo(){
        if(raiz == null){
            return null;
        }
        Nodo _actual = raiz;
        while (_actual.izq!=null){
            _actual = _actual.izq;
        }
        return _actual.clave;
    }

    public C maximo(){
        if(raiz == null){
            return null;
        }
        Nodo _actual = raiz;
        while (_actual.der!=null){
            _actual = _actual.der;
        }
        return _actual.clave;
    }

    public void insertar(C clave, V valor){
        if(raiz == null){
            raiz = new Nodo(clave, valor);
            cardinal += 1;
            return;
        }

        Nodo _actual = raiz;
        boolean listo = false;
        while(!listo){
            if(clave.compareTo(_actual.clave) < 0){
                if(_actual.izq == null){
                    Nodo nuevo = new Nodo(clave, valor);
                    nuevo.padre = _actual;
                    _actual.izq = nuevo;
                    cardinal += 1;
                    listo = true;
                }
                else{
                    _actual = _actual.izq;
                }
            }
            else if(clave.compareTo(_actual.clave) > 0){
                if(_actual.der == null){
                    Nodo nuevo = new Nodo(clave, valor);
                    nuevo.padre = _actual;
                    _actual.der = nuevo;
                    cardinal += 1;
                    listo = true;
                }
                else{
                    _actual = _actual.der;
                }
            }
            else{
                _actual.valor = valor;   // la clave ya estaba: reemplazo el valor
                listo = true;
            }
        }
    }

    public boolean pertenece(C clave){
        Nodo _actual=raiz;
        while (_actual != null){
            if(clave.compareTo(_actual.clave) == 0){
                return true;
            }
            else if(clave.compareTo(_actual.clave) > 0){
                _actual = _actual.der;
            }
            else{
                _actual = _actual.izq;
            }
        }
        return false;
    }

    public V obtener(C clave){
        Nodo _actual = raiz;
        while (_actual != null){
            int cmp = clave.compareTo(_actual.clave);
            if(cmp == 0){
                return _actual.valor;
            }
            else if(cmp > 0){
                _actual = _actual.der;
            }
            else{
                _actual = _actual.izq;
            }
        }
        return null;
    }

    public void eliminar(C clave){
        Nodo actual = raiz;
        Nodo padre = null;

        while (actual != null && clave.compareTo(actual.clave) != 0) {
            padre = actual;

            if (clave.compareTo(actual.clave) < 0) {
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }

        if (actual == null) {
            return;
        }

        if (actual.izq == null && actual.der == null) {
            if (padre == null) {
                raiz = null;
            } else if (padre.izq == actual) {
                padre.izq = null;
            } else {
                padre.der = null;
            }
        }

        else if (actual.izq == null || actual.der == null) {
           Nodo hijo = actual.izq;
            if (hijo == null) {
                hijo = actual.der;
            }

            hijo.padre = padre;

            if (padre == null) {
                raiz = hijo;
            } 
            else if (padre.izq == actual) {
                padre.izq = hijo;
            } 
            else {
                padre.der = hijo;
            }
        }

        else {
            Nodo p = actual;
            Nodo sucesor = actual.der;

            while (sucesor.izq != null) {
                p = sucesor;
                sucesor = sucesor.izq;
            }

            actual.clave = sucesor.clave;  
            actual.valor = sucesor.valor;
            if (sucesor.der != null) {
                sucesor.der.padre = p;
            }

            if (p == actual) {
                p.der = sucesor.der;
            } else {
                p.izq = sucesor.der;
            }
        }
        cardinal -= 1;
    }

    public String toString(){
        String resultado = "{";

        if(raiz != null){
            C _ultimo = minimo();
            resultado += _ultimo;

            while (_ultimo != null){
                C _sucesor = null;
                Nodo _actual = raiz;
                while (_actual!=null){
                    if(_actual.clave.compareTo(_ultimo)>0){
                        _sucesor = _actual.clave;
                        _actual = _actual.izq;
                    }
                    else{
                        _actual = _actual.der;
                    }
                }
                if(_sucesor != null){
                    resultado += "," + _sucesor;
                }
                _ultimo = _sucesor;
            }
        }

        return resultado + "}";
    }


    public class ABB_Iterador {
        private Nodo _actual;

        public ABB_Iterador() {
            _actual = raiz;
            if (_actual != null) {
                while (_actual.izq != null) {
                    _actual = _actual.izq;
                }
            }
        }

        public boolean haySiguiente() {
            return _actual != null;
        }

        public C siguiente() {
           C res = _actual.clave;

            if (_actual.der != null) {
                _actual = _actual.der;
                while (_actual.izq != null) {
                    _actual = _actual.izq;
                }
            }
            else {
                Nodo n = _actual;
                while (n.padre != null && n.padre.der == n) {
                    n = n.padre;
                }
                _actual = n.padre;
            }
            return res;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }
}

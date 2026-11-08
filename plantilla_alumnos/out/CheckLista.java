package aed.tp2;
public class CheckLista {
  public static void main(String[] args) {
    ListaEnlazada<Integer> lista = new ListaEnlazada<>();
    lista.agregarAtras(1);
    lista.agregarAtras(2);
    lista.agregarAtras(3);
    ListaEnlazada<Integer>.ListaIterador it = lista.iterador();
    System.out.println(it.siguiente());
    System.out.println(it.hayAnterior());
    System.out.println(it.anterior());
    System.out.println(it.siguiente());
    System.out.println(it.siguiente());
    System.out.println(it.siguiente());
    System.out.println(it.haySiguiente());
  }
}

package formula;

public class Nodos {

    public Nodos sig;
    public char dato;

    public Nodos() {
        sig = null;
        dato =' ';
    }

    public Nodos(Nodos n, char l) {
        sig = n;
        dato = l;
    }
}

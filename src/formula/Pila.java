package formula;

import javax.swing.JTextArea;

public class Pila {

    
    Nodos pila;
    int tam;

    public Pila() {
        pila = null;
    }

    public boolean vacia() {
        return pila == null;
    }

    public void borrar() {
        pila = null;
        tam=0;
    }

    public void apilar(char d) {
        Nodos nue = new Nodos();
        nue.dato = d;
        if (vacia()) {
            nue.sig = null;
            pila = nue;
        } else {
            nue.sig = pila;
            pila = nue;
        }
        tam++;

    }

    public void imp() {
        if (vacia()) {
            System.out.println("Pila vacia");

        } else {
            Nodos reco = pila;
            System.out.println("Listado de elemetos de la pila\n");
            while (reco != null) {
                System.out.println("--" + "[" + reco.dato + "]");
                reco = reco.sig;

            }
        }
    }

    public boolean parentesis() {
        int c1 = 0, c2 = 0, a = 0;
        boolean j = false;
        if (vacia()) {
            System.out.println("Pila vacia");
            j = false;
        } else {
            Nodos reco = pila;
            while (reco != null) {
                char u = reco.dato;

                if (u == '(') {
                    c1++;
                } else if (u == ')') {
                    c2++;
                } else {
                    a++;
                }
                reco = reco.sig;

            }
            j = c1 == c2;

        }

        return j;
    }

    public boolean letra(JTextArea mos) {
        boolean j = false;
        Nodos reco = pila;
        char u = reco.dato;
        if (!parentesis()) {
            mos.append("No coinceden el número de parentesis\nFormula mal formada");
            j = false;
        } else if (tam == 1) {
            if (u == 'p' || u == 'q' || u == 'r' || u == 's' | u == 'T' || u == 'L') {
                mos.append("Formula bien formada");
                j = true;
            } else {
                mos.append("Formula mal formada");
            }

        } else {

            while (reco != null) {
                if (u == '(' || u == ')') {
                    reco = reco.sig;
                    if (reco.dato == 'L' || reco.dato == 'T') {
                        mos.append("Mal,  T L -> ( )\nFormula mal formada");
                        j = false;
                    } else {
                        mos.append("Formula bien formada");
                        j = true;
                    }

                } else if (u == 'q' || u == 'r' || u == 's' || u == 'p') {
                    reco = reco.sig;
                    if (reco.dato == 'q' || reco.dato == 'r' || reco.dato == 's' || reco.dato == 'p') {
                        mos.append("Mal, p,q,r,s -> p,q,r,s\nFormula mal formada");
                        j = false;
                    } else {
                        j = true;
                    }

                } else if (u == '<' || u == '>' || u == '∧' || u == 'v' || u == '-') {
                    reco = reco.sig;
                    if (reco.dato == 'L' || reco.dato == 'T') {
                        mos.append("Mal, T L -> <->  -> ∧ v\nFormula mal formada");
                        j = false;
                    } else if (reco.dato == '<' || reco.dato == '>' || reco.dato == '∧' || reco.dato == 'v' || reco.dato == '-') {
                        mos.append("Mal, <->,  ->, ∧, v, -      ->       <->,  ->, ∧, v, -\nFormula mal formada");
                        j = false;

                    } else {
                        j = true;
                    }

                } else if (u == 'L' || u == 'T') {
                    reco = reco.sig;
                    if (reco.dato == '<' || reco.dato == '>' || reco.dato == '∧' || reco.dato == 'v' || reco.dato == '-' || reco.dato == '(' || reco.dato == ')') {
                        mos.append("Formula mal formada");
                        j = false;
                    } else if (reco.dato == 0) {
                        mos.append("Formula bien formada");
                        j = true;
                    }

                } else {
                    mos.append("Formula bien formada");
                    j = true;
                }
                reco = reco.sig;

            }
        }
        return j;

    }
}

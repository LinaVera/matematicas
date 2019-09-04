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
        tam = 0;
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
//Saber si son operadores logicos como: < > v ∧

    public boolean OL(char a) {

        return a == '<' || a == '>' || a == 'v' || a == '∧';
    }
//Saber si son Sigma: p,q,r,s, L, T

    public boolean sigma(char a) {

        return a == 'p' || a == 'q' || a == 'r' || a == 's' || a == 'L' || a == 'T';
    }
//Saber sis on parentesis

    public boolean parentesis(char a) {

        return a == '(' || a == ')';
    }
///Validar si la formula esta bien formada

    public boolean Fbf(JTextArea m) {
        boolean bf = true;
        Nodos reco = pila;
        char l = reco.dato;
        //Igual numero de parentesis
        if (!parentesis()) {
            m.append("No coinceden el número de parentesis\nFórmula mal formada");
            bf = false;
            //si el tamaño es 1 solo pueden estar las sigmas
        } else if (tam == 1) {
            if (sigma(l)) {
                //m.append("Formula bien formada");
                bf = true;
            } else {
                m.append("Formula mal formada");
                bf = false;
            }
            //Si termina en un operador logico o negación
        } else if (OL(l) || l == '-') {
            //Negación
            if (l == '-') {
                m.append("Formula mal formada\nNegación al final");
                //Operador logico
            } else if (OL(l)) {
                m.append("Formula mal formada\nOperador logico al final");
            }
            bf = false;
            //Diferentes combinaciones
        } else {
            while (reco!=null) {
                //Si hay un parentesis

                if (parentesis(l)) {
                    reco = reco.sig;

                    //Si es )    
                    if (l == ')') {
                        l = reco.dato;
                        char p = reco.dato;
                        if (OL(p) || p == '-' || p == '(') {
                            m.append("Hay OL, -, (  EN  ) \nFormula mal formada");
                            System.out.println("parentesis )");
                            break;
                        } else {
                            bf = true;
                        }
                    } //Si es ( 
                    else if (l == '(') {
                        l = reco.dato;
                        char p = reco.dato;
                        if (sigma(p) || p == ')') {
                            m.append("Hay Sigma, )  EN ( \nFormula mal formada");
                            bf = false;
                            System.out.println("parentesis (");
                            break;
                        } else {
                            bf = true;
                        }
                    }

                } //Si hay sigma
                else if (sigma(l)) {
                    reco = reco.sig;
                    l = reco.dato;
                    char sg = reco.dato;
                    if (sigma(sg) || sg == ')') {
                        m.append("Hay Sigma, )  EN Sigma \nFormula mal formada");
                        bf = false;
                        break;
                    } else {
                        bf = true;

                    }

                    /*} //si es negacion
                else if (l == '-') {
                    reco = reco.sig;
                    l = reco.dato;
                    char n = reco.dato;
                    if (OL(n) ) {
                        m.append("Hay OL, )  EN  Negacion \nFormula mal formada");
                        bf = false;
                        break;
                    } else {
                        bf = true;

                    }
                     */
                } //si es operadores logicos
                else if (OL(l)) {
                    reco = reco.sig;
                    l = reco.dato;
                    char ol = reco.dato;
                    if (OL(ol) || ol == '-' || ol == '(') {
                        m.append("Hay OL, -, (   EN  OL \nFormula mal formada");
                        bf = false;
                        break;
                    } else {
                        bf = true;

                    }
                }
reco=reco.sig;
            }

        }

        return bf;

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

        } else if (u == '-') {
            mos.append("Mal,  - al final \nFormula mal formada");
            j = false;
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

                } else if (u == '<' || u == '>' || u == '∧' || u == 'v') {
                    reco = reco.sig;
                    if (reco.dato == 'L' || reco.dato == 'T') {
                        mos.append("Mal, T L -> <->  -> ∧ v\nFormula mal formada");
                        j = false;
                    } else if (reco.dato == '<' || reco.dato == '>' || reco.dato == '∧' || reco.dato == 'v') {
                        mos.append("Mal, <->,  ->, ∧, v, -      ->       <->,  ->, ∧, v, -\nFormula mal formada");
                        j = false;

                    } else {
                        j = true;
                    }

                } else if (u == 'L' || u == 'T') {
                    reco = reco.sig;
                    if (reco.dato == '<' || reco.dato == '>' || reco.dato == '∧' || reco.dato == 'v' || reco.dato == '(' || reco.dato == ')') {
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

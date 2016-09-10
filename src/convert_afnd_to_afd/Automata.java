package convert_afnd_to_afd;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Automata {

    public Automata() {
        E = new ArrayList<>();
        Q = new ArrayList<>();
        F = new ArrayList<>();
    }

    public ArrayList<String> getE() {
        return E;
    }

    public void setE(ArrayList<String> E) {
        this.E = E;
    }

    public ArrayList<String> getQ() {
        return Q;
    }

    public void setQ(ArrayList<String> Q) {
        this.Q = Q;
    }

    public ArrayList<String>[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(ArrayList<String>[][] matriz) {
        this.matriz = matriz;
    }

    public ArrayList<String> getF() {
        return F;
    }

    public void setF(ArrayList<String> F) {
        this.F = F;
    }

    public String getI() {
        return I;
    }

    public void setI(String I) {
        this.I = I;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                s.append(matriz[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
   

    private ArrayList<String> E; //alfabeto
    private ArrayList<String> Q;  //estados
    private ArrayList<String>[][] matriz; //matriz de mapeo
    private ArrayList<String> F; //estados finales
    private String I; //estado inicial
}

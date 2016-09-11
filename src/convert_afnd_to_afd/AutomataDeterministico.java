package convert_afnd_to_afd;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Isa
 */
public class AutomataDeterministico {

    private ArrayList<String> E; //alfabeto
    private ArrayList<String> Q;  //estados
    private String[][] matriz; //matriz 
    private ArrayList<String> F; //estados finales
    private String I; //estado inicial

    public AutomataDeterministico() {
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

    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(String[][] matriz) {
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

//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz[0].length; j++) {
//                s.append(matriz[i][j]).append(" ");
//            }
//            s.append("\n");
//        }
//        return s.toString();
//    }

}

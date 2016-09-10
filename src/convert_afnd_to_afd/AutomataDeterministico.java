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

    public AutomataDeterministico(ArrayList<String> alfabeto, ArrayList<String> estados, String[][] matriz, ArrayList<String> estados_finales, String estados_inicial) {
        this.E = alfabeto;
        this.Q = estados;
        this.matriz = matriz;
        this.F = estados_finales;
        this.I = estados_inicial;
    }
    public AutomataDeterministico(){
         E = new ArrayList<>();
        Q = new ArrayList<>();
        F = new ArrayList<>();
    }

    public ArrayList<String> getAlfabeto() {
        return E;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.E = alfabeto;
    }

    public ArrayList<String> getEstados() {
        return Q;
    }

    public void setEstados(ArrayList<String> estados) {
        this.Q = estados;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

    public ArrayList<String> getEstados_finales() {
        return F;
    }

    public void setEstados_finales(ArrayList<String> estados_finales) {
        this.F = estados_finales;
    }

    public String getEstados_inicial() {
        return I;
    }

    public void setEstados_inicial(String estados_inicial) {
        this.I = estados_inicial;
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
   
    
}

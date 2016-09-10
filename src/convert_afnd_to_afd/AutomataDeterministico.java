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
    private ArrayList<String> alfabeto; //alfabeto
    private ArrayList<String> estados;  //estados
    private String[][] matriz; //matriz 
    private ArrayList<String> estados_finales; //estados finales
    private String estados_inicial; //estado inicial

    public AutomataDeterministico(ArrayList<String> alfabeto, ArrayList<String> estados, String[][] matriz, ArrayList<String> estados_finales, String estados_inicial) {
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.matriz = matriz;
        this.estados_finales = estados_finales;
        this.estados_inicial = estados_inicial;
    }

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }

    public ArrayList<String> getEstados_finales() {
        return estados_finales;
    }

    public void setEstados_finales(ArrayList<String> estados_finales) {
        this.estados_finales = estados_finales;
    }

    public String getEstados_inicial() {
        return estados_inicial;
    }

    public void setEstados_inicial(String estados_inicial) {
        this.estados_inicial = estados_inicial;
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

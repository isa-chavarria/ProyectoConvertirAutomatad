package convert_afnd_to_afd;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mario
 */
public class AutomataNoDeterministico {

    public AutomataNoDeterministico() {
        E = new ArrayList<>();
        Q = new ArrayList<>();
        F = new ArrayList<>();
        newE = new ArrayList<>();
        newEtemp = new ArrayList<>();
        contador = 0;
        hashMap = new HashMap<>();
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
                s.append("[");
                for (int k = 0; k < matriz[i][j].size(); k++) {
                    s.append(matriz[i][j].get(k));
                }
                if (matriz[i][j].isEmpty()) {
                    s.append("X");
                }
                s.append("]").append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public ArrayList<String> getEstado(String estado, String lenguaje) {
        ArrayList<String> ListaEstados = new ArrayList<>();
        for (int i = 1; i < matriz[0].length; i++) {
            if (matriz[0][i].get(0).equals(lenguaje)) {
                ListaEstados = matriz[Integer.parseInt(estado) + 1][i];
                break;
            }
        }
        return ListaEstados;
    }

    public ArrayList<String> getEstadosConTranVacias(ArrayList<String> estados) {
        ArrayList<String> ListaEstadosConCerradura = estados;
        int i;
        for (i = 0; i < estados.size(); i++) {
            for (int j = 1; j < matriz[0].length; j++) {
                if (matriz[0][j].get(0).equals("")) {
                    for (int k = 0; k < matriz[Integer.parseInt(estados.get(i)) + 1][j].size(); k++) {
                        if (!estados.contains(matriz[Integer.parseInt(estados.get(i)) + 1][j].get(k))) {
                            ListaEstadosConCerradura.add(matriz[Integer.parseInt(estados.get(i)) + 1][j].get(k));
                        }
                    }
                }
            }
        }
        return ListaEstadosConCerradura;

    }

    /* public ArrayList<String> AddNewEtemp(ArrayList<String> estados) {
     
     }*/
    public void setToNewMatriz(ArrayList<Mapeo> mapa, AutomataDeterministico d, String from, String read, String to) {
        d.getQ().add(to);
        mapa.add(new Mapeo(from, read, to));
    }

    public void setMatrizAutodeterministica(AutomataDeterministico d) {
        for (int i = 0; i < getE().size(); i++) {
            if (!getE().get(i).equals("")) {
                d.getE().add(getE().get(i));
            }
        }
    }

    public String addAndGetNewState(ArrayList<String> listaVacios, String estadoNuevo) {
        listaVacios.sort(null);
        ArrayList<String> estados = new ArrayList<>();
        estados = hashMap.get(estadoNuevo);
        if (estados == null || !estados.equals(listaVacios)) {
            hashMap.put("" + contador, listaVacios);
            contador++;
            newEtemp.add("" + contador);
            return "" + contador;
        }
        return null;
    }

    public AutomataDeterministico convertir_AFND_TO_AFD() {
        AutomataDeterministico automataDeterministico = new AutomataDeterministico();
        setMatrizAutodeterministica(automataDeterministico);
        ArrayList<Mapeo> mapa = new ArrayList<>();
        newE.add(I);
        ArrayList<String> estados = new ArrayList<>();
        ArrayList<String> estadosVacios = new ArrayList<>();
        estadosVacios = getEstadosConTranVacias(newE);
        String est = addAndGetNewState(estadosVacios, "");
        newE = newEtemp;
        for (String nuevoEstado : newEtemp) {
            for (String estado : newE) {
                for (String lenguaje : E) {
                    if (!lenguaje.equals("")) {
                        estados = getEstado(estado, lenguaje);
                        if (!estados.isEmpty()) {
                            estadosVacios = getEstadosConTranVacias(estados);
                            est = addAndGetNewState(estadosVacios, nuevoEstado);
                            setToNewMatriz(mapa, automataDeterministico, estado, lenguaje, est);
                        } else {
                            setToNewMatriz(mapa, automataDeterministico, estado, lenguaje, "");
                        }
                    }
                }
            }
            newE = hashMap.get(nuevoEstado);
        }

        return automataDeterministico;
    }

    private ArrayList<String> E; //alfabeto
    private ArrayList<String> Q;  //estados
    private ArrayList<String>[][] matriz; //matriz de mapeo
    private ArrayList<String> F; //estados finales
    private String I; //estado inicial
    private ArrayList<String> newE;
    private ArrayList<String> newEtemp;
    private HashMap<String, ArrayList<String>> hashMap;
    int contador;
}

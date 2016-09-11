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
        ArrayList<String> ListaEstados;
        int i, j;
        for (i = 0; i < matriz.length; i++) {
            if (matriz[0][i].equals(lenguaje)) {
                break;
            }
        }
        for (j = 0; j < matriz.length; j++) {
            if (matriz[j][0].equals(estado)) {
                break;
            }
        }
        ListaEstados = matriz[j][i];
        return ListaEstados;
    }

    public ArrayList<String> getEstadosConTranVacias(ArrayList<String> estados) {
        ArrayList<String> ListaEstadosConCerradura = new ArrayList<>();
    
        for (int i = 0; i < estados.size(); i++) {
            ListaEstadosConCerradura.add(estados.get(i));
        }
<<<<<<< Updated upstream
        int i;
        for (i = 0; i < matriz.length; i++) {
            if (matriz[0][i].equals("")) {
                break;
            }
        }
        for (int k = 0; k < estados.size(); k++) {
            for (int j = 0; j < matriz.length; j++) {
                if (estados.get(k).equals(matriz[j][0])) {
                    ListaEstadosConCerradura.addAll(matriz[j][i]);
                    break;
                }
            }
        }
=======
>>>>>>> Stashed changes

        return ListaEstadosConCerradura;

    }

<<<<<<< Updated upstream
   /* public ArrayList<String> AddNewEtemp(ArrayList<String> estados) {

    }*/
=======
    /* public ArrayList<String> AddNewEtemp(ArrayList<String> estados) {
     
     }*/
    public void setToNewMatriz(ArrayList<Mapeo> mapa, AutomataDeterministico d, String from, String read, String to) {
        d.getQ().add(to);
        mapa.add(new Mapeo(from,read,to));
    }

    public void setMatrizAutodeterministica(AutomataNoDeterministico nd, AutomataDeterministico d) {
        for (int i = 0; i < nd.getE().size(); i++) {
            if (!nd.getE().get(i).equals("")) {
                d.getE().add(nd.getE().get(i));
            }
        }
    }
>>>>>>> Stashed changes

    public String addAndGetNewState(ArrayList<String> listaVacios){
        listaVacios.sort(null);
        String key = "";
        for(String str : listaVacios){
            key += str;
        }
        String estado = hashMap.get(key);
        if(estado == null) {
            hashMap.put(key, "q" + contador);
            newEtemp.add(estado);
            contador++;
        }
        return estado;
    }

    public AutomataDeterministico convertir_AFND_TO_AFD() {
        AutomataDeterministico automataDeterministico = new AutomataDeterministico();
        ArrayList<Mapeo> mapa = new ArrayList<>();
        newE.add(I);
        ArrayList<String> estados;
        ArrayList<String> estadosVacios;
        do {
            for (String estado : newE) {
                for (String lenguaje : E) {
                    estados = getEstado(estado, lenguaje);
                    estadosVacios = getEstadosConTranVacias(estados);
                    String est = addAndGetNewState(estadosVacios);
                    
                }
            }
            newE.clear();
            newE = newEtemp;
        } while (!newE.isEmpty());

        return automataDeterministico;
    }

    private ArrayList<String> E; //alfabeto
    private ArrayList<String> Q;  //estados
    private ArrayList<String>[][] matriz; //matriz de mapeo
    private ArrayList<String> F; //estados finales
    private String I; //estado inicial
    private ArrayList<String> newE;
    private ArrayList<String> newEtemp;
    private HashMap<String, String> hashMap;
    int contador;
}

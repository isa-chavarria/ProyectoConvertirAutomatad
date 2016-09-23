package convert_afnd_to_afd;

import java.util.ArrayList;
import java.util.Collections;
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
        contador = -1;
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

    public boolean equalLists(ArrayList<String> one, ArrayList<String> two) {
        if (one == null && two == null) {
            return true;
        }

        if ((one == null && two != null)
                || one != null && two == null
                || one.size() != two.size()) {
            return false;
        }

    //to avoid messing the order of the lists we will use a copy
        //as noted in comments by A. R. S.
        one = new ArrayList<String>(one);
        two = new ArrayList<String>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

    public ArrayList<String> getEstado(ArrayList<String> estados, String lenguaje) {
        ArrayList<String> ListaEstados = new ArrayList<>();
        for (int i = 1; i < matriz[0].length; i++) {
            if (matriz[0][i].get(0).equals(lenguaje)) {  //column number
                for (int j = 0; j < estados.size(); j++) {  // numero de estados
                    for (int k = 0; k < matriz[Integer.parseInt(estados.get(j)) + 1][i].size(); k++) { //estados dentro de cada celda
                        if (!ListaEstados.contains(matriz[Integer.parseInt(estados.get(j)) + 1][i].get(k))) {  //si ya estaba agregado
                            ListaEstados.add(matriz[Integer.parseInt(estados.get(j)) + 1][i].get(k));
                        }
                    }
                }
                break;
            }
        }
        return ListaEstados;
    }

    public ArrayList<String> getEstadosConTranVacias(ArrayList<String> estados) {
        ArrayList<String> ListaEstadosConCerradura = estados;
        for (int i = 0; i < estados.size(); i++) {
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
    public void setToNewMatriz(ArrayList<Mapeo> mapa, String from, String read, String to) {
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
        //listaVacios.sort(null);
        for (int i = 0; i <= contador; i++) {
            if (equalLists(listaVacios,hashMap.get(("" + i)))) {
                return "" + i;
            }
        }
        contador++;
        hashMap.put("" + contador, listaVacios);
        newEtemp.add("" + contador);
        return "" + contador;

    }

    public AutomataDeterministico convertir_AFND_TO_AFD() {
        AutomataDeterministico automataDeterministico = new AutomataDeterministico();
        ArrayList<Mapeo> mapa = new ArrayList<>();
        newE.add(I);
        ArrayList<String> estados = new ArrayList<>();
        ArrayList<String> estadosVacios = new ArrayList<>();
        estadosVacios = getEstadosConTranVacias(newE);
        String est = addAndGetNewState(estadosVacios, "");
        for (int j = 0; j <= contador; j++) {
            newE = hashMap.get("" + j);
            for (String lenguaje : E) {
                if (!lenguaje.equals("")) {
                    estados = getEstado(newE, lenguaje);
                    if (!estados.isEmpty()) {
                        estadosVacios = getEstadosConTranVacias(estados);
                        est = addAndGetNewState(estadosVacios, "" + contador);
                        setToNewMatriz(mapa, "" + j, lenguaje, est);
                    } else {
                        setToNewMatriz(mapa, "" + j, lenguaje, ""); //va a un estado de error con ""
                    }
                }
            }

        }
        setMatrizAutodeterministica(automataDeterministico);  //setea el alfabeto
        automataDeterministico.setI("0");  //setea estado inicial
        for (int i = 0; i <= contador; i++) {
            for (int j = 0; j < getF().size(); j++) {
                if (hashMap.get(String.format("%s", i)).contains(getF().get(j))) { // setea los estados finales
                    automataDeterministico.getF().add(String.format("%s", i));
                }
            }
        }
        automataDeterministico.setQ(newEtemp);   //setea los estados
        int cont = 0;
        for (String alfabeto : E) {
            if (!alfabeto.equals("")) {
                cont++;
            }
        }
        automataDeterministico.setMatriz(new String[newEtemp.size() + 1][cont + 1]);   //inicializa la matriz
        for (int i = 0; i < automataDeterministico.getMatriz().length; i++) {
            for (int j = 0; j < automataDeterministico.getMatriz()[0].length; j++) {
                automataDeterministico.getMatriz()[i][j] = "";
            }
        }
        for (int i = 1; i < automataDeterministico.getMatriz().length; i++) {
            automataDeterministico.getMatriz()[i][0] = automataDeterministico.getQ().get(i - 1);   // setea los estados de la matriz
        }
        for (int i = 1; i < automataDeterministico.getMatriz()[0].length; i++) {
            automataDeterministico.getMatriz()[0][i] = automataDeterministico.getE().get(i - 1);  // setea el abecedario de la matriz
        }
        for (int i = 0; i < mapa.size(); i++) {
            for (int j = 1; j < automataDeterministico.getMatriz()[0].length; j++) {
                if (mapa.get(i).getRead().equals(automataDeterministico.getMatriz()[0][j])) {
                    automataDeterministico.getMatriz()[Integer.parseInt(mapa.get(i).getFrom()) + 1][j] = mapa.get(i).getTo();  //setea los campos de la matriz desde el mapeo
                }
            }
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

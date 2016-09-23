/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_afnd_to_afd;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Convert_AFND_to_AFD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        validarArgumentos(args);
        
        for(String arg : args){
            System.out.println("Validando " + arg);
            if(!ValidarXSD.validateXMLSchema("jflap.xsd", arg)){
                System.out.println("Archivo " + arg + " no tiene el formato correcto");
            }
            else{
                System.out.println("Archivo valido");
                String nombre = arg.substring(0, arg.length()-4);
                String ext = arg.substring(arg.length()-4, arg.length());
                AutomataNoDeterministico and = ReadXML.read(arg);
                
                AutomataDeterministico ad = and.convertir_AFND_TO_AFD();
                WriteXML.writeXMLFile(ad, nombre + "-deterministico");
            }
        }
    }
    
    public static void validarArgumentos(String[] args){
        if(args.length == 0){
            System.out.println("Uso: Convert_AFND_to_AFD <archivo1 archivos2 ...>");
            System.exit(0);
        }
    }
}

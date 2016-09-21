/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_afnd_to_afd;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Mario
 */
public class Convert_AFND_to_AFD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadXML xml = new ReadXML();
       
        xml.read(chooseFile().getAbsolutePath());
        WriteXML.writeXMLFile(xml.getAutomata().convertir_AFND_TO_AFD(),"C://Users//malvarado//Desktop//AFD");
        
        
    }
    private static File chooseFile() {
        File r = null;
        JFileChooser fc = new JFileChooser();

        fc.setDialogTitle("Cargar el Automata");

        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".jff");
            }

            @Override
            public String getDescription() {
                return "Automatas";
            }

        });

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            r = fc.getSelectedFile();
        }

        return r;
    }
}

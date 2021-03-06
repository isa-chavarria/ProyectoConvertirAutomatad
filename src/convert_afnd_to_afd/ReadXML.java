package convert_afnd_to_afd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadXML {

    public static AutomataNoDeterministico read(String file) {
        AutomataNoDeterministico automata = new AutomataNoDeterministico();
        try {

            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("state");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    automata.getQ().add(eElement.getAttribute("id"));

                    if (eElement.getElementsByTagName("initial").getLength() != 0) {
                        automata.setI(eElement.getAttribute("id"));
                    }
                    if (eElement.getElementsByTagName("final").getLength() != 0) {
                        automata.getF().add(eElement.getAttribute("id"));
                    }
                }
            }

            NodeList trans = doc.getElementsByTagName("transition");

            for (int j = 0; j < trans.getLength(); j++) {

                Node nNode = trans.item(j);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (!automata.getE().contains(eElement.getElementsByTagName("read").item(0).getTextContent())) {
                        automata.getE().add(eElement.getElementsByTagName("read").item(0).getTextContent());
                    }
                }
            }
            automata.setMatriz(new ArrayList[automata.getQ().size() + 1][automata.getE().size() + 1]);
            for (int i = 0; i < automata.getMatriz().length; i++) {
                for (int j = 0; j < automata.getMatriz()[0].length; j++) {
                    automata.getMatriz()[i][j] = new ArrayList<>();
                }
            }
            for (int u = 0; u < automata.getE().size(); u++) {     //llena la primera fila con el alfabeto
                automata.getMatriz()[0][u + 1].add(automata.getE().get(u));
            }
            for (int d = 0; d < automata.getQ().size(); d++) {
                automata.getMatriz()[d + 1][0].add(automata.getQ().get(d));
            }

            for (int k = 0; k < trans.getLength(); k++) {

                Node nNode = trans.item(k);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String n = eElement.getElementsByTagName("from").item(0).getTextContent();
                    int x = automata.getQ().indexOf(n);
                    String m = eElement.getElementsByTagName("read").item(0).getTextContent();
                    int y = automata.getE().indexOf(m);
                    String c = eElement.getElementsByTagName("to").item(0).getTextContent();

                    automata.getMatriz()[x + 1][y + 1].add(c);

                }
            }
            System.out.println(automata.toString());
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            System.err.println(e.getMessage());
        }
        
        return automata;
    }
}

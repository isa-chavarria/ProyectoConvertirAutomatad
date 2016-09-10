/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_afnd_to_afd;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author herdoiza
 */
public class WriteXML {

    public static void writeXMLFile(AutomataDeterministico automata, String file) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("structure");
            doc.appendChild(rootElement);

            // type elements
            Element type = doc.createElement("Type");
            type.appendChild(doc.createTextNode("fa"));
            rootElement.appendChild(type);

            // Automaton
            Element automaton = doc.createElement("automaton");
            rootElement.appendChild(automaton);

            for(int i=0;i<5;i++){
                // sate elements
                Element state = doc.createElement("state");
                state.setAttribute("id", ""+i);
                state.setAttribute("name", "q"+i);
                automaton.appendChild(state);

                //x y
                Element x = doc.createElement("x");
                x.appendChild(doc.createTextNode("" + (i * 10)));
                state.appendChild(x);
                Element y = doc.createElement("y");
                y.appendChild(doc.createTextNode("50.0"));
                state.appendChild(y);
                Element finalVar= doc.createElement("final");
                state.appendChild(finalVar);
            }

            for(int i=0;i<5;i++){
                // sate elements
                Element transition = doc.createElement("transition");
                automaton.appendChild(transition);

                //x y
                Element from = doc.createElement("from");
                from.appendChild(doc.createTextNode("" + i));
                transition.appendChild(from);
                Element to = doc.createElement("to");
                to.appendChild(doc.createTextNode("" + i));
                transition.appendChild(to);
                Element read= doc.createElement("read");
                read.appendChild(doc.createTextNode("" + (i%2)));
                transition.appendChild(read);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file + ".jff"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}

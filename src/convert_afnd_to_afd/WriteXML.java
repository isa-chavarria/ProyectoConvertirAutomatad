/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_afnd_to_afd;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author herdoiza
 */
public class WriteXML {

    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static void writeXMLFile(AutomataDeterministico automata, String file) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Comment comment = doc.createComment("Created with JFLAP 6.4.");
            doc.appendChild(comment);

            Element rootElement = doc.createElement("structure");
            doc.appendChild(rootElement);

            // type elements
            Element type = doc.createElement("type");
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
            StreamResult result2 = new StreamResult(new StringWriter());

            transformer.transform(source, result2);

            String xmlString = result2.getWriter().toString();
            xmlString = prettyFormat(xmlString, 4);
            xmlString = xmlString.replaceAll("    ", "\t");
            xmlString = xmlString.replaceAll("\r\n", "&#13;\n");
            xmlString = xmlString.replaceAll("</structure>&#13;", "</structure>");
            xmlString = xmlString.replaceAll("encoding=\"UTF-8\"", "encoding=\"UTF-8\" standalone=\"no\"");
            System.out.println(xmlString);
            BufferedWriter writer = null;
            writer = new BufferedWriter( new FileWriter(file + ".jff"));
            writer.write( xmlString);
            if(writer != null)
                writer.close();


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

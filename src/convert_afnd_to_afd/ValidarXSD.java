/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_afnd_to_afd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author david
 */
public class ValidarXSD {
   
   public static boolean validateXMLSchema(String xsdPath, String xmlPath){
      try {
         SchemaFactory factory =
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL urlToDictionary = ValidarXSD.class.getResource("" + xsdPath);
            Schema schema = factory.newSchema(urlToDictionary);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
      } catch (IOException e){
         System.out.println("Exception: "+e.getMessage());
         return false;
      }catch(SAXException e1){
         System.out.println("SAX Exception: "+e1.getMessage());
         return false;
      }
		
      return true;
	
   }
}

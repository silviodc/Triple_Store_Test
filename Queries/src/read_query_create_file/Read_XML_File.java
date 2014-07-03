package read_query_create_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import type_query.Query;

public class Read_XML_File {

		
    public ArrayList <Query>  read_Query(String caminho) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {

    	
    	
    	ArrayList<Query>  query = new ArrayList<Query>();
    	
        File fXmlFile = new File(caminho).getAbsoluteFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        
        //optional, but recommended
        
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("query");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String type =eElement.getElementsByTagName("type").item(0).getTextContent();
                String endpoint =eElement.getElementsByTagName("endpoint").item(0).getTextContent();
                String number = eElement.getElementsByTagName("number").item(0).getTextContent();
                String consulta = eElement.getElementsByTagName("value").item(0).getTextContent();
                
                //constructor => String query,String endPoint, String number
                query.add(new Query(consulta,endpoint,number,type));
            }
        }
        return query;
    }
}

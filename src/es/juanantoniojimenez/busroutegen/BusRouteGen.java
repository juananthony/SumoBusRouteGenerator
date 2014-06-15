package es.juanantoniojimenez.busroutegen;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import es.juanantoniojimenez.busroutegen.model.AdjacencyMatrix;
import es.juanantoniojimenez.busroutegen.model.Connection;

import java.io.File;
import java.util.ArrayList;

public class BusRouteGen {
	

	public static void main(String argv[]){
		
		try{
			File fXmlFile = new File("/Users/juananthony/Desktop/castellana/castellana.net.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			ArrayList<Connection> connections = new ArrayList<Connection>(); 
			AdjacencyMatrix matrix = new AdjacencyMatrix();
			
			doc.getDocumentElement().normalize();
			 
			NodeList nList = doc.getElementsByTagName("connection");
			
			matrix.addConnectionsList(nList);
			
			String fromEdge = "131751221#0";
			
			String toEdge = "76817092#3";
			
			//System.out.println(matrix.isConnected(fromEdge, toEdge));
			
//			System.out.println(matrix.getEdgesConnected(fromEdge).toString());
			
//			System.out.println(matrix.toString());
			
			System.out.println("Route: " + matrix.getRoute(fromEdge, toEdge));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

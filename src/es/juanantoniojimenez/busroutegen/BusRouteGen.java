package es.juanantoniojimenez.busroutegen;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import es.juanantoniojimenez.busroutegen.model.AdjacencyMatrix;
import es.juanantoniojimenez.busroutegen.model.Connection;
import es.juanantoniojimenez.busroutegen.model.Scene;
import es.juanantoniojimenez.busroutegen.model.Simulation;
import es.juanantoniojimenez.busroutegen.model.SimulationEngine;
import es.juanantoniojimenez.busroutegen.util.SumoProperties;

import java.io.File;
import java.util.ArrayList;

public class BusRouteGen {
	

	public static void main(String argv[]){
		
		try{
//			File fXmlFile = new File("/Users/juananthony/Documents/Proyecto/castellana/castellana.net.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//			
//			ArrayList<Connection> connections = new ArrayList<Connection>(); 
//			AdjacencyMatrix matrix = new AdjacencyMatrix();
//			
//			doc.getDocumentElement().normalize();
//			 
//			NodeList nList = doc.getElementsByTagName("connection");
//			
//			matrix.addConnectionsList(nList);
//			
//			String fromEdge = "131751221#0";
//			
//			String toEdge = "118934456#1";
//				
//			//System.out.println(matrix.isConnected(fromEdge, toEdge));
//			
//			System.out.println(matrix.getEdgesConnected(fromEdge).toString());
//			
//			System.out.println(matrix.toString());
//			
//			System.out.println("Route: " + matrix.getRoute(fromEdge, toEdge));
			
			long start = System.currentTimeMillis();
			
//			Scene scene = new Scene(1, "/Users/juananthony/Documents/Proyecto/castellana/scenePrueba", 
//					"/Users/juananthony/Documents/Proyecto/castellana/castellana.net.xml", 
//					"/Users/juananthony/Documents/Proyecto/castellana/bus/castellana.aditional.xml", 
//					"/Users/juananthony/Documents/Proyecto/castellana/flow3/castellana.generated-trips.xml", 
//					"/Users/juananthony/Documents/Proyecto/castellana/tripinfo.aux.out.xml",
//					" --tripinfo-output ");
//			
//			scene.runAllSimulations();
			
			String simPath = SumoProperties.get("simPath");
			String netFile = SumoProperties.get("netFile");
			String flowFile = SumoProperties.get("flowFile");
			String addFile = SumoProperties.get("addFile");
			
			String outputType = SumoProperties.get("outputType");
			
			SimulationEngine engine = new SimulationEngine(simPath,
					netFile, 
					flowFile, 
					addFile, 
					outputType);
			
			engine.run();
			
			float elapsedTimeSec = (System.currentTimeMillis() - start)/1000F;
			
			System.out.println(engine.toString() + "\n\n\nTime: " + elapsedTimeSec + "s");
			
//			System.out.println("Score: " + scene.getMeanScore() + "\nTime: " + elapsedTimeSec + "s");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

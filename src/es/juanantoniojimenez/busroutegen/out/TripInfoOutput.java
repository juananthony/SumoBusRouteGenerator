package es.juanantoniojimenez.busroutegen.out;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.juanantoniojimenez.busroutegen.exception.SumoException;
import es.juanantoniojimenez.busroutegen.model.TripInfo;

public class TripInfoOutput {
	private HashMap<String, TripInfo> hash = new HashMap<String, TripInfo>();
	
	/**
	 * Empty constructor.
	 */
	public TripInfoOutput() {
		
	}
	
	/**
	 * Constructor using an output file.
	 * 
	 * @param outputFile Path of output file.
	 * @throws SumoException Throws a SumoException if some error appears.
	 */
	public TripInfoOutput(String outputFile) throws SumoException {
		File fXmlFile = new File("/Users/juananthony/Desktop/castellana/castellana.net.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SumoException("ERROR: Can't parse configuration creating a newDocumentBuilder.");
		}
		try {
			Document doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			throw new SumoException("ERROR: parsing xml. SAXException.");
		} catch (IOException e) {
			throw new SumoException("ERROR: Error loading xml file. IOException.");
		}
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, TripInfo> getHash() {
		return hash;
	}

	public void setHash(HashMap<String, TripInfo> hash) {
		this.hash = hash;
	}
}

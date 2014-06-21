package es.juanantoniojimenez.busroutegen.out;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.juanantoniojimenez.busroutegen.exception.SumoException;
import es.juanantoniojimenez.busroutegen.model.TripInfo;

public class TripInfoOutput {
	private HashMap<String, TripInfo> tripInfos = new HashMap<String, TripInfo>();
	
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
		File fXmlFile = new File(outputFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SumoException("ERROR: Can't parse configuration creating a newDocumentBuilder.");
		}
		try {
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("tripinfo");
			for (int i = 0; i < nList.getLength(); i++) {
				TripInfo tripInfo = new TripInfo(nList.item(i));
				tripInfos.put(tripInfo.getId(), tripInfo);
			}
		} catch (SAXException e) {
			throw new SumoException("ERROR: parsing xml. SAXException.");
		} catch (IOException e) {
			throw new SumoException("ERROR: Error loading xml file. IOException.");
		}
		
	}
	
	/**
	 * Returns the mean speead of simulation.
	 * @return double with the mean speed of all trip infos in simulation.
	 */
	public double getMeanSpeed() {
		double retValue = 0.0;
		ArrayList<TripInfo> trips = new ArrayList(tripInfos.values());
		
		for(TripInfo trip : trips) {
			retValue += (trip.getRouteLength()/trip.getDuration());
		}
		
		retValue = retValue / trips.size();
		return retValue;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, TripInfo> getHash() {
		return tripInfos;
	}

	/**
	 * 
	 * @param hash
	 */
	public void setHash(HashMap<String, TripInfo> hash) {
		this.tripInfos = hash;
	}
}

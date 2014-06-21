package es.juanantoniojimenez.busroutegen.model;

import java.io.IOException;
import java.util.HashMap;

import es.juanantoniojimenez.busroutegen.exception.SumoException;
import es.juanantoniojimenez.busroutegen.out.TripInfoOutput;

public class Simulation {
	private HashMap<String, Edge> edges = new HashMap<String, Edge>();
	
	private String mapFile;
	
	private String additionalFile;
	
	private String tripFile;
	
	private String outputFile;
	
	private String outputType = " --tripinfo-output ";
	
	private String sumoPath = "/Applications/sumo-0.20.0/bin/sumo";
	
	private String sumoGuiPath = "/Applications/sumo-0.20.0/bin/sumo-gui";
	
	/**
	 * Simulation constructor using map, additional, and out files.
	 * 
	 * @param mapFile Sumo map file.
	 * @param addFile Additional file (bus info).
	 * @param outFile Out path file.
	 * @param outType Out type of file.
	 */
	public Simulation(String mapFile, String addFile, String tripFile, String outFile, String outType) {
		this.additionalFile = addFile;
		this.mapFile = mapFile;
		this.tripFile = tripFile;
		this.outputFile = outFile;
		this.outputType = outType;
	}
	
	public boolean run() throws SumoException {
		boolean retValue = true;
		Runtime rt = Runtime.getRuntime();
		
		try {
			String cli = sumoPath + " -n " + mapFile + " -a " + additionalFile + " -r " + tripFile + outputType + outputFile;
			Process pr = rt.exec(cli);
			pr.waitFor();
			TripInfoOutput output = new TripInfoOutput(outputFile);
			System.out.println("Mean Speed: " + output.getMeanSpeed());
		} catch (IOException e) {
			retValue = false;
			throw new SumoException("ERROR: executing Sumo simulation");
		} catch (InterruptedException e) {
			retValue = false;
			throw new SumoException("ERROR: Sumo Process was interrupted");
		}
		
		return retValue;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Edge> getEdges() {
		return edges;
	}

	/**
	 * 
	 * @param edges
	 */
	public void setEdges(HashMap<String, Edge> edges) {
		this.edges = edges;
	}

	/**
	 * 
	 * @return
	 */
	public String getMapFile() {
		return mapFile;
	}

	/**
	 * 
	 * @param mapFile
	 */
	public void setMapFile(String mapFile) {
		this.mapFile = mapFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getAdditionalFile() {
		return additionalFile;
	}

	/**
	 * 
	 * @param additionalFile
	 */
	public void setAdditionalFile(String additionalFile) {
		this.additionalFile = additionalFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * 
	 * @param outputFile
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getOutputType() {
		return outputType;
	}

	/**
	 * 
	 * @param outputType
	 */
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getSumoPath() {
		return sumoPath;
	}

	public void setSumoPath(String sumoPath) {
		this.sumoPath = sumoPath;
	}

	public String getSumoGuiPath() {
		return sumoGuiPath;
	}

	public void setSumoGuiPath(String sumoGuiPath) {
		this.sumoGuiPath = sumoGuiPath;
	}
}

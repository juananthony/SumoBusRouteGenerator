package es.juanantoniojimenez.busroutegen.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
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
	
	private Double score;
	
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
	
	/**
	 * 
	 * @return
	 * @throws SumoException
	 */
	public boolean run() throws SumoException {
		boolean retValue = true;

		String cli = sumoPath;
		cli += " -n " + mapFile;
		cli += " -a " + additionalFile;
		cli += " -r " + tripFile;
		cli += outputType + outputFile;
		
		executeCommand(cli);

		TripInfoOutput sim = new TripInfoOutput(outputFile);
		
		setScore(sim.getMeanSpeed());
		
		return retValue;
	}
	
	/**
	 * 
	 * @param command
	 * @return
	 */
	private String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
//			p.waitFor();
			BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getTripFile() {
		return tripFile;
	}

	public void setTripFile(String tripFile) {
		this.tripFile = tripFile;
	}
}

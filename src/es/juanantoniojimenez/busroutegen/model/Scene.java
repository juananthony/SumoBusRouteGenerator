package es.juanantoniojimenez.busroutegen.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import es.juanantoniojimenez.busroutegen.exception.SumoException;
import es.juanantoniojimenez.busroutegen.util.Terminal;

/**
 * 
 * @author juananthony
 *
 */
public class Scene {
	
	private Long id = 1L;

	private ArrayList<Simulation> sims;
	
	private Integer numSimulations = 5;
	
	private String mapFile;
	
	private String additionalFile;
	
	private String tripFile;
	
	private String outputFile;
	
	private String outputFileRoot;
	
	private String outputType = " --tripinfo-output ";
	
	private Double meanScore;
	
	private boolean executed = false;
	
	private String sceneDir;
	
	private static String CREATE_SCENE_DIR_CMD = "mkdir ";
	
	/**
	 * 
	 * @param mapFile
	 * @param addFile
	 * @param tripFile
	 * @param outputFile
	 * @param outputType
	 */
	public Scene(String mapFile, String addFile, String tripFile, String outFile, String outType) {
		
		this.mapFile = mapFile;
		this.additionalFile = addFile;
		this.tripFile = tripFile;
		this.outputFile = outFile;
		setOutputFileRoot(outFile);
		this.outputType = outType;
		
		setSceneDir(mapFile);
				
		createSimulations(numSimulations, mapFile, addFile, tripFile, outType);
	}
	
	
	/**
	 * 
	 * @param numSims
	 * @param mapFile
	 * @param addFile
	 * @param tripFile
	 * @param outFile
	 * @param outType
	 */
	public Scene(int numSims, String mapFile, String addFile, String tripFile, String outFile, String outType) {
		
		this.mapFile = mapFile;
		this.additionalFile = addFile;
		this.tripFile = tripFile;
		this.outputFile = outFile;
		setOutputFileRoot(outFile);
		this.outputType = outType;
		numSimulations = numSims;
		
		setSceneDir(mapFile);
		Terminal.executeCommand(CREATE_SCENE_DIR_CMD + sceneDir);
		
		createSimulations(numSimulations, mapFile, addFile, tripFile, outType);
	}
	
	/**
	 * 
	 * @param mapFile
	 * @param addFile
	 * @param tripFile
	 * @param outputFile
	 * @param outputType
	 */
	public Scene(Long id, String mapFile, String addFile, String tripFile, String outFile, String outType) {
		this.id = id;
		this.mapFile = mapFile;
		this.additionalFile = addFile;
		this.tripFile = tripFile;
		this.outputFile = outFile;
		setOutputFileRoot(outFile);
		this.outputType = outType;
		
		setSceneDir(mapFile);
				
		createSimulations(numSimulations, mapFile, addFile, tripFile, outType);
	}
	
	
	/**
	 * 
	 * @param numSims
	 * @param mapFile
	 * @param addFile
	 * @param tripFile
	 * @param outFile
	 * @param outType
	 */
	public Scene(Long id, int numSims, String mapFile, String addFile, String tripFile, String outFile, String outType) {
		this.id = id;
		this.mapFile = mapFile;
		this.additionalFile = addFile;
		this.tripFile = tripFile;
		this.outputFile = outFile;
		setOutputFileRoot(outFile);
		this.outputType = outType;
		numSimulations = numSims;
		
		setSceneDir(mapFile);
		Terminal.executeCommand(CREATE_SCENE_DIR_CMD + sceneDir);
		
		createSimulations(numSimulations, mapFile, addFile, tripFile, outType);
	}

	/**
	 * Create Simulations ready to be executed.
	 * 
	 * @param mapFile
	 * @param addFile
	 * @param tripFile
	 * @param outType
	 */
	public void createSimulations(int numSims, String mapFile, String addFile, String tripFile, String outType) {
		numSimulations = numSims;
		
		sims = new ArrayList<Simulation>();
				
		for (int i = 0; i < numSimulations; i++) {
			sims.add(new Simulation(mapFile, 
									addFile, 
									tripFile, 
									sceneDir + "/" + outputFileRoot + "." + String.format("%03d", i + 1) + ".xml", 
									outType));
		}
	}
	
	/**
	 * Call to all run() Simulation's method.
	 * 
	 * @return return TRUE if all simulation was success, FALSE in otherwise.
	 * @throws SumoException 
	 */
	public boolean runAllSimulations() throws SumoException {
		boolean retValue = true;
		
		for(Simulation sim : sims) {
			sim.run();
		}
		executed = true;
		
		return retValue;
	}
	
	/**
	 * Returns the mean score of all Simulations. If this method is called before any Simulation was executed, it method returns null.
	 * @return If this method is called before any Simulation was executed, it method returns null.
	 */
	public Double getMeanScore() {
		if(meanScore == null && executed) {
			meanScore = 0.0;
			for(Simulation sim : sims) {
				meanScore += sim.getScore();
			}
			meanScore = meanScore / numSimulations;
		}
		return meanScore;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Simulation> getSims() {
		return sims;
	}

	/**
	 * 
	 * @param sims
	 */
	public void setSims(ArrayList<Simulation> sims) {
		this.sims = sims;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getNumSimulations() {
		return numSimulations;
	}

	/**
	 * 
	 * @param numSimulations
	 */
	public void setNumSimulations(Integer numSimulations) {
		this.numSimulations = numSimulations;
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
	public String getTripFile() {
		return tripFile;
	}

	/**
	 * 
	 * @param tripFile
	 */
	public void setTripFile(String tripFile) {
		this.tripFile = tripFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getOutputFileRoot() {
		return outputFileRoot;
	}

	/**
	 * This method removes all '.xml' in the output file String
	 * @param outputFileRoot
	 */
	public void setOutputFileRoot(String outputFileRoot) {
		if(outputFileRoot.contains(".xml")) {
			String[] aux = outputFileRoot.split("/");
			this.outputFileRoot = aux[aux.length - 1].replace(".xml", "");
		} else {
			this.outputFileRoot = outputFileRoot;
		}
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

	public void setMeanScore(Double meanScore) {
		this.meanScore = meanScore;
	}


	public String getSceneDir() {
		return sceneDir;
	}


	public void setSceneDir(String sceneDir) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = Calendar.getInstance();
		this.sceneDir = "";
		String[] pathElems = sceneDir.split("/");
		for (int i = 0; i < pathElems.length - 1; i++) {
			this.sceneDir += pathElems[i] + "/";
		}
		this.sceneDir += "scene";
	}
}

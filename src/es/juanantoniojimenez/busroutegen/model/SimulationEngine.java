package es.juanantoniojimenez.busroutegen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.juanantoniojimenez.busroutegen.exception.SumoException;
import es.juanantoniojimenez.busroutegen.util.Terminal;

/**
 * 
 * @author juananthony
 *
 */
public class SimulationEngine {

	private String simulationPath;
	
	private String networkFile;
	
	private String flowFile;
	
	private String additionalFile;
	
	private String outputType;
	
	private Integer numSimulationPerScene = 2;
	
	private HashMap<Integer, Scene> scenes = new HashMap<Integer, Scene>();
	
	private HashMap<Integer, Double> scores = new HashMap<Integer, Double>();
	
	/**
	 * Empty constructor.
	 */
	public SimulationEngine() {
		
	}
	
	/**
	 * 
	 * @param simPath
	 * @param networkFile
	 * @param flowFile
	 * @param additionalFile
	 * @param outputFile
	 */
	public SimulationEngine(String simPath, String networkFile, String flowFile, String additionalFile, String outputType) {
		this.setSimulationPath(simPath);
		this.setNetworkFile(networkFile);
		this.setFlowFile(flowFile);
		this.setAdditionalFile(additionalFile);
		this.setOutputType(outputType);
		
		// First, creates /scenes folder
		Terminal.executeCommand("mkdir " + simPath + "/scenes");
	} 
	
	/**
	 * @throws SumoException 
	 * 
	 */
	public void run() throws SumoException {
		// while solution not found
		boolean searching = true;
		Integer idIncrement = 0;
		//while(searching) {
		while(idIncrement<5) {
			idIncrement++;
			createScene(idIncrement);
			//TODO TEMPORAL
			searching = false;
		}
		
	}
	
	/**
	 * 
	 * @param idScene
	 * @return
	 * @throws SumoException 
	 */
	public Scene createScene(Integer idScene) throws SumoException {
		String scenePath = this.getSimulationPath() + "/scenes/scene_" + idScene;
		
		// Creates scene folder
		String cli = "mkdir " + scenePath;
		Terminal.executeCommand(cli);
		
		// Copy scene files
		//TODO When "bus stops" generator was developed, copy addtional file to scene folder
		
		Scene scene = new Scene(idScene, 
								scenePath, 
								3, 
								simulationPath + "/" + networkFile,
								simulationPath + "/" + additionalFile,		//TODO Modify when "bus stops" generator was developed. 
								simulationPath + "/" + flowFile,
								/*simulationPath + "/" + */networkFile.replace(".xml", "") + ".out",
								" --tripinfo-output "
								);
		scene.runAllSimulations();
		scores.put(idScene, scene.getMeanScore());
		scenes.put(idScene, scene);
		
		return scene;
	}
	
	/**
	 * Returns best score about all simulations.
	 * 
	 * @return Simulations best score
	 */
	public Double getBestScore() {
		ArrayList<Double> values = new ArrayList(scores. values());
		
		Double retValue = (double) -1.0F;
		
		for(Double d : values) {
			if(d > retValue) {
				retValue = d;
			}
		}
		
		return retValue;
	}
	
	/**
	 * Returns scene with the best score.
	 * 
	 * @return Scene's ID with best score
	 */
	public String getBestScene() {
		// Iterator through HashMap
		Iterator it = scores.entrySet().iterator();
		Double max = (double) -1.0F;
		String bestScene = "";
		
		// Iterate all Map
		while(it.hasNext()) {
			Map.Entry<Integer,Double> scene = (Map.Entry<Integer,Double>) it.next();
			
			if(scene.getValue() > max) {
				bestScene = "scene_" + scene.getKey();
			}
		}
		
		return bestScene;
	}
	
	/**
	 * Returns all score with a readable format (table).
	 * 
	 * @return All Scores in a Table
	 */
	public String toString() {
		String out = "";
		Iterator it = scores.entrySet().iterator();
		
		out += "|\t ID \t|\t scene_{id} \t|\t   Score   \t|";
		
		while(it.hasNext()) {
			Map.Entry<Integer,Double> scene = (Map.Entry<Integer,Double>) it.next();
			
			out += "\n|\t" + scene.getKey() + "\t|\tscene_" + scene.getKey() + "\t|\t" + scene.getValue() + "\t|";
		}
		
		return out;
	}
	
	/**
	 * simulationFolder's getter.
	 * @return simulationFolder
	 */
	public String getSimulationPath() {
		return simulationPath;
	}

	/**
	 * simulationFolder's setter.
	 * @param simulationFolder simulationFolder
	 */
	public void setSimulationPath(String simulationFolder) {
		this.simulationPath = simulationFolder;
	}

	/**
	 * networkFile's getter.
	 * @return networkFile
	 */
	public String getNetworkFile() {
		return networkFile;
	}

	/**
	 * networkFile's setter.
	 * @param networkFile networkFile
	 */
	public void setNetworkFile(String networkFile) {
		this.networkFile = networkFile;
	}

	public String getFlowFile() {
		return flowFile;
	}

	public void setFlowFile(String flowFile) {
		this.flowFile = flowFile;
	}

	public String getAdditionalFile() {
		return additionalFile;
	}

	public void setAdditionalFile(String additionalFile) {
		this.additionalFile = additionalFile;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public Integer getNumSimulationPerScene() {
		return numSimulationPerScene;
	}

	public void setNumSimulationPerScene(Integer numSimulationPerScene) {
		this.numSimulationPerScene = numSimulationPerScene;
	}
	
}

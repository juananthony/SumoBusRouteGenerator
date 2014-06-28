package es.juanantoniojimenez.busroutegen.model;

import java.util.HashMap;

import es.juanantoniojimenez.busroutegen.util.Terminal;

/**
 * 
 * @author juananthony
 *
 */
public class SimulationEngine {

	private String simulationFolder;
	
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
	 * @param simulationFolder
	 * @param networkFile
	 * @param flowFile
	 * @param additionalFile
	 * @param outputFile
	 */
	public SimulationEngine(String simulationFolder, String networkFile, String flowFile, String additionalFile, String outputFile) {
		this.setSimulationFolder(simulationFolder);
		this.setNetworkFile(networkFile);
		this.setFlowFile(flowFile);
		this.setAdditionalFile(additionalFile);
		this.setOutputType(outputFile);
	} 
	
	/**
	 * 
	 */
	public void run() {
		// while solution not found
		boolean searching = true;
		Integer idIncrement = 0;
		while(searching) {
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
	 */
	public Scene createScene(Integer idScene) {
		String scenePath = this.getSimulationFolder() + "/scene_" + idScene;
		
		// Creates scene folder
		String cli = "mkdir " + scenePath;
		Terminal.executeCommand(cli);
		
		// Copy scene files
		cli = "cp " + networkFile + "sceneDir";
		Terminal.executeCommand(cli);
		cli = "cp " + additionalFile + "sceneDir";
		Terminal.executeCommand(cli);
		cli = "c+ " + flowFile + "sceneDir";
		Terminal.executeCommand(cli);
		
		Scene scene = new Scene(idScene, 
								scenePath, 
								3, 
								scenePath + "/" + networkFile,
								scenePath + "/" + additionalFile,
								scenePath + "/" + flowFile,
								scenePath + "/" + networkFile.replace(".xml", "") + ".out",
								" --tripinfo-output "
								);
		
		scenes.put(idScene, scene);
		
		return scene;
	}

	/**
	 * simulationFolder's getter.
	 * @return simulationFolder
	 */
	public String getSimulationFolder() {
		return simulationFolder;
	}

	/**
	 * simulationFolder's setter.
	 * @param simulationFolder simulationFolder
	 */
	public void setSimulationFolder(String simulationFolder) {
		this.simulationFolder = simulationFolder;
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

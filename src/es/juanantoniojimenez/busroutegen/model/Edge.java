package es.juanantoniojimenez.busroutegen.model;

/**
 * Sumo Edge class.
 * 
 * @author juananthony
 *
 */
public class Edge {
	
	private String id;
	
	private String from;
	
	private String to;
	
	private String type;
	
	private String spreadType;

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param from
	 * @param to
	 */
	public Edge(String id, String from, String to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 
	 * @return
	 */
	public String getTo() {
		return to;
	}

	/**
	 * 
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public String getSpreadType() {
		return spreadType;
	}

	/**
	 * 
	 * @param spreadType
	 */
	public void setSpreadType(String spreadType) {
		this.spreadType = spreadType;
	}
	
	

}

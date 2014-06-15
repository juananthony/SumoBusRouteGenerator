package es.juanantoniojimenez.busroutegen.model;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Connection {

	private String id;
	
	private Edge from;
	
	private Edge to;
	
	private String fromLane;
	
	private String toLane;
	
	private String via;
	
	private String dir;
	
	private String state;
	
	/**
	 * Empty constructor
	 */
	public Connection() {
		
	}
	
	/**
	 * Constructor with a Node from the XML.
	 * 
	 * @param node node
	 */
	public Connection(Node node) {
		NamedNodeMap attr = node.getAttributes();
		
		// Get the from-edgeID from the node 
		String edgeIdAux;
		if(attr.item(1).toString().split("=")[0].equals("from")){
			edgeIdAux = attr.item(1).toString().split("=")[1].split("\"")[1];
		} else {
			edgeIdAux = getAttr(attr, "from");
		}
		
		// Creates a new Edge object to the "from" edge
		this.from = new Edge(edgeIdAux, null, null);
		
		// Sets connection's id with edge names
		this.id = edgeIdAux + "&-&";
		
		// Get the to-edgeID from the node
		if(attr.item(4).toString().split("=")[0].equals("to")){
			edgeIdAux = attr.item(4).toString().split("=")[1].split("\"")[1];
		} else {
			edgeIdAux = getAttr(attr, "to");
		}
		
		// Creates a new Edge object to the "to" edge
		this.to = new Edge(edgeIdAux, null, null);
		
		// Sets to the connection's id the edgeID
		this.id += edgeIdAux;
	}
	
	/**
	 * 
	 * @param attr
	 * @param search
	 * @return
	 */
	public String getAttr(NamedNodeMap attr, String search) {
		String ret = null;
		for(int i = 0; i < attr.getLength(); i++) {
			String[] array = attr.item(i).toString().split("=");
			if(array[0].equals(search)) {
				ret = array[1].split("\"")[1];
				break;
			}
		}
		return ret;
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
	public Edge getFrom() {
		return from;
	}

	/**
	 * 
	 * @param from
	 */
	public void setFrom(Edge from) {
		this.from = from;
	}

	/**
	 * 
	 * @return
	 */
	public Edge getTo() {
		return to;
	}

	public void setTo(Edge to) {
		this.to = to;
	}

	public String getFromLane() {
		return fromLane;
	}

	public void setFromLane(String fromLane) {
		this.fromLane = fromLane;
	}

	public String getToLane() {
		return toLane;
	}

	public void setToLane(String toLane) {
		this.toLane = toLane;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

package es.juanantoniojimenez.busroutegen.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * AdjacencyMatrix
 * 
 * @author juananthony
 *
 */
public class AdjacencyMatrix {
	private HashMap<String, Boolean> hash = new HashMap<String, Boolean>();

	
	
	/**
	 * Returns TRUE if "from" edge is connected to the "to" edge, FALSE in otherwise.
	 * 
	 * @param from Edge
	 * @param to Edge
	 * @return TRUE if this edges are connected and false if not.
	 */
	public Boolean isConnected(Edge from, Edge to) {
		Boolean ret = false;
		
		ArrayList<String> hashKeys = new ArrayList(hash.keySet());
		
		for (String connId : hashKeys) {
			if (connId.equals(from.getId() + "-" + to.getId())
					&& hash.get(connId)) {
				ret = true;
			}
		}
		
		return ret;
	}
	
	/**
	 * Returns TRUE if "from" edge is connected to the "to" edge, FALSE in otherwise.
	 * 
	 * @param from Edge
	 * @param to Edge
	 * @return TRUE if this edges are connected and false if not.
	 */
	public Boolean isConnected(String from, String to) {
		Boolean ret = false;
		
		ArrayList<String> hashKeys = new ArrayList(hash.keySet());
		
		for (String connId : hashKeys) {
			if (connId.equals(from + "-" + to)
					&& hash.get(connId)) {
				ret = true;
			}
		}
		
		return ret;
	}
	
	/**
	 * Add a new Connection to the AdjacencyMatrix.
	 * 
	 * @param conn Connection object
	 * @return
	 */
	public boolean addConnection(Connection conn) {
		boolean ret = true;
		
		// Checks if this connection doesn't exist in the matrix
		if(!isConnected(conn.getFrom(), conn.getTo())) {
			hash.put(conn.getId(), true);
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	/**
	 * Add a NodeList of Connections from XML.
	 * 
	 * @param nList list of nodes
	 * @return true if operation was success, false in otherwise
	 */
	public boolean addConnectionsList(NodeList nList) {
		boolean ret = true;
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			
			Connection conn = new Connection(node);
			
			boolean retAdd = addConnection(conn);
			ret = ret? retAdd : ret;
		}
		
		return ret;
	}
	
	/**
	 * Removes a connection from the adjacency matrix.
	 * 
	 * @param conn Connection to remove.
	 * @return Return TRUE if operation was success, FALSE in otherwise.
	 */
	public boolean removeConnection(Connection conn) {
		boolean ret = true;
		
		// Checks if this connection exists in the matrix
		if(isConnected(conn.getFrom(), conn.getTo())) {
			removeValue(conn.getId());
		} else {
			ret = false;
		}
		return ret;
		
	}
	
	/**
	 * 
	 */
	public String toString(){
		String ret = "";
		
		ArrayList<String> hashKeys = new ArrayList(hash.keySet());
		
		for(String key : hashKeys) {
			ret += key + " - " + hash.get(key) + "\n";
		}
		
		return ret;
	}
	
	/**
	 * Put a connectio to false
	 * @param key
	 * @return
	 */
	public Boolean removeValue(String key) {
		boolean ret = false;
		if(hash.containsKey(key)) {
			if(hash.get(key)) {
				hash.put(key, false);
			}
		}
		
		return ret;
	}
	
	
	
}

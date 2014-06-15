package es.juanantoniojimenez.busroutegen.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
			if (connId.equals(from.getId() + "&-&" + to.getId())
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
			if (connId.equals(from + "&-&" + to)
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
	
	/**
	 * 
	 * 
	 * @param edge
	 * @return
	 */
	public ArrayList<String> getEdgesConnected(String edge) {
		ArrayList<String> hashKeys = new ArrayList(hash.keySet());
		ArrayList<String> ret = new ArrayList<String>();
		
		for(String key : hashKeys) {
			String[] diff = key.split("&-&");
			if(diff[0].equals(edge)) {
				ret.add(diff[1]);
			}
		}
		
		return ret;
	}
	
	private class LinkedEdge {
		public String id;
		public LinkedEdge parent;
		public LinkedEdge(String edge, LinkedEdge parent){
			this.id = edge;
			this.parent = parent;
		}
		@Override
		public String toString(){
			return id;
		}
	}
	
	/**
	 * Get the route from two Edges using a Graph Search Algorithm.
	 * 
	 * @param from Edge ID
	 * @param to Edge ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getRoute(String from, String to) throws Exception {
		
		ArrayList<String> route = new ArrayList<String>();
		
		// Open list with 'from' edge
		Queue<LinkedEdge> open = new LinkedList<LinkedEdge>();
		open.add(new LinkedEdge(from, null));
		
		// 'Close' list empty.
		Queue<LinkedEdge> close = new LinkedList<LinkedEdge>();
		
		// Boolean value to control loop
		boolean routeFind = false;
		
		// m element to the algorithm
		LinkedEdge m = null;
		
		// Loop until solution appears or error appears 
		while(!routeFind) {
			
			// If open list is empty, throw an exception
			if(open.isEmpty()) {
				throw new Exception();
			}
			
			// Get Edge from Open List
			m = open.poll();
			close.add(m);
			
			// Get all connected edges from m
			ArrayList<String> expandM = getEdgesConnected(m.id);
			
			// Check if any of childs of m is the solution (to)
			for(String auxM : expandM) {
				if(auxM.equals(to)) {
					routeFind = true;
					break;
				}
				if(!open.contains(auxM)) {
					LinkedEdge auxLinked = new LinkedEdge(auxM, m);
					open.add(auxLinked);
				}
			}
			
		}
		
		// Convert close list into return route
		Stack<String> stack = new Stack<String>();
		while(true){
			int size = close.size() - 1;
			
			if(m == null || m.id.equals(from)) {
				break;
			}
			stack.push(m.id);
			m = m.parent;
		}
		for(int i = 0; i < stack.size(); i++) {
			route.add(stack.pop());
		}
		
		return route;
	}
	
}

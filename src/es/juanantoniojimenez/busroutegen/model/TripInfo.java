package es.juanantoniojimenez.busroutegen.model;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class TripInfo {

	private String id;
	
	private double depart;
	
	private String departLane;
	
	private double departPos;
	
	private double departSpeed;
	
	private double departDelay;
	
	private double arrival;
	
	private String arrivalLane;
	
	private double arrivalPos;
	
	private double arrivalSpeed;
	
	private double duration;
	
	private double routeLength;
	
	private double waitSteps;
	
	private double rerouteNo;
	
	private String devices;
	
	private String vType;
	
	private String vaporized;
	
	/**
	 * Empty constructor. 
	 */
	public TripInfo() {
		
	}
	
	/**
	 * Constructor by Node element of XML output. 
	 * @param node Node element of TripInfo output.
	 */
	public TripInfo(Node node) {
		NamedNodeMap attr = node.getAttributes();
		
		for(int i = 0; i < attr.getLength(); i++) {
			String[] elem = attr.item(i).toString().split("=");
			if(elem[0].equals("id")) {
				id = elem[1].split("\"")[1];
			}
			if(elem[0].equals("depart")) {
				depart = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("departLane")) {
				depart = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("departPos")) {
				departPos = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("departSpeed")) {
				departSpeed = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("departDelay")) {
				departDelay = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("arrival")) {
				arrival = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("arrivalLane")) {
				arrivalLane = elem[1].split("\"")[1];
			}
			if(elem[0].equals("arrivalPos")) {
				arrivalPos = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("arrivalSpeed")) {
				arrivalSpeed = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("duration")) {
				duration = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("routeLength")) {
				routeLength = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("waitSteps")) {
				waitSteps = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("rerouteNo")) {
				rerouteNo = Double.parseDouble(elem[1].split("\"")[1]);
			}
			if(elem[0].equals("devices")) {
				devices = elem[1].split("\"")[1];
			}
			if(elem[0].equals("vType")) {
				vType = elem[1].split("\"")[1];
			}
			if(elem[0].equals("vaporized")) {
				vaporized = elem[1].split("\"")[1];
			}
		}
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
	public double getDepart() {
		return depart;
	}

	/**
	 * 
	 * @param depart
	 */
	public void setDepart(double depart) {
		this.depart = depart;
	}

	public String getDepartLane() {
		return departLane;
	}

	public void setDepartLane(String departLane) {
		this.departLane = departLane;
	}

	public double getDepartPos() {
		return departPos;
	}

	public void setDepartPos(double departPos) {
		this.departPos = departPos;
	}

	public double getDepartSpeed() {
		return departSpeed;
	}

	public void setDepartSpeed(double departSpeed) {
		this.departSpeed = departSpeed;
	}

	public double getDepartDelay() {
		return departDelay;
	}

	public void setDepartDelay(double departDelay) {
		this.departDelay = departDelay;
	}

	public double getArrival() {
		return arrival;
	}

	public void setArrival(double arrival) {
		this.arrival = arrival;
	}

	public String getArrivalLane() {
		return arrivalLane;
	}

	public void setArrivalLane(String arrivalLane) {
		this.arrivalLane = arrivalLane;
	}

	public double getArrivalPos() {
		return arrivalPos;
	}

	public void setArrivalPos(double arrivalPos) {
		this.arrivalPos = arrivalPos;
	}

	public double getArrivalSpeed() {
		return arrivalSpeed;
	}

	public void setArrivalSpeed(double arrivalSpeed) {
		this.arrivalSpeed = arrivalSpeed;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getRouteLength() {
		return routeLength;
	}

	public void setRouteLength(double routeLength) {
		this.routeLength = routeLength;
	}

	public double getWaitSteps() {
		return waitSteps;
	}

	public void setWaitSteps(double waitSteps) {
		this.waitSteps = waitSteps;
	}

	public double getRerouteNo() {
		return rerouteNo;
	}

	public void setRerouteNo(double rerouteNo) {
		this.rerouteNo = rerouteNo;
	}

	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public String getVaporized() {
		return vaporized;
	}

	public void setVaporized(String vaporized) {
		this.vaporized = vaporized;
	}
}

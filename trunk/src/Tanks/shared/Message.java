package Tanks.shared;

import java.io.Serializable;

import Tanks.shared.mapElements.GameObject;

public class Message implements Serializable {
	
	private int counter;
	
	public String receiver;
	public String sender;
	public String extraString;
	public GameObject object;

}

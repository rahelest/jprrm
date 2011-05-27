package Tanks.shared;

import java.io.Serializable;

import Tanks.shared.mapElements.GameObject;

public class Message implements Serializable {
	
	private String receiver;
	private String sender;
	private GameObject object;

}

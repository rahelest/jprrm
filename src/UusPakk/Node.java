package UusPakk;

import java.util.ArrayList;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Node {
	
	/**
	 * Tipu kauguse puu juurelemendist.
	 */
	private int depth;
	/**
	 * Tipule omistatud väärtus.
	 */
	private int value;
	/**
	 * Tipule omistatud kaal.
	 */
	private int weight;
	/**
	 * Ahne algoritmi ennustus sellele tipule, teatud juhtudel ka väärtuse/kaalu suhe..
	 */
	private float bound;
	/**
	 * Väärtuse / kaalu suhe.
	 */
	private float ratio;
	/**
	 * Järgmise elemendi viide pinu jaoks.
	 */
	private Node next;
	/**
	 * Nimekiri valitud elementidest.
	 */
	private ArrayList<Boolean> valikud;
	
	/**
	 * Konstruktor.
	 */
	public Node() {
		depth = -1;
		value = 0;
		weight = 0;
		bound = 0;
		ratio = 0;
	}
	
	/**
	 * Konstruktor koos mainitud väljade väärtustamisega.
	 * @param newDepth Tipu sügavus.
	 * @param newValue Tipu väärtus.
	 * @param newWeight Tipu kaal.
	 */
	public Node (int newDepth, int newValue, int newWeight) {
		depth = newDepth;
		value = newValue;
		weight = newWeight;
		bound = 0;
		if (weight == 0) {
			ratio = 0;
		} else {
			ratio = ((float) value) / ((float) weight);
		}
	}
	
	/**
	 * Väärtuse / kaalu suhte küsimine.
	 * @return Väärtuse / kaalu suhe.
	 */
	public float getRatio() {
		return ratio;
	}

	/**
	 * Konstruktor pinu jaoks määrab ainult väärtuse ja järgmise elemendi viite.
	 * @param val Tipu väärtus.
	 * @param n Järgmise elemendi viide.
	 */
	public Node(int val, Node n) {
		value = val;
	}
	
	/**
	 * Elemendi väärtuse küsimine.
	 * @return Elemendi väärtus.
	 */
	public int get() {
		return value;
	}

	/**
	 * Elemendi sügavuse küsimine.
	 * @return Elemendi sügavus.
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Elemendi sügavuse määramine.
	 * @param Elemendi uus sügavus.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Elemendi väärtuse küsimine.
	 * @return Elemendi väärtus.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Elemendi väärtuse määramine.
	 * @param Määratav väärtus.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Elemendi kaalu küsimine.
	 * @return Elemendi kaal.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Elemendi kaalu määramine.
	 * @param weight Elemendi uus kaal.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Elemendi tulemushinnangu küsimine.
	 * @return the bound Elemendi tulemushinnang.
	 */
	public float getBound() {
		return bound;
	}

	/**
	 * Elemendi tulemushinnangu määramine.
	 * @return the bound Elemendi tulemushinnang.
	 */
	public void setBound(float bound) {
		this.bound = bound;
	}
	
	/**
	 * Järgmise elemendi küsimine.
	 * @return Järgmine element.
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Järgmise elemendi määramine.
	 * @return Määratav element.
	 */
	public void setNext (Node n) {
		next = n;
	}
	
	/**
	 * Stringesitus paremaks loetavuseks.
	 * @return Kirjeldus.
	 */
	public String toString() {
//		return "\nNode - Väärtus: " + value + " Kaal: " + weight;
		return "( " + value + ", " + weight + ", " + ratio + " ) ";
	}

	/**
	 * Valikute nimekirja küsimine.
	 * @param valikud Valikute nimekiri.
	 */
	public ArrayList<Boolean> getValikud() {
		return valikud;
	}

	/**
	 * Valikute nimekirja määramine.
	 * @param valikud Uus valikute nimekiri.
	 */
	public void setValikud(ArrayList<Boolean> valikud) {
		this.valikud = valikud;
	}

	/**
	 * Valikute nimekirjast kasutute elementide eemaldamine (false-d lõpus).
	 * @return Uus ja parem node.
	 */
	public Node trimZeros() {
		for (int i = valikud.size() - 1; i >= 0; i++) {
			if (valikud.get(i)) {
//				System.out.print("1");
				break;
			}
			else {
//				System.out.print("0");
				valikud.remove(valikud.size() - 1);
			}
		}
		return this;
	}
	
	

}

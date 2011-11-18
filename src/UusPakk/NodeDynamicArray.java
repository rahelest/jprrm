package UusPakk;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 * Muutuva suurusega array, hoiab Node-e meeles.
 */

public class NodeDynamicArray {
	
	/**
	 * Massiiv, mis hoiab elemente meeles.
	 */
	private Node[] dynamicArray;
	/**
	 * Massiivi viimase reaalse elemendi indeks.
	 */
	private int lastElementsIndex = -1;

	/**
	 * Konstruktor.
	 * @param Massiivi suurus.
	 */
	public NodeDynamicArray(int n) {
		dynamicArray = new Node[n];
	}
	/**
	 * Elemendi massiivi lisamine.
	 * @param Lisatav element.
	 * @return Lisatud elemendi indeks.
	 */
	public int add(Node n) {
		lastElementsIndex++;
		
		if (lastElementsIndex >= dynamicArray.length) {
			Node[] temp = new Node[dynamicArray.length * 2];
			for (int i = 0; i < dynamicArray.length; i++) {
				temp[i] = dynamicArray[i];
			}
			dynamicArray = temp;
		}
		
		dynamicArray[lastElementsIndex] = n;
		
		return lastElementsIndex;
	}
	
	/**
	 * Viimase elemendi massiivist eemaldamine.
	 * @return Eemaldatud element.
	 */
	public Node rem() {
		
		Node tempNode = dynamicArray[lastElementsIndex];
		dynamicArray[lastElementsIndex] = null;
		
		if (lastElementsIndex < dynamicArray.length / 4) {
			Node[] temp = new Node[dynamicArray.length  / 4];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = dynamicArray[i];
			}
			dynamicArray = temp;
		} else if (lastElementsIndex < 0) {
			return null;
		}
		
		lastElementsIndex--;
		return tempNode;
	}
	/**
	 * Indeksi järgi massiivist elemendi küsimine.
	 * @param Indeks.
	 * @return Leitud element.
	 */
	public Node get(int i) {
		return dynamicArray[i];
	}
	
	/**
	 * Massiivi elemendi indeksi järgi välja vahetamine.
	 * @param Asendav element.
	 * @param Indeksikoht, mida vahetatakse.
	 */
	public void put(Node n, int i) {
		try {
			if (dynamicArray.length <= i ) {
				add(n);
				return;
			}
			dynamicArray[i] = n;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Vale indeks: " + i + " ning pikkus oli " + dynamicArray.length);
			System.out.println(this.toString());
		}
	}
	
	/**
	 * Jada pikkus, arvestades ka vaba ruumi.
	 * @return Pikkus.
	 */
	public int len() {
		return dynamicArray.length;
	}
	
	/**
	 * Kas jada on tühi.
	 * @return Vastav tõeväärtus.
	 */
	public boolean isEmpty() {
		if (lastElementsIndex < 0) return true;
		else return false;
	}

	/**
	 * Massiivi reaalsete elementide hulk - 1.
	 * @return Massiivi viimase elemendi indeks.
	 */
	public int getLastElementIndex() {
		return lastElementsIndex;
	}
	
	/**
	 * Stringesitus paremaks loetavuseks.
	 * @return Kirjeldus.
	 */
	public String toString() {
		String result = "";
		for (int i = 0;  i <= getLastElementIndex(); i++) {
			if (dynamicArray[i] != null)
			result += dynamicArray[i].toString();
		}
		return result;
	}
}



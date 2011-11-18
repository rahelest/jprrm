package UusPakk;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 * Muutuva suurusega array. Suurus muutub ise vastavalt täidetusele.
 */

public class DynamicArray {
	/**
	 * Massiiv, kus asju hoitakse.
	 */
	protected int[] dynArray;
	/**
	 * Indeks, kus kirjas viimase elemendi indeks, mis määratud.
	 */
	protected int lastElementsIndex = -1;
	
	/**
	 * Konstruktor.
	 * @param Massiivi esialgne suurus.
	 */
	public DynamicArray(int n) {
		dynArray = new int[n];
	}
	
//	public DynamicArray create(int n) {
//		dynArray = new int[n];
//		return this;
//	}
	/**
	 * Elemendi lisamine massiivile, vajadusel kasvatatakse massiivi 2x.
	 * @param Lisatav element.
	 * @return Lisatud elementi indeks.
	 */
	public int add(int x) {
		lastElementsIndex++;
		if (lastElementsIndex >= dynArray.length) {
			int[] temp = new int[dynArray.length * 2];
			for (int i = 0; i < dynArray.length; i++) {
				temp[i] = dynArray[i];
			}
			dynArray = temp;
		}
		dynArray[lastElementsIndex] = x;
		
//		System.out.println("Lisasin väärtuse: " + x + " lastElementsNo: " + lastElementsIndex);
		return lastElementsIndex;
	}
	
	/**
	 * Remove last element of the array.
	 * @return The removed element.
	 */
	
	public int rem() {		
		int temp = dynArray[lastElementsIndex];
		dynArray[lastElementsIndex] = 0;
		if (lastElementsIndex <= dynArray.length / 4) {
			int[] temp2 = new int[dynArray.length  / 4];
			for (int i = 0; i < temp2.length; i++) {
				temp2[i] = dynArray[i];
			}
			dynArray = temp2;
		} else if (lastElementsIndex < 0) {
			return 0;
		}		
		lastElementsIndex--;
		return temp;
	}
	
	/**
	 * Massiivist indeksi järgi elemendi küsimine.
	 * @param Otsitava elemendi indeks.
	 * @return Leitud element.
	 */
	public int get(int i) {
		return dynArray[i];
	}
	
	/**
	 * Elemendi teisega vahetamine.
	 * @param Asendav element.
	 * @param Indeks, kuhu element pannakse.
	 */
	public void put(int x, int i) {
		try {
			dynArray[i] = x;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Nii ei saa, vähendada prooviti väljaspool massiivi!");
		}
	}
	
	/**
	 * Jada pikkus, arvestades ka vaba ruumi.
	 * @return Pikkus.
	 */
	public int len() {
		return dynArray.length;
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
	public int lastElement() {
		return lastElementsIndex;
	}
	/**
	 * Stringesitus paremaks loetavuseks.
	 * @return Kirjeldus.
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < lastElementsIndex; i++) {
			result += dynArray[i] + " ";
		}
		return result;
	}
	
	/**
	 * Massiivi shallow-copy tegija.
	 * @return Uus massiiv.
	 */
	public DynamicArray clone() {
		DynamicArray uus = new DynamicArray(dynArray.length+1);
		for (int i = 0; i <= lastElementsIndex; i++) {
			uus.add(dynArray[i]);
		}
		return uus;
	}
}

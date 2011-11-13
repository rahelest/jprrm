package knapsack.tyybid;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */


public class PriorityQueue {

	private DynamicArray d;
	
	public PriorityQueue() {
		d = new DynamicArray(1);
		d.add(0);
	}
	
//	Lisab arvu x prioriteetj�rjekorda O(lg n)
	public void enqueue(int x) {
		int location =  d.add(x);
//		System.out.println("loc " + location + " & len " + d.len());
		if (d.len() > 1) { // kui massiivi pikkus on suurem �hest, hakkab programm lapsi otsima
			for (int i = location; i > 0; i /= 2) { //alustades lastest t�idab ts�kkel massiivi tagant ette
				if (d.get(i) < d.get((i) / 2)) {
//					System.out.println("Vahetame " + d.get(i - 1)
//							+ "(" + (i - 1) + ") sellega: " + d.get(((i - 1) / 2)) + " (" + ((i - 1) / 2) + ")");
					int temp = d.get(i);
					d.put(d.get((i) / 2), i);
					d.put(temp, (i) / 2);
				} else {
					break;
				}
			}
		}		
	}
	
//	v�ljastab ja eemaldab minimaalse elemendi
	public int dequeue() {
		int temp = d.get(1);
		d.put(d.rem(),1);
		System.out.println("--------------------------------------------------------\nEemaldame " + temp);
//		System.out.println("Praegu on selline:");
//		toMyString();
		int i = 1;
//		System.out.println("Lastelement: " + d.lastElement());
		
		while (i * 2 <= d.lastElement()) {
			if (i * 2 + 1 > d.lastElement() || d.get(i * 2) < d.get((i * 2) + 1)) { //juhul kui parem laps on puudu v�i on vasak sellest v�iksem
				if (d.get(i) < d.get(i * 2)) break;
				changeParentAndChild(i, i * 2);
//				System.out.println("Vahetan vasakuga, " + i + " ja " + (i * 2));
				i *= 2;
			} else if (d.get(i) > d.get(i * 2 + 1)) { // juhul kui parem laps on vanemast v�iksem
				changeParentAndChild(i, i * 2 + 1);
//				System.out.println("Vahetan paremaga, " + i + " ja " + ((i * 2) + 1));
//				System.out.println("i on praegu " + i);
				i = i * 2 + 1;
			} else break;
//			System.out.println("i on praegu " + i);
		}
		
//		System.out.println("Ja n��d:");
		toMyString();
		return temp;
	}

	/**
	 * See meetod aitab hoida koodi ilusamana ning tegeleb kahe massiivipesa sisu vahetamisega
	 * @param i vanema indeks
	 * @param i2 lapse indeks
	 */
	private void changeParentAndChild(int i, int i2) {
		int temp = d.get(i);
		d.put(d.get(i2), i);
		d.put(temp, i2); 
	}
	
	public boolean isEmpty() {
		int temp = d.lastElement();
		if (temp > 0) return false;
		else return true;
	}
	
	public void toMyString() {
		for (int i = 0; i <= d.lastElement(); i++) {
			System.out.println("Element nr " + i + " v��rtusega " + d.get(i));
		}
	}
}

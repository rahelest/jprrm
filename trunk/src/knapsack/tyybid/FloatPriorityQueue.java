package knapsack.tyybid;


/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class FloatPriorityQueue {

	private FloatDynamicArray d;
	
	public FloatPriorityQueue() {
		setD(new FloatDynamicArray(1));
		getD().add(null);
	}
	
	private FloatPriorityQueue (FloatDynamicArray f) {
		setD(f);
	}
	
//	Lisab arvu x prioriteetj�rjekorda O(lg n)
	public int enqueue(int w, int v) {
		int location =  getD().add(w, v);
//		System.out.println("loc " + location + " & len " + d.len());
		if (getD().lastElement() > 1) { // kui massiivi pikkus on suurem �hest, hakkab programm lapsi otsima
			for (int i = location; i > 1; i /= 2) { //alustades lastest t�idab ts�kkel massiivi tagant ette
				if (getD().get(i).get() > getD().get((i) / 2).get()) {
//					System.out.println("Vahetame " + d.get(i - 1)
//							+ "(" + (i - 1) + ") sellega: " + d.get(((i - 1) / 2)) + " (" + ((i - 1) / 2) + ")");
					FloatNode temp = getD().get(i);
					getD().put(getD().get((i) / 2), i);
					getD().put(temp, (i) / 2);
				} else {
					break;
				}
			}
		}	
		return location;
	}
	
//	v�ljastab ja eemaldab minimaalse elemendi
	public FloatNode dequeue() {
		FloatNode temp = getD().get(1);
		getD().put(getD().rem(),1);
		System.out.println("--------------------------------------------------------\nEemaldame " + temp.get());
//		System.out.println("Praegu on selline:");
//		toMyString();
		int i = 1;
//		System.out.println("Lastelement: " + d.lastElement());
		
		while (i * 2 <= getD().lastElement()) {
			if (i * 2 + 1 > getD().lastElement() || getD().get(i * 2).get() > getD().get((i * 2) + 1).get()) { //juhul kui parem laps on puudu v�i on vasak sellest suurem
				if (getD().get(i).get() > getD().get(i * 2).get()) break; //kui laps on vanemast v�iksem, katkestab
				changeParentAndChild(i, i * 2);
//				System.out.println("Vahetan vasakuga, " + i + " ja " + (i * 2));
				i *= 2;
			} else if (getD().get(i).get() < getD().get(i * 2 + 1).get()) { // juhul kui parem laps on vanemast suurem
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
		FloatNode temp = getD().get(i);
		getD().put(getD().get(i2), i);
		getD().put(temp, i2); 
	}
	
	public boolean isEmpty() {
		int temp = getD().lastElement();
		if (temp > 0) return false;
		else return true;
	}
	
	public void toMyString() {
		for (int i = 1; i <= getD().lastElement(); i++) {
			System.out.println("Element nr " + i + " v��rtusega " + getD().get(i).get());
		}
	}
	
	public FloatPriorityQueue clone() {
		FloatPriorityQueue f = new FloatPriorityQueue(getD().clone());
		return f;
	}

	public FloatNode getFirst () {
		return getD().get(1);
	}

	public FloatDynamicArray getD() {
		return d;
	}

	public void setD(FloatDynamicArray d) {
		this.d = d;
	}
}

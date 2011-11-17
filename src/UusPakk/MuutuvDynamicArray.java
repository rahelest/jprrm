package UusPakk;

import java.lang.reflect.Array;

public class MuutuvDynamicArray<T> {
	
	private T t; // T stands for "Type"
	T[] list;
	int lastElementsIndex = -1;
	
	public MuutuvDynamicArray (T t) {
		this.t = t;
	}
	
	public int add(T x) {
		lastElementsIndex++;
		if (lastElementsIndex >= list.length) {
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) Array.newInstance((Class<T>) t,list.length * 2);
			for (int i = 0; i < list.length; i++) {
				temp[i] = list[i];
			}
			list = temp;
		}
		list[lastElementsIndex] = x;
//		System.out.println("Lisasin väärtuse: " + x + " lastElementsNo: " + lastElementsIndex);
		return lastElementsIndex;
	}
	
	public <T> T rem() {			
		int temp = list[lastElementsIndex];
		list[lastElementsIndex] = 0;
		if (lastElementsIndex <= list.length / 4) {
			int[] temp2 = new int[list.length  / 4];
			for (int i = 0; i < temp2.length; i++) {
				temp2[i] = list[i];
			}
			list = temp2;
		} else if (lastElementsIndex < 0) {
			return 0;
		}		
		lastElementsIndex--;
		return temp;
	}
	
	public int get(int i) {
		return list[i];
	}
	
	public void put(int x, int i) {
		try {
			list[i] = x;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Nii ei saa, vähendada prooviti väljaspool massiivi!");
		}
	}
	
	public int len() {
		return list.length;
	}
	
	public boolean isEmpty() {
		if (lastElementsIndex < 0) return true;
		else return false;
	}

	public int lastElement() {
		return lastElementsIndex;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < lastElementsIndex; i++) {
			result += list[i] + " ";
		}
		return result;
	}
	
	public DynamicArray clone() {
		DynamicArray uus = new DynamicArray(1);
		for (int i = 0; i <= lastElementsIndex; i++) {
			uus.add(list[i]);
		}
		return uus;
	}
}

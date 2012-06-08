package web.control;

import java.util.ArrayList;

public class ProductCatalogTreeEmulator {
	
	@SuppressWarnings("rawtypes")
	public ArrayList[] getCatalogTree() {
		ArrayList<String> names = new ArrayList<>();
		ArrayList<Integer> parents = new ArrayList<Integer>();
		
		names.add("Seadmed");parents.add(0);
		names.add("Keemia");parents.add(0);
		names.add("Ehitusmaterjalid");parents.add(0);
		names.add("Metallit��stuse masinad");parents.add(1);
		names.add("Puut�� masinad");parents.add(1);
		names.add("V�rvid");parents.add(2);
		names.add("Lakid");parents.add(2);
		names.add("P�randakatted");parents.add(3);
		names.add("Treipingid");parents.add(4);
		names.add("Freespingid");parents.add(4);
		
		ArrayList[] lists = {names, parents};
		
		
		/*String[][] tree = {("0", "Seadmed", "0"), ("0", "Keemia", "0"),
						   ("0", "Ehitusmaterjalid", "0"),
						   ("1", "Metallit��stuse masinad", "1"),
						   ("1", "Puut�� masinad", "1")
						   
		};*/
		
		return lists;
	}

}

package web.control;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalogTreeEmulator {
	
	public Map<String, Integer> getCatalogTree() {
		Map<String, Integer> tree = new HashMap<String, Integer>();
		
		tree.put("Seadmed", 0);
		tree.put("Keemia", 0);
		tree.put("Ehitusmaterjalid", 0);
		tree.put("Metallit��stuse masinad", 1);
		tree.put("Puut�� masinad", 1);
		tree.put("V�rvid", 2);
		tree.put("Lakid", 2);
		tree.put("P�randakatted", 3);
		tree.put("Treipingid", 4);
		tree.put("Freespingid", 4);
		
		return tree;
	}

}

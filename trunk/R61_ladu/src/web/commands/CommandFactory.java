package web.commands;

import java.util.Map;

import web.commands.product.ProductCommandFactory;
import web.commands.warehouse.WarehouseCommandFactory;

public class CommandFactory {
	
	/*
	 * Käskude järjekord on oluline!
	 * Sisendiks EventAndUIStatusFinderi leitud
	 * staatuste ja sündmuste massiivi ja teeb
	 * käskude massiivi, mis sisaldab just selliste
	 * staatuste/sündmuste teenindamiseks vajalikke'
	 * käske.Tagastab selle controllerile.
	 */
	public Command[] findCommands(Map<String, String> map) {
		
		/*
		 * Suunab ümber, kas productcommandfactoryle vm?
		 */
		
		Command[] c1 = new ProductCommandFactory().getCommand(map);

		
		return null;
		
	}

}

package web.commands;

import java.util.Map;

import web.commands.product.ProductCommandFactory;
import web.commands.warehouse.WarehouseCommandFactory;

public class CommandFactory {
	
	/*
	 * K�skude j�rjekord on oluline!
	 * Sisendiks EventAndUIStatusFinderi leitud
	 * staatuste ja s�ndmuste massiivi ja teeb
	 * k�skude massiivi, mis sisaldab just selliste
	 * staatuste/s�ndmuste teenindamiseks vajalikke'
	 * k�ske.Tagastab selle controllerile.
	 */
	public Command[] findCommands(Map<String, String> map) {
		
		/*
		 * Suunab �mber, kas productcommandfactoryle vm?
		 */
		
		Command[] c1 = new ProductCommandFactory().getCommand(map);

		
		return null;
		
	}

}

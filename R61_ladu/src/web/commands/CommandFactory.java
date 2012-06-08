package web.commands;

import web.commands.product.ProductCommandFactory;

public class CommandFactory {
	
	/*
	 * K�skude j�rjekord on oluline!
	 * Sisendiks EventAndUIStatusFinderi leitud
	 * staatuste ja s�ndmuste massiivi ja teeb
	 * k�skude massiivi, mis sisaldab just selliste
	 * staatuste/s�ndmuste teenindamiseks vajalikke'
	 * k�ske.Tagastab selle controllerile.
	 */
	public Command[] findCommands(String event) {
		
		/*
		 * Suunab �mber, kas productcommandfactoryle vm?
		 */
		
		new ProductCommandFactory().getCommand(event);

		
		return null;
		
	}

}

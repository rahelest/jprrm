package web.commands;

import web.commands.product.ProductCommandFactory;

public class CommandFactory {
	
	/*
	 * Käskude järjekord on oluline!
	 * Sisendiks EventAndUIStatusFinderi leitud
	 * staatuste ja sündmuste massiivi ja teeb
	 * käskude massiivi, mis sisaldab just selliste
	 * staatuste/sündmuste teenindamiseks vajalikke'
	 * käske.Tagastab selle controllerile.
	 */
	public Command[] findCommands(String event) {
		
		/*
		 * Suunab ümber, kas productcommandfactoryle vm?
		 */
		
		new ProductCommandFactory().getCommand(event);

		
		return null;
		
	}

}

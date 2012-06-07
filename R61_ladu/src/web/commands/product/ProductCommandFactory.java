package web.commands.product;

import java.util.ArrayList;
import java.util.Map;

import web.commands.Command;

public class ProductCommandFactory {

	public Command[] getCommand(Map<String, String> find) {
		
		ArrayList<Command> commands = new ArrayList<Command>();

		 commands.add(new addProductCommand());
		
		return (Command[]) commands.toArray();
	}

}

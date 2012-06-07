package web.commands.product;

import java.util.ArrayList;
import java.util.Map;

import web.commands.Command;

public class ProductCommandFactory {

	public Command[] getCommand(Map<String, String> events) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		
		if (events.containsKey("id")) {
			commands.add(new getProductCommand());
		} else if (events.containsKey("new")) {
			commands.add(new addProductCommand());
		} else if (events.containsKey("search")) {
			commands.add(new searchByTypeCommand());
		} else if (events.containsKey("type")) {
			commands.add(new showByTypeCommand());
		}		 
		
		return (Command[]) commands.toArray();
	}

}

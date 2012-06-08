package web.commands.warehouse;

import java.util.ArrayList;
import java.util.Map;

import web.commands.Command;

public class WarehouseCommandFactory {
	
	public Command[] getCommand(Map<String, String> events) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		
		if (events.containsKey("id")) {
			commands.add(new addToWarehouseCommand());
		} else if (events.containsKey("new")) {
			commands.add(new removeFromWarehouseCommand());
		} else if (events.containsKey("search")) {
			commands.add(new moveBetweenWarehousesCommand());
		}	 
		
		return (Command[]) commands.toArray();
	}

}

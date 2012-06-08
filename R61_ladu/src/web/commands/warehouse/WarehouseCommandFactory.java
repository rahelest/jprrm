package web.commands.warehouse;

import java.util.ArrayList;
import web.commands.Command;

public class WarehouseCommandFactory {
	
	public Command[] getCommand(String event) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		
		if (event.equals("id")) {
			commands.add(new addToWarehouseCommand());
		} else if (event.equals("new")) {
			commands.add(new removeFromWarehouseCommand());
		} else if (event.equals("search")) {
			commands.add(new moveBetweenWarehousesCommand());
		}	 
		
		return (Command[]) commands.toArray();
	}

}

package web.commands.product;

import java.util.ArrayList;
import java.util.Map;

import web.commands.Command;

public class ProductCommandFactory {

	public Command[] getCommand(String event) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		
		if (event.equals("getProductAddForm")) {
			commands.add(new addProductFormCommand());
		} else if (event.equals("addProduct")) {
			commands.add(new addProductCommand());
		} else if (event.equals("getProduct")) {
			commands.add(new getProductCommand());
		} else if (event.equals("changeProduct")) {
			commands.add(new changeProductCommand());
		} else if (event.equals("viewProducts")) {
			commands.add(new showByTypeCommand());
		} else if (event.equals("deleteProduct")) {
			//TODO
		} else if (event.equals("getTypeSearchForm")) {
			commands.add(new searchFormByTypeCommand());
		} else if (event.equals("doTypeSearch")) {
			commands.add(new searchByTypeCommand());
		} else if (event.equals("doTypelessSearch")) {
			commands.add(new searchWithoutTypeCommand());
		}	 
		
		commands.add(new getProductCatalogTree());
		
		Command[] commandArray = new Command[commands.size()];
		
		for (int i = 0; i < commands.size(); i++) {
			commandArray[i] = commands.get(i);
		}
		
		return commandArray;
	}

}

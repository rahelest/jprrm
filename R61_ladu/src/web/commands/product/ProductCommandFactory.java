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
			commands.add(new addProductCommand());
		} else if (event.equals("viewProducts")) {
			
		} else if (event.equals("deleteProduct")) {
			
		} else if (event.equals("getTypeSearchForm")) {
			
		} else if (event.equals("doTypeSearch")) {
			
		} else if (event.equals("doTypelessSearch")) {
			
		}
		
		if (event.equals("id")) {
			commands.add(new getProductCommand());
		} else if (event.equals("new")) {
			commands.add(new addProductCommand());
		} else if (event.equals("search")) {
			commands.add(new searchByTypeCommand());
		} else if (event.equals("type")) {
			commands.add(new showByTypeCommand());
		}		 
		
		return (Command[]) commands.toArray();
	}

}

package shared;

import java.io.Console;

public class Frontend {
	public static void main(String[] args) {
		Console console = System.console();
		
		while(true) {			
			String input = console.readLine("> ");
			System.out.println(input);
		}
	}
}

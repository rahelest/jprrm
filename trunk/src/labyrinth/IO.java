package labyrinth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class IO {
	
	char[] entrance = null;
	char[] exit = null;
	
	public static void main(String[] args) {
		new IO(args);
	}
	
	public IO (String[] args) {
		char[][] maze = readInput(args[0]);
		Maze m = new Maze(entrance, exit);
		maze = m.solve(maze);
		writeOutput(maze);
		
	}
	
	private char[][] readInput(String string) {
		BufferedReader br;
		char [][] maze = null;
		int n = 0;
		try {
			br = new BufferedReader(
						new InputStreamReader(new FileInputStream(
									string + ".in")));
			
			n = Integer.parseInt(br.readLine());
			maze = new char[2 * n + 1][2 * n + 1];
			
			String line = br.readLine();
			int f = 0;
			while (line != null) {
				for (int i = 0; i < maze.length; i++) {
					maze[f][i] = line.charAt(i);
					if (line.charAt(i) == "B") {
						entrance = {f, i};
					} else if (line.charAt(i) == "F") {
						exit = {f, i};
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return maze;
	}

	private void writeOutput(char[][] maze) {
		// TODO Auto-generated method stub
		
	}



}

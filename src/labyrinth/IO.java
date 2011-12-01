package labyrinth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {
	
	int[] entrance = {0,0};
	int[] exit = {0,0};
	
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
						new InputStreamReader(new FileInputStream("in\\" + 
									string + ".in")));
			
			n = Integer.parseInt(br.readLine());
			maze = new char[2 * n + 1][2 * n + 1];
			//rida, veerg
			String line = br.readLine();
			line = br.readLine();
			int f = 0;
			while (line != null) {
				for (int i = 0; i < maze.length; i++) {
					maze[f][i] = line.charAt(i);
					if (line.charAt(i) == 'B') {
						entrance[0] = f;
						entrance[1] = i;
					} else if (line.charAt(i) == 'F') {
						exit[0] = f;
						exit[1] = i;
					}
				}
				f++;
				line = br.readLine();
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

package labyrinth;

import java.util.ArrayList;

public class RahelMaze {
	
	int[] entrance;
	int[] exit;
	//RIDA; VEERG
	static final int[] NORTH = {-1, 0};
	static final int[] EAST = {0, 1};
	static final int[] SOUTH = {1, 0};
	static final int[] WEST = {0, -1};

	public RahelMaze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		return deDeadEndMaze(maze);
	}

	private char charFromMatrix(char[][] maze, int[] current, int[] dir) {
		if ((current[0] > (maze.length - 2)) || (current[1] > (maze.length - 2))) System.out.println("VÄLJAS! " + current[0] + dir[0] + " " + current[1] + dir[1]);
		if ((current[0] + dir[0]) < 0 || (current[1] + dir[1]) < 0) {
			System.out.println("VÄLJAS! " + current[0] + " " + dir[0] + ", " + current[1] + " " + dir[1]);
			prindiLaby(maze);
		}
		return maze[current[0] + dir[0]][current[1] + dir[1]];
	}

	public void prindiLaby(char[][] maze) {
		System.out.print(" ");
		for (int col = 0; col < maze.length; col++) {
			System.out.print(" " + col%10);
		}
		System.out.println();
		for (int row = 0; row < maze.length; row++) {
			System.out.print(row%10 + " ");
			for (int col = 0; col < maze.length; col++) {
				System.out.print(maze[row][col] + " ");
			}
			System.out.println();
		} 
		System.out.println();
	}

	private char[][] deDeadEndMaze(char[][] maze) {
		maze = findDeadEnd(maze);
		maze = findCulDeSacs(maze);
		return maze;
	}

	private char[][] findDeadEnd(char[][] maze) {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				int[] place = {row,col};
				int[] way = isItDeadEnd(place, maze);
				if (way != null) {
					maze = fillDeadEnds(place, maze, way);
				}
			}
		}
		return maze;
	}

	private char[][] fillDeadEnds(int[] place, char[][] maze, int[] way) {
		while (way != null) {
			maze[place[0]][place[1]] = 'X';
			place = moveOneStep(place, way);
			way = isItDeadEnd(place, maze);
		}
		return maze;
	}
	
	private int[] moveOneStep(int[] current, int[] dir) {
		int[] kaugus = {current[0] + dir[0], current[1] + dir[1]};
		return kaugus;
	}

	private int[] isItDeadEnd(int[] coordinate, char[][] maze) {
		int walls = 0;
		int[] freeDirection = null;
		if (is(coordinate, entrance) || is(coordinate, exit) || (maze[coordinate[0]][coordinate[1]] == 'X')) {
			return null;
		}
		if (charFromMatrix(maze, coordinate, NORTH) == 'X') {
			walls++;
		} else {
			freeDirection = NORTH;
		}
		if (charFromMatrix(maze, coordinate, EAST) == 'X') {
			walls++;
		} else {
			freeDirection = EAST;
		}
		if (charFromMatrix(maze, coordinate, SOUTH) == 'X') {
			walls++;
		} else {
			freeDirection = SOUTH;
		}
		if (charFromMatrix(maze, coordinate, WEST) == 'X') {
			walls++;
		} else {
			freeDirection = WEST;
		}
		if (walls == 3) {
			return freeDirection;
		} else {
			return null;
		}
		
	}

	private boolean is(int[] coordinate, int[] otherCoordinate) {
		if (coordinate[0] == otherCoordinate[0] && coordinate[1] == otherCoordinate[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/*
	 * cul de sac on siis, kui algo jõuab samasse ristmikusse varem teisi läbimata
	 */
	
	private char[][] findCulDeSacs(char[][] maze) {
		ArrayList<int[]> junctions = new ArrayList<int[]>();
		int[] place = entrance;
		
		int[] response = detectDirectionOrJunction();
		
//		/*
//		 * MILLAL LÕPETADA? 
//		 * kui ristmikul suunad otsas, eemaldada?
//		 * lõpp, kui kõik otsas?
//		 */
//			int[] response = detectDirectionOrJunction();
//			if (response[2] == 0) {
//				int[] junction = {response[0], response[1]};
//				int junctionIndex = hasJunction(junctions, junction); 
//				if (junctionIndex != -1) {
//					//cul de sac!
//				} else {
//					junctions.add(junction);
//				}
//			} else {
//				int[] newWay = {response[0], response[1]};
//			}
//		
		return maze;
	}

	private int[] detectDirectionOrJunction() {
		/* INDEKSID
		 * 0,1 koordinaadid
		 * 2 ristmiku aste
		 * 3,4 ainus / mingi suund... kuidas hiljem läbi käia neid, mis puudu?
		 * vb siis astme asemel võimalikud suunad - kus tuli, meelde jätta!
		 * => veel vabad suunad, nt 134, 2-st tuli, 3 suunda veel...  
		 */
		return entrance;
		// TODO Auto-generated method stub
		
	}
	
	private int hasJunction(ArrayList<int[]> junctions, int[] junction) {
		for (int i = 0; i < junctions.size(); i++) {
			if (is(junctions.get(i), junction)) {
				return i;
			}
		}
		return -1;
	}
}

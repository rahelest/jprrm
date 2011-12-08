package labyrinth;

public class RahelMaze {
	
	int[] entrance;
	int[] exit;
	int[] threeWay;
	//RIDA; VEERG
	static final int[] NORTH = {-1, 0};
	static final int[] EAST = {0, 1};
	static final int[] SOUTH = {1, 0};
	static final int[] WEST = {0, -1};
	
	char[][] maze;

	public RahelMaze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		this.maze = maze;
		return deDeadEndMaze();
	}

	private char charFromMatrix(int[] current, int[] dir) {
		return maze[current[0] + dir[0]][current[1] + dir[1]];
	}

	public void printOut(char[][] maze2) {
		System.out.print(" ");
		for (int col = 0; col < maze2.length; col++) {
			System.out.print(" " + col%10);
		}
		System.out.println();
		/*
		 * Ma tean, et järgnev kood oleks ilusam, aga sellega ei saa teha ilusama maze mõttes tühikuid iga char-i vahele horisontaalsuunas.
		 */
//		for (char[] row : maze) {							
//			System.out.println(String.valueOf(row) + " ");
//		}														
		for (int row = 0; row < maze2.length; row++) {
			System.out.print(row%10 + " ");
			for (int col = 0; col < maze2.length; col++) {
				System.out.print(maze2[row][col] + " ");
			}
			System.out.println();
		} 
		System.out.println();
	}
	
	private void editMaze(int[] where, char whatTo) {
		maze[where[0]][where[1]] = whatTo;
		
	}

	private char[][] deDeadEndMaze() {
		findDeadEnds();
//		printOut(maze);
		long algus = System.currentTimeMillis();
		findCulDeSacs();
		System.out.println(System.currentTimeMillis() - algus);
//		printOut(maze);
		findDeadEnds();
		return maze;
	}

	private void findDeadEnds() {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				int[] place = {row,col};
				int[] way = isItDeadEnd(place);
				if (way != null) {
					maze = fillDeadEnds(place, maze, way);
				}
			}
		}
	}

	private char[][] fillDeadEnds(int[] place, char[][] maze, int[] direction) {
		while (direction != null) {
			editMaze(place, 'X');
			place = moveOneStep(place, direction);
			direction = isItDeadEnd(place);
		}
		return maze;
	}
	
	/*
	 * cul de sac on siis, kui algo jõuab samasse ristmikusse varem teisi läbimata
	 */
	
	/**
	 * Otsib ristmikuid, kui leiab kolmese, kutsub Cul-De-Sac kontrolli välja.
	 */
	private void findCulDeSacs() {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				int[] currentLocation = {row,col};
				if (countOutboundRoads(currentLocation) == 3) {
					threeWay = currentLocation;
					checkAllDirections(currentLocation);
//					findNextJunctionsAndFill(currentLocation,maze);					
				}
			}
		}
	}
	
	/**
	 * Kontrollib ristmikul iga suuna kohta, kas sinna saab minna ning sel juhul 
	 * kontrollib, ega see koht labürindis pole Cul-De-Sac.
	 * @param currentLocation
	 */
	private void checkAllDirections(int[] currentLocation) {
		if (is(currentLocation, goToNextJunction(moveOneStep(currentLocation, NORTH), NORTH))) {
			editMaze(currentLocation, 'X');
		} else if (is(currentLocation, goToNextJunction(moveOneStep(currentLocation, EAST), EAST))) {
			editMaze(currentLocation, 'X');
		} else if (is(currentLocation, goToNextJunction(moveOneStep(currentLocation, SOUTH), SOUTH))) {
			editMaze(currentLocation, 'X');
		} else if (is(currentLocation, goToNextJunction(moveOneStep(currentLocation, WEST), WEST))) {
			editMaze(currentLocation, 'X');
		} 
		
	}

	/**
	 * Liigub mööda teed antud suunas järgmise ristmikuni.
	 * @param currentLocation
	 * @param direction
	 * @return
	 */
	private int[] goToNextJunction(int[] currentLocation, int[] direction) {
		while (countOutboundRoads(currentLocation) != 3) {
			if (!is(direction, SOUTH) && !northIsWall(currentLocation)) {
				currentLocation = moveOneStep(currentLocation, NORTH);
				direction = NORTH;
			} else if (!is(direction, WEST) && !eastIsWall(currentLocation)) {
				currentLocation = moveOneStep(currentLocation, EAST);
				direction = EAST;
			} else if (!is(direction, NORTH) && !southIsWall(currentLocation)) {
				currentLocation = moveOneStep(currentLocation, SOUTH);
				direction = SOUTH;
			} else if (!is(direction, EAST) && !westIsWall(currentLocation)) {
				currentLocation = moveOneStep(currentLocation, WEST);
				direction = WEST;
			} else break;
		}
		return currentLocation;
	}

	private boolean findNextJunctionsAndFill(int[] currentLocation) {
		if (is(currentLocation,threeWay)) {
			maze[currentLocation[0]][currentLocation[1]] = 'X';
			return true;
		} else if (countOutboundRoads(currentLocation) > 2 ) {
			
		}
		if (!northIsWall(currentLocation)) {
			findNextJunctionsAndFill(moveOneStep(currentLocation, NORTH));
		}
		if (!eastIsWall(currentLocation)) {
			findNextJunctionsAndFill(moveOneStep(currentLocation, EAST));
		}
		if (!southIsWall(currentLocation)) {
			findNextJunctionsAndFill(moveOneStep(currentLocation, SOUTH));
		}
		if (!westIsWall(currentLocation)) {
			findNextJunctionsAndFill(moveOneStep(currentLocation, WEST));
		}
		return true;
	}

	private int[] moveOneStep(int[] current, int[] dir) {
		int[] kaugus = {current[0] + dir[0], current[1] + dir[1]};
		return kaugus;
	}

	/**
	 * Väldime tüübiteisendusi, võrdleme char-e.
	 * @param coordinate
	 * @param maze
	 * @return
	 */
	private int[] isItDeadEnd(int[] coordinate) {
		int walls = 0;
		int[] freeDirection = null;
		if (is(coordinate, entrance) || is(coordinate, exit) || (maze[coordinate[0]][coordinate[1]] == 'X')) {
			return null;
		}
		if (northIsWall(coordinate)) {
			walls++;
		} else {
			freeDirection = NORTH;
		}
		if (eastIsWall(coordinate)) {
			walls++;
		} else {
			freeDirection = EAST;
		}
		if (southIsWall(coordinate)) {
			walls++;
		} else {
			freeDirection = SOUTH;
		}
		if (westIsWall(coordinate)) {
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
	
	private int countOutboundRoads(int[] coordinate) {
		int walls = 0;
		if (northIsWall(coordinate)) {
			walls++;
		}
		if (eastIsWall(coordinate)) {
			walls++;
		}
		if (southIsWall(coordinate)) {
			walls++;
		}
		if (westIsWall(coordinate)) {
			walls++;
		}
		return 4 - walls;
	}

	private boolean is(int[] coordinate, int[] otherCoordinate) {
		if (coordinate[0] == otherCoordinate[0] && coordinate[1] == otherCoordinate[1]) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @param coordinate
	 * @param maze
	 * @return
	 */
	private boolean westIsWall(int[] coordinate) {
		return charFromMatrix(coordinate, WEST) == 'X';
	}

	/**
	 * @param coordinate
	 * @param maze
	 * @return
	 */
	private boolean southIsWall(int[] coordinate) {
		return charFromMatrix(coordinate, SOUTH) == 'X';
	}

	/**
	 * @param coordinate
	 * @param maze
	 * @return
	 */
	private boolean eastIsWall(int[] coordinate) {
		return charFromMatrix(coordinate, EAST) == 'X';
	}

	/**
	 * @param coordinate
	 * @param maze
	 * @return
	 */
	private boolean northIsWall(int[] coordinate) {
		return charFromMatrix(coordinate, NORTH) == 'X';
	}
}

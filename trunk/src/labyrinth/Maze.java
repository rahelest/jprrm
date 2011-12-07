package labyrinth;

public class Maze {
	
	int[] entrance;
	int[] exit;
	static final int[] NORTH = {1, 0};
	static final int[] EAST = {0, 1};
	static final int[] SOUTH = {-1, 0};
	static final int[] WEST = {0, -1};

	public Maze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		
		char[][] resultingMaze = maze.clone();
		int[] currentRoom = entrance.clone();
		
		//vaatab järjest suundi, eelistab neid, mis lõpule lähemal
		
		char[][] deDeadEndedMaze = deDeadEndMaze(maze);
		
		while (!exit.equals(currentRoom)) {
			currentRoom = getClosestNext(resultingMaze, currentRoom);
			resultingMaze[currentRoom[0]][currentRoom[1]] = '*';
		}

		return resultingMaze;
	}



	private int[] getClosestNext(char[][] tempMaze, int[] currentRoom) {
		int[] closestToExit = {tempMaze.length, tempMaze.length};
		int[] closestRoomToExit = {0,0};
		
		//F!
		if (" ".equals(charFromMatrix(tempMaze, currentRoom, NORTH)) && equalOrBest(distanceFromExit(currentRoom, NORTH), closestToExit)) {
			closestRoomToExit = moveOneStep(currentRoom, NORTH);
		}
		if (" ".equals(charFromMatrix(tempMaze, currentRoom, EAST)) && equalOrBest(distanceFromExit(currentRoom, EAST), closestToExit)) {
			closestRoomToExit = moveOneStep(currentRoom, EAST);
		}
		if (" ".equals(charFromMatrix(tempMaze, currentRoom, SOUTH)) && equalOrBest(distanceFromExit(currentRoom, SOUTH), closestToExit)) {
			closestRoomToExit = moveOneStep(currentRoom, SOUTH);
		}
		if (" ".equals(charFromMatrix(tempMaze, currentRoom, WEST)) && equalOrBest(distanceFromExit(currentRoom, WEST), closestToExit)) {
			closestRoomToExit = moveOneStep(currentRoom, WEST);
		}
		if (closestRoomToExit[0] == 0 && closestRoomToExit[1] == 1) {
			//ei leidnud midagi, mine tagasi
		}
		return closestRoomToExit;
	}

	private char charFromMatrix(char[][] maze, int[] current, int[] dir) {
		return maze[current[0] + dir[0]][current[1] + dir[1]];
	}

	private boolean equalOrBest(int[] kaugus, int[] lahim) {
		if (kaugus[0] + kaugus[1] <= lahim[0] + lahim[1]) {
			return true;
		}
		return false;
	}
	
	private int[] moveOneStep(int[] current, int[] dir) {
		int[] kaugus = {current[0] + dir[0], current[1] + dir[1]};
		return kaugus;
	}

	private int[] distanceFromExit(int[] current, int[] dir) {
		current = moveOneStep(current, dir);
		int[] kaugus = {Math.abs(-exit[0] + current[0]), Math.abs(-exit[1] + current[1])};
		return kaugus;
	}
	
	private char[][] deDeadEndMaze(char[][] maze) {
		maze = makeNeighbourNumbers(maze);
		return null;
	}

	private char[][] makeNeighbourNumbers(char[][] maze) {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				
			}
		}
		return null;
	}
}

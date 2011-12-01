package labyrinth;

public class Maze {
	
	int[] entrance;
	int[] exit;
	final int[] N = {1, 0};
	final int[] E = {0, 1};
	final int[] S = {-1, 0};
	final int[] W = {0, -1};

	public Maze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		
		char[][] answer = maze.clone();
		int[] current = entrance.clone();
		
		//vaatab järjest suundi, eelistab neid, mis lõpule lähemal
		
		while (!exit.equals(current)) {
			current = getClosestNext(answer, current);
			answer[current[0]][current[1]] = '*';
		}
		
		return answer;
	}

	private int[] getClosestNext(char[][] answer, int[] current) {
		int[] lahim = {answer.length, answer.length};
		int[] closest = {0,0};

		if (" ".equals(charFromMatrix(answer, current, N)) && equalOrBest(distanceFromExit(current, N), lahim)) {
			closest = moveOneStep(current, N);
		}
		if (" ".equals(charFromMatrix(answer, current, E)) && equalOrBest(distanceFromExit(current, E), lahim)) {
			closest = moveOneStep(current, E);
		}
		if (" ".equals(charFromMatrix(answer, current, S)) && equalOrBest(distanceFromExit(current, S), lahim)) {
			closest = moveOneStep(current, S);
		}
		if (" ".equals(charFromMatrix(answer, current, W)) && equalOrBest(distanceFromExit(current, W), lahim)) {
			closest = moveOneStep(current, W);
		}
		if (closest[0] == 0 && closest[1] == 1) {
			//ei leidnud midagi, mine tagasi
		}
		return closest;
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
}

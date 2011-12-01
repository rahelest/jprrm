package labyrinth;

public class Maze {
	
	int[] entrance;
	int[] exit;

	public Maze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		
		char[][] answer = maze.clone();
		int[] current = entrance.clone();
		
		//vaatab järjest suundi, eelistab neid, mis lõpule lähemal
		
		while (!exit.equals(current)) {
			int[] next = getClosestNext(answer, current);
		}
		
		return answer;
	}

	private int[] getClosestNext(char[][] answer, int[] current) {
		int[] lahim = {answer.length, answer.length};
		int[] closest = {0,0};
		if (!"X".equals(answer[current[0]][current[1] + 1]) && parem(kaugus(current, 0),lahim)) {
			closest[0] = current[0];
			closest[1] = current[1] + 1;
		}
		return closest;
	}

	private boolean parem(int[] kaugus, int[] lahim) {
		if (kaugus[0] + kaugus[1] <= lahim[0] + lahim[1]) {
			return true;
		}
		return false;
	}

	private int[] kaugus(int[] current, int dir) {
		int[] kaugus = {0,0};
		switch (dir) {
		//N
			case 0: kaugus[0] = Math.abs(exit[0] - current[0] - 1);
					kaugus[1] = Math.abs(exit[1] - current[1]); 
					break;
		//E
			case 1:	kaugus[0] = Math.abs(exit[0] - current[0]);
					kaugus[1] = Math.abs(exit[1] - current[1] - 1); 
					break;
		//S
			case 2: kaugus[0] = Math.abs(exit[0] - current[0] + 1);
					kaugus[1] = Math.abs(exit[1] - current[1]); 
					break;
		//W
			case 3: kaugus[0] = Math.abs(exit[0] - current[0]);
					kaugus[1] = Math.abs(exit[1] - current[1] + 1); 
					break;
		}
		return kaugus;
	}
}

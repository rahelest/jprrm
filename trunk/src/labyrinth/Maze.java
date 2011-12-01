package labyrinth;

public class Maze {
	
	char[] entrance;
	char[] exit;

	public Maze(char[] entrance, char[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	public char[][] solve (char[][] maze) {
		
		char[][] answer = maze.clone();
		char[] current = entrance.clone();
		
		//vaatab järjest suundi, eelistab neid, mis lõpule lähemal
		
		while (!exit.equals(current)) {
			char[] next = getClosestNext(answer, current);
		}
		
		return answer;
	}

	private char[] getClosestNext(char[][] answer, char[] current) {
		int[] lahim = {answer.length, answer.length};
		if (!"X".equals(answer[current[0]][current[1] + 1]) && kaugus(current, 0) < lahim) {
			
		}
		return null;
	}

	private int[] kaugus(char[] current, int i) {
		switch {
		case 0:

		}
	}
}

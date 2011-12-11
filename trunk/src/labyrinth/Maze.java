package labyrinth;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Labürindi klass, töötleb ja leiab tee.
 * @author t093563
 * @author t083851
 *
 */
public class Maze {
	
	/**
	 * Labürindi sissepääsu koordinaadid.
	 */
	int[] entrance;
	
	/**
	 * Labürindi väljapääsu koordinaadid.
	 */
	int[] exit;
	
	/**
	 * Parajasti Cul-De-Saci-otsingus uuritav koordinaat.
	 */
	int[] threeWay;
	
	/**
	 * Konstant põhjasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final int[] NORTH = {-1, 0};
	
	/**
	 * Konstant idasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final int[] EAST = {0, 1};
	
	/**
	 * Konstant lõunasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final int[] SOUTH = {1, 0};
	
	/**
	 * Konstant läänesuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final int[] WEST = {0, -1};
	
	/**
	 * Labürindi muutuja, seda muudetaksegi lahenduse leidmiseks.
	 */
	char[][] maze;

	/**
	 * Konstruktor, jätab meelde sisse- ja väljapääsu.
	 * @param entrance Sissepääsu koordinaadid.
	 * @param exit Väljapääsu koordinaadid.
	 */
	public Maze(int[] entrance, int[] exit) {
		this.entrance = entrance;
		this.exit = exit;
	}

	/**
	 * Käsk labürindi lahendamiseks.
	 * @param maze Lahendatav labürint.
	 * @return Lahendus.
	 */
	public char[][] solve (char[][] maze) {
		this.maze = maze;
		return deDeadEndMaze();
	}
	
	/**
	 * Tupikute leidja.
	 * @return Vastusega labürint.
	 */
	public char[][] deDeadEndMaze() {
		/**
		 * Eemalda tupikud.
		 */
		findDeadEnds();
		long algus = System.currentTimeMillis();
		/**
		 * Eemalda Cul-De-Sacid (väike tsükkel tupiku lõpus,
		 * seda tavaline tupiku otsimine ei leia).
		 */
		findCulDeSacs();
		System.out.println(System.currentTimeMillis() - algus);
		/**
		 * Eemalda tupikud, mille Cul-De-Sac üles leiab.
		 */
		findDeadEnds();
		LabiKaija l = new LabiKaija(maze); 
		return maze;
	}
	
	/**
	 * Tupikute otsija. Kontrollib kogu labürindi kõik koordinaadid.
	 */
	public void findDeadEnds() {
		/**
		 * Alustab 1-st ja lõpetab 2n-ga, sest kõige ääres on 
		 * alati sein ning nii ei pea muretsema massiivi 
		 * piiridest väljumise pärast.
		 */
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				int[] place = {row,col};
				int[] way = isItDeadEnd(place);
				if (way != null) {
					fillDeadEnds(place, way);
				}
			}
		}
	}
	
	/**
	 * Iga koordinaadi kohta kontrollib kõiki kõrvalseinu, 
	 * kui on kolm neist seinad, tagastab ainsa vaba suuna.
	 * @param coordinate Uuritav koordinaat.
	 * @return Vastavalt tulemusele null või ainus vaba suund.
	 */
	public int[] isItDeadEnd(int[] coordinate) {
		int walls = 0;
		int[] freeDirection = null;
		if (is(coordinate, entrance) || is(coordinate, exit) || (maze[coordinate[0]][coordinate[1]] == 'X')) {
			return null;
		}
		if (isThisDirectionWall(coordinate, NORTH)) {
			walls++;
		} else {
			freeDirection = NORTH;
		}
		if (isThisDirectionWall(coordinate, EAST)) {
			walls++;
		} else {
			freeDirection = EAST;
		}
		if (isThisDirectionWall(coordinate, SOUTH)) {
			walls++;
		} else {
			freeDirection = SOUTH;
		}
		if (isThisDirectionWall(coordinate, WEST)) {
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
	
	/**
	 * Kontrollib int[] võrduvust. Kui mõlema massiivi vastava indeksi
	 * kohad võrduvad, siis võrduvad ka need massiivid.
	 * @param coordinate Üks massiiv.
	 * @param otherCoordinate Teine massiiv.
	 * @return Võrdlemise tulemus.
	 */
	public boolean is(int[] coordinate, int[] otherCoordinate) {
		for (int i = 0; i < coordinate.length; i++) {
			if (coordinate[i] != otherCoordinate[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Kirjutab tupikus ruumi kohale X-i, et see võimalikust lahendusest eemaldada.
	 * Seejärel kontrollib sellele ruumile järgmist ruumi, ega see tupik pole.
	 * Tsükkel töötab, kuni jõuab ristmikuni.
	 * @param currentLocation Muudetav ruum.
	 * @param direction Ainus suund muudetavast ruumist välja.
	 */
	public void fillDeadEnds(int[] currentLocation, int[] direction) {
		while (direction != null) {
			editMaze(currentLocation, 'X');
			currentLocation = moveOneStep(currentLocation, direction);
			direction = isItDeadEnd(currentLocation);
		}
	}
	
	/**
	 * Labürindi muutmise meetod.
	 * @param where Koordinaat, mida muudetakse.
	 * @param whatTo Mis on muudetava koha uueks väärtuseks.
	 */
	public void editMaze(int[] where, char whatTo) {
		maze[where[0]][where[1]] = whatTo;	
	}
	
	/**
	 * Labürindis ühe sammi võrra edasi liikumine.
	 * @param currentPlace Kus kohast liigutakse.
	 * @param direction Mis suunas liikuda.
	 * @return Uue koha koordinaadid.
	 */
	public int[] moveOneStep(int[] currentPlace, int[] direction) {
		int[] uusKoordinaat = {currentPlace[0] + direction[0], currentPlace[1] + direction[1]};
		return uusKoordinaat;
	}
	
	public char charFromMatrix(int[] current, int[] dir) {
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
	

	/**
	 * Otsib ristmikuid, kui leiab kolmese, kutsub Cul-De-Sac kontrolli välja.
	 */
	public void findCulDeSacs() {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				int[] currentLocation = {row,col};
				if (countOutboundRoads(currentLocation) == 3) {
					threeWay = currentLocation;
					checkAllDirections(currentLocation);				
				}
			}
		}
	}
	
	/**
	 * Loeb kokku, mitu kõrvalteed on antud koordinaadil.
	 * @param coordinate Uuritav koordinaad.
	 * @return Kõrvalteede arv.
	 */
	public int countOutboundRoads(int[] coordinate) {
		int walls = 0;
		if (isThisDirectionWall(coordinate, NORTH)) {
			walls++;
		}
		if (isThisDirectionWall(coordinate, EAST)) {
			walls++;
		}
		if (isThisDirectionWall(coordinate, SOUTH)) {
			walls++;
		}
		if (isThisDirectionWall(coordinate, WEST)) {
			walls++;
		}
		return 4 - walls;
	}
	
	
	/**
	 * Kontrollib ristmikul iga suuna kohta, kas sinna saab minna ning sel juhul 
	 * kontrollib, ega see koht labürindis pole Cul-De-Sac (jõuab sama ristmikuni välja).
	 * @param currentLocation Uuritav koordinaat.
	 */
	public void checkAllDirections(int[] currentLocation) {
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
	public int[] goToNextJunction(int[] currentLocation, int[] direction) {
		while (countOutboundRoads(currentLocation) != 3) {
			if (!is(direction, SOUTH) && !isThisDirectionWall(currentLocation, NORTH)) {
				currentLocation = moveOneStep(currentLocation, NORTH);
				direction = NORTH;
			} else if (!is(direction, WEST) && !isThisDirectionWall(currentLocation, EAST)) {
				currentLocation = moveOneStep(currentLocation, EAST);
				direction = EAST;
			} else if (!is(direction, NORTH) && !isThisDirectionWall(currentLocation, SOUTH)) {
				currentLocation = moveOneStep(currentLocation, SOUTH);
				direction = SOUTH;
			} else if (!is(direction, EAST) && !isThisDirectionWall(currentLocation, WEST)) {
				currentLocation = moveOneStep(currentLocation, WEST);
				direction = WEST;
			} else break;
		}
		return currentLocation;
	}
	
	/**
	 * Kontrollib, kas antud ruumi kindlas suunas asuv naaberruum on sein
	 * või mitte.
	 * @param coordinate Uuritav suund.
	 * @param direction Soovitud suund.
	 * @return Kas on sein?
	 */
	public boolean isThisDirectionWall(int[] coordinate, int[] direction) {
		return charFromMatrix(coordinate, direction) == 'X';
	}
	
	/**
	 * Dijkstra algoritmi jaoks vajalik Node objekt, ristmik labürindis
	 * @author t083851
	 *
	 */
	class Node {
		private Dimension location;		
		private ArrayList<Edge> edges;
		
		public Node(int[] coordinates) {
			location = new Dimension(coordinates[0],coordinates[1]);
			edges = new ArrayList<Edge>();
		}
		
		public Dimension getLocation() {
			return location;			
		}
		
		public void addEdge(Edge edge) {
			
		}
	}
	
	/**
	 * Dijkstra algoritmi jaoks vajalik Edge objekt, tee kahe ristmiku vahel
	 * @author Administraator
	 *
	 */
	class Edge {
		Node start;
		Node end;
		ArrayList<Dimension> path;
		
	}
}

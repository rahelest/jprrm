package labyrinth;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Labürindi klass, töötleb ja leiab tee.
 * @author t093563
 * @author t083851
 *
 */
public class Maze {
	
	/**
	 * Labürindi sissepääsu koordinaadid. -legacy (failist lugemine)
	 */
	Dimension entrance;
	
	/**
	 * Asukoht, mis on põhimõtteliselt ruuduke labürindis, sissepääs
	 */
	Location entranceLoc;
	
	/**
	 * Labürindi väljapääsu koordinaadid. -legacy (failist lugemine)
	 */
	Dimension exit;
	
	/**
	 * Asukoht, mis on põhimõtteliselt ruuduke labürindis, väljapääs
	 */
	Location exitLoc;
	
	/**
	 * Parajasti Cul-De-Saci-otsingus uuritav koordinaat.
	 */
	Dimension threeWay;
	
	/**
	 * Konstant põhjasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final Dimension NORTH = new Dimension(0, -1);
	
	/**
	 * Konstant idasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final Dimension EAST = new Dimension(1, 0);
	
	/**
	 * Konstant lõunasuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final Dimension SOUTH = new Dimension(0, 1);
	
	/**
	 * Konstant läänesuuna koordinaadimuutuse lihtsustamiseks.
	 */
	static final Dimension WEST = new Dimension(-1, 0);
	
	static List<Dimension> suunad = new ArrayList<Dimension>();
	
	/**
	 * Labürindi muutuja, seda muudetaksegi lahenduse leidmiseks.
	 */
	char[][] maze;
	
	/**
	 * Muutmata labürint hilisemaks rajamärkimiseks ja tagastamiseks;
	 */
	char[][] virginMaze;
	
	/**
	 * Asupaikade nimekiri, mida on vaja veel uurida.
	 */
	PriorityQueue<Location> veelUurida = new PriorityQueue<Location>(11, new PQComparator());
	
	/**
	 * Asupaikade nimekiri, mille on algoritm juba läbi käinud.
	 */
	ArrayList<Location> labiUuritud = new ArrayList<Location>();
	
	public Maze() {
		suunad.add(NORTH);
		suunad.add(EAST);
		suunad.add(SOUTH);
		suunad.add(WEST);
	}

	/**
	 * Konstruktor, jätab meelde sisse- ja väljapääsu.
	 * @param entrance Sissepääsu koordinaadid.
	 * @param exit Väljapääsu koordinaadid.
	 */
	public Maze(Dimension entrance, Dimension exit) {
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
		virginMaze = maze.clone();
		findBeginningAndEnd();		
		deDeadEndMaze();
		findShortestPath();
		return virginMaze;
	}

	/**
	 * See on abiklass, mis aitab läbida labürinti
	 */
	class Location {
		/**
		 * Asukoht x-y-teljestikus
		 */
		Dimension coordinates;
		/**
		 * Selle asukoha absoluutne kaugus väljapääsust
		 */
		int localCost;
		/**
		 * Samme stardist 
		 */
		int parentCost;
		/**
		 * Kogumaksumus (local + parent)
		 */
		int passThroughCost;
		/**
		 * 
		 */
		Location cameFrom;
		
		private Location(Dimension d, Location parent) {
			coordinates = new Dimension(d);
			cameFrom = parent;
		}

		public void markTheSpot() {
			virginMaze[coordinates.height][coordinates.width] = '*';
			if (cameFrom != null) {
				cameFrom.markTheSpot();
			}
			
		}

		public Set<Location> findPossibleDirections() {
			Set<Location> voimalikudSuunad = new HashSet<Location>();
			
			for (Dimension d : suunad) {
				if ((cameFrom == null || !cameFrom.coordinates.equals(coordinates))
						&& !isThisDirectionWall(this.coordinates, d)) {
					voimalikudSuunad.add(new Location(moveOneStep(coordinates, d), this));
				}
			}
			
						
			return voimalikudSuunad;
		}
	}
	
	/**
	 * Käime läbi kogu sisendi ning otsime üles sissepääsu ja väljapääsu koordinaadid
	 * @param maze
	 */
	private void findBeginningAndEnd () {
		for (int row = 0; row < maze.length; row++) {
			for (int column = 0; column < maze.length; column++) {
				if (maze[row][column] == 'B') {
					entrance = new Dimension(column,row);
					entranceLoc = new Location(entrance, null);
				} else if (maze[row][column] == 'F') {
					exit = new Dimension(column,row);
				}
			}
		}		
	}		
		
	/**
	 * Tupikute leidja.
	 * @return Vastusega labürint.
	 */
	private void deDeadEndMaze() {
		/**
		 * Eemalda tupikud.
		 */
		findDeadEnds();
		/**
		 * Eemalda Cul-De-Sacid (väike tsükkel tupiku lõpus,
		 * seda tavaline tupiku otsimine ei leia).
		 */
		findCulDeSacs();
		/**
		 * Eemalda tupikud, mille Cul-De-Sac üles leiab.
		 */
		findDeadEnds();
	}
	
	
	/**
	 * A-Staril põhinev lähima läbimiseks sobiliku raja leidja
	 */
	private void findShortestPath() {
		// TODO Auto-generated method stub
		veelUurida.add(entranceLoc);
		while (!veelUurida.isEmpty()) {
			Location uuritav = veelUurida.poll();
			labiUuritud.add(uuritav);
			if (uuritav.coordinates.equals(exit)) {
				uuritav.markTheSpot();
				break;
			} else {
				Set<Location> naabrid = uuritav.findPossibleDirections();
			}
		}
		
	}
	
	/**
	 * Tupikute otsija. Kontrollib kogu labürindi kõik koordinaadid.
	 */
	private void findDeadEnds() {
		/**
		 * Alustab 1-st ja lõpetab 2n-ga, sest kõige ääres on 
		 * alati sein ning nii ei pea muretsema massiivi 
		 * piiridest väljumise pärast.
		 */
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				Dimension place = new Dimension (row,col);
				Dimension way = isItDeadEnd(place);
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
	private Dimension isItDeadEnd(Dimension coordinate) {
		int walls = 0;
		Dimension freeDirection = null;
		if (coordinate.equals(entrance) || coordinate.equals(exit) || (maze[coordinate.width][coordinate.height] == 'X')) {
			return null;
		}
		
		for (Dimension d : suunad) {
			if (isThisDirectionWall(coordinate, d)) {
				walls++;
			} else {
				freeDirection = d;
			}
		}
		
		if (walls == 3) {
			return freeDirection;
		} else {
			return null;
		}		
	}

	/**
	 * Kirjutab tupikus ruumi kohale X-i, et see võimalikust lahendusest eemaldada.
	 * Seejärel kontrollib sellele ruumile järgmist ruumi, ega see tupik pole.
	 * Tsükkel töötab, kuni jõuab ristmikuni.
	 * @param currentLocation Muudetav ruum.
	 * @param direction Ainus suund muudetavast ruumist välja.
	 */
	private void fillDeadEnds(Dimension currentLocation, Dimension direction) {
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
	private void editMaze(Dimension where, char whatTo) {
		maze[where.width][where.height] = whatTo;	
	}
	
	/**
	 * Labürindis ühe sammi võrra edasi liikumine.
	 * @param currentPlace Kus kohast liigutakse.
	 * @param direction Mis suunas liikuda.
	 * @return Uue koha koordinaadid.
	 */
	Dimension moveOneStep(Dimension currentPlace, Dimension direction) {
		Dimension uusKoordinaat = new Dimension (currentPlace.width + direction.width,
				currentPlace.height+ direction.height);
		return uusKoordinaat;
	}
	
	private char charFromMatrix(Dimension current, Dimension dir) {
		return maze[current.width + dir.width][current.height + dir.height];
	}

	void printOut(char[][] maze2) {														
		for (int row = 0; row < maze2.length; row++) {
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
	private void findCulDeSacs() {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze.length - 1; col++) {
				Dimension currentLocation = new Dimension(row,col);
				if (countOutboundRoads(currentLocation) == 3) {
					threeWay = currentLocation;
					checkAllDirectionsAndBlock(currentLocation);				
				}
			}
		}
	}
	
	/**
	 * Loeb kokku, mitu kõrvalteed on antud koordinaadil.
	 * @param coordinate Uuritav koordinaad.
	 * @return Kõrvalteede arv.
	 */
	private int countOutboundRoads(Dimension coordinate) {
		int walls = 0;
		
		for (Dimension d : suunad) {
			if (isThisDirectionWall(coordinate, d)) {
				walls++;
			}
		}
		return 4 - walls;
	}
	
	
	/**
	 * Kontrollib ristmikul iga suuna kohta, kas sinna saab minna ning sel juhul 
	 * kontrollib, ega see koht labürindis pole Cul-De-Sac (jõuab sama ristmikuni välja).
	 * @param currentLocation Uuritav koordinaat.
	 */
	private void checkAllDirectionsAndBlock(Dimension currentLocation) {
		
		for (Dimension d : suunad) {
			if (currentLocation.equals(goToNextJunction(moveOneStep(currentLocation, d), d))) {
				editMaze(currentLocation, 'X');
				break;
			}
		}
	}

	/**
	 * Liigub mööda teed antud suunas järgmise ristmikuni.
	 * @param currentLocation
	 * @param direction
	 * @return
	 */
	private Dimension goToNextJunction(Dimension currentLocation, Dimension direction) {
		while (countOutboundRoads(currentLocation) != 3) {
			if (!direction.equals(SOUTH) && !isThisDirectionWall(currentLocation, NORTH)) {
				currentLocation = moveOneStep(currentLocation, NORTH);
				direction = NORTH;
			} else if (!direction.equals(WEST) && !isThisDirectionWall(currentLocation, EAST)) {
				currentLocation = moveOneStep(currentLocation, EAST);
				direction = EAST;
			} else if (!direction.equals(NORTH) && !isThisDirectionWall(currentLocation, SOUTH)) {
				currentLocation = moveOneStep(currentLocation, SOUTH);
				direction = SOUTH;
			} else if (!direction.equals(EAST) && !isThisDirectionWall(currentLocation, WEST)) {
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
	boolean isThisDirectionWall(Dimension coordinate, Dimension direction) {
		return charFromMatrix(coordinate, direction) == 'X';
	}
	
	
	class PQComparator implements Comparator<Location> {

		@Override
		public int compare(Location l1, Location l2) {
			if (l1.passThroughCost < l2.passThroughCost) {
				return -1;
			} else if (l1.passThroughCost == l2.passThroughCost) {
				return 0;
			} else {
				return 1;
			}
		}
		
	}
}

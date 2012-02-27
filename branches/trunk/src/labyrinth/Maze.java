package labyrinth;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Labürindi klass, töötleb ja leiab tee.
 * Töötlemine on küll pikemapoolne, aga peaks toimima 
 * ka näidissisenditest küllaltki palju suuremate sisendite puhul ja 
 * seejuures järjest kasulikum olema.
 * (dead endid ja cul-de-sac-d oleks hiljem veelgi kulukamad)
 */
public class Maze {
	
	/**
	 * Labürindi sissepääsu koordinaadid. -legacy (failist lugemine)
	 */
	static Dimension entrance;
	
	/**
	 * Asukoht, mis on põhimõtteliselt ruuduke labürindis, sissepääs
	 */
	Location entranceLoc;
	
	/**
	 * Labürindi väljapääsu koordinaadid. -legacy (failist lugemine)
	 */
	static Dimension exit;
	
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
	List <Dimension> tee = new ArrayList<Dimension>();
	
	/**
	 * Asupaikade nimekiri, mille on algoritm juba läbi käinud.
	 */
	ArrayList<Location> labiUuritud = new ArrayList<Location>();
	
	public Maze() {}

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
		long algus = System.currentTimeMillis();
		suunad.add(NORTH);
		suunad.add(EAST);
		suunad.add(SOUTH);
		suunad.add(WEST);
		this.maze = maze;
		virginMaze = cloneMaze(maze);
		findBeginningAndEnd();	
		deDeadEndMaze();
		findShortestPath();
		System.out.println("Kulunud aeg: " + (System.currentTimeMillis() - algus));
		return virginMaze;
	}
	
	/**
	 * haaaaaaaax!
	 */
	public void hoiaParemale() {
		Dimension koht = goToNextJunction2(entrance, kuhuMinna(entrance));
		taidaVirgin();
	}

	private void taidaVirgin() {
		for(int i = 0; i < tee.size(); i++) {
			Dimension koht = tee.get(i);
			if (virginMaze[koht.width][koht.height] == ' ') {
				virginMaze[koht.width][koht.height] = '*';
			}
			
		}
	}

	private Dimension kuhuMinna(Dimension koht) {
		for (Dimension d : suunad) {
			if (charFromMatrix(koht, d) == ' ') {
				return d;
			}
		}
		return null;
	}
	
	private Dimension goToNextJunction2(Dimension currentLocation, Dimension direction) {
		while (countOutboundRoads(currentLocation) != 3) {
			tee.add(currentLocation);
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
	 * Kloonime ühe neitsi, mida on pärast hea tärnida
	 * @param maze2 labürint, mille klooni loodame tulemuseks saada
	 * @return kloonitud labürint
	 */
	private char[][] cloneMaze(char[][] maze2) {
		char[][] temp = new char[maze2.length][maze2.length];
		for (int row = 0; row < maze.length; row++) {
			for (int column = 0; column < maze.length; column++) {
				temp[row][column] = maze2[row][column];
			}
		}
		return temp;
	}

	
	
	/**
	 * Käime läbi kogu sisendi ning otsime üles sissepääsu ja väljapääsu koordinaadid
	 * @param maze
	 */
	private void findBeginningAndEnd () {
		for (int row = 0; row < maze.length; row++) {
			for (int column = 0; column < maze.length; column++) {
				if (maze[row][column] == 'B') {
					entrance = new Dimension(row,column);
					entranceLoc = new Location(entrance);
				} else if (maze[row][column] == 'F') {
					exit = new Dimension(row,column);
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
//		System.out.println(exit);
		List<Location> prioriteetsed = new ArrayList<Location>();
		prioriteetsed.add(entranceLoc);
		while (!prioriteetsed.isEmpty()) {
			/**
			 * ebaoptimaalne häkk, kuna ei jõua PQ-d implementeerida
			 */
			double minPT = 0.0;
			Location uuritav = null;
			for (Location loc : prioriteetsed) {
					minPT = loc.getPassThrough();
					if (loc.getPassThrough() >= minPT) {
					uuritav = loc;
				}
			}
			prioriteetsed.remove(uuritav);
			labiUuritud.add(uuritav);
//			System.out.println(uuritav);
			if (uuritav.coordinates.equals(exit)) {
				uuritav.markTheSpot();
				break;
			} else {
				Set<Location> naabrid = uuritav.findPossibleDirections();
//				System.out.println(prioriteetsed.size());
				for (Location naaber : naabrid) {
					if (prioriteetsed.contains(naaber)) {
						
						System.out.println(naaber);
						Location temp = new Location (naaber.coordinates);
						temp.setParent(uuritav);
						if (temp.getPassThrough() >= naaber.getPassThrough()) {
							continue;
						}
					}
					
					if (labiUuritud.contains(naaber)) {
						Location temp = new Location (naaber.coordinates);
						if (temp.getPassThrough() >= naaber.getPassThrough()) {
							continue;
						}
					}
					naaber.setParent(uuritav);
					veelUurida.remove(naaber);
					labiUuritud.remove(naaber);
					prioriteetsed.remove(uuritav);
					prioriteetsed.add(0,naaber);
				}
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
		if (maze[currentLocation.height][currentLocation.width] == 'X') {
			return currentLocation;
		}
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
		int localCost = 0;
		/**
		 * Samme stardist 
		 */
		double parentCost = 0.0;
		/**
		 * Kogumaksumus (local + parent)
		 */
		double passThroughCost = 0.0;
		/**
		 * 
		 */
		Location cameFrom;
		
		public Location(Dimension d) {
//			System.out.println(this);
			coordinates = new Dimension(d);
//			System.out.println(coordinates);
		}

		/**
		 * Tähistab võimaliku käigukoha tärniga
		 */
		public void markTheSpot() {
//			System.out.println("TÄRNITAN");
			if (virginMaze[coordinates.width][coordinates.height] == ' ')
			virginMaze[coordinates.width][coordinates.height] = '*';
			if (cameFrom != null) {
				cameFrom.markTheSpot();
			}
			
		}

		/**
		 * Leiab võimalikud suunad, kuhu liikuda, välistab tuldud tee
		 * @return võimalikud edasi liikumise suunad
		 */
		public Set<Location> findPossibleDirections() {			
			boolean algus = false;
			Set<Location> voimalikudSuunad = new HashSet<Location>();
			if (cameFrom == null) {
				algus = true;
			}
			for (Dimension d : suunad) {
				if (!isThisDirectionWall(this.coordinates, d) && (algus || !cameFrom.coordinates.equals(moveOneStep(this.coordinates, d)))) {
					voimalikudSuunad.add(new Location(moveOneStep(this.coordinates, d)));
				}
				
			}
			return voimalikudSuunad;
		}
		
		public String toString() {
//			if (cameFrom != null) {
				return coordinates + " ";
//			} else {
//				return null;
//			}
		}

		private double getPassThrough() {
			if (this.coordinates.equals(entrance)) {
				return 0;
			} else {
				return (double) getLocalCost() + getParentCost();
			}
		}

		private double getParentCost() {
			if (this.coordinates.equals(entrance)) {
				return 0;
			}
			if (parentCost == 0) {
				parentCost = 1.0 + 0.5 * (cameFrom.parentCost - 1.0);
			}
				return parentCost;
		}

		private int getLocalCost() {
			if (this.coordinates.equals(entrance)) {
				return 0;
			} else {
				localCost = Math.abs(exit.height - coordinates.height) + Math.abs(exit.width - coordinates.width);
				return localCost;
			}
		}
		
		private void setParent(Location parent) {
			this.cameFrom = parent;
		}
		
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
	
	/*
	 * FAILI LUGEMISE MEETODID
	 */
	
	/**
	 * Faili nimi, millest hakatakse labürinti lugema
	 * ja kuhu tulemus kirjutatakse.
	 */
	static String filename;
	
	/**
	 * Main meetod.
	 * @param args Faili nimi.
	 */
	public static void main(String[] args) {
		filename = args[0];
		char[][] maze = readInput(filename);
		Maze m = new Maze(entrance, exit);
//		m.printOut(maze);
		maze = m.solve(maze);
		m.printOut(maze);
		writeOutput(maze);	
	}
	
	/**
	 * Faili info sisselugeja.
	 * @param string Faili nimi, kust loetakse.
	 * @return Labürint, mida töötlema hakatakse.
	 */
	private static char[][] readInput(String string) {
		BufferedReader br;
		char [][] maze = null;
		int n = 0;
		try {
			br = new BufferedReader(
						new InputStreamReader(new FileInputStream("in\\" + 
									string + ".in")));
			
			/**
			 * Esimene rida näitab ära labürindi suuruse,
			 * see loetakse sisse eraldi. Vastavalt sellele
			 * luuakse ka 2n+1 dimensioonidega char maatriks.
			 */
			n = Integer.parseInt(br.readLine());
			maze = new char[2 * n + 1][2 * n + 1];

			String line = br.readLine();
			int f = 0;
			while (line != null) {
				for (int i = 0; i < maze.length; i++) {
					maze[f][i] = line.charAt(i);

					/**
					 * Kontrollib, kas loetud täht on B või F,
					 * sel juhul kirjutatakse koordinaadid 
					 * vastavalt entrance või exit muutujasse.
					 */
					if (line.charAt(i) == 'B') {
						entrance = new Dimension(f, i);
					} else if (line.charAt(i) == 'F') {
						exit = new Dimension(f, i);
					}
				}

				f++;
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud! Kontrolli sisendparameetrit.");
			e.printStackTrace();
			System.exit(-1);
		} catch (NumberFormatException e) {
			System.out.println("Esimene rida failis ei ole number!");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return maze;
	}

	/**
	 * Kirjutab labürindi muutuja faili.
	 * @param maze Uus labürint.
	 */
	private static void writeOutput(char[][] maze) {
		FileWriter outputStream;
		BufferedWriter out;
		try {
			outputStream = new FileWriter("out\\" +
					filename + ".out");
			out = new BufferedWriter(outputStream);
			for (char[] row : maze) {
				out.write(row);
				out.write("\n");
			}			
			out.close();
		} catch (IOException e) {
			System.out.println("Programm läks katki, kahju küll.");
			e.printStackTrace();
		}		  
		
	}
}

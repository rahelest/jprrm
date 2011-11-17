package UusPakk;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HargneJaKarbi arvuta;
		String testFail1 = "E:/" + "15.in";
		String testFail2 = "E:/" + "20.in";
		String testFail3 = "E:/" + "25.in";
		
		System.out.println("\nHARGNE JA KÄRBI VS. SÜGAVUTI");
		System.out.println("FIGHT!");
		
		long algus1 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail1);
		long lopp1 = System.currentTimeMillis();
		
		long algus2 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail1);
		long lopp2 = System.currentTimeMillis();
		
		long algus3 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail2);
		long lopp3 = System.currentTimeMillis();
		
		long algus4 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail2);
		long lopp4 = System.currentTimeMillis();
		
		long algus5 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail3);
		long lopp5 = System.currentTimeMillis();
		
		long algus6 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail3);
		long lopp6 = System.currentTimeMillis();
		
		System.out.println("\nKÄRPIMISEGA VS. KÄRPIMISETA");
		System.out.println("FIGHT!");
		
		long algus7 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(false, true, testFail3);
		long lopp7 = System.currentTimeMillis();
		
		
		System.out.println("Kärpimisega 1: " + testFail1 + " >>> " + (lopp1-algus1));
		System.out.println("Sügavutiotsing 1: " + testFail1 + " >>> " + (lopp2-algus2));
		System.out.println("Kärpimisega 2: " + testFail2 + " >>> " + (lopp3-algus3));
		System.out.println("Sügavutiotsing 2: " + testFail2 + " >>> " + (lopp4-algus4));
		System.out.println("Kärpimisega 3: " + testFail3 + " >>> " + (lopp5-algus5));
		System.out.println("Sügavutiotsing 3: " + testFail3 + " >>> " + (lopp6-algus6));
		
		System.out.println("Kärpimisega: " + testFail3 + " >>> " + (lopp5-algus5));
		System.out.println("Kärpimiseta: " + testFail3 + " >>> " + (lopp7-algus7));
		
	}

}

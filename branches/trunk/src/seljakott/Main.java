package seljakott;

/**
 * @author t083851 Jaanus Piip
 * @author t093563 Rahel Rjadnev-Meristo
 *
 */

public class Main {

	/**
	 * Staatiline main.
	 * @param args Puuduvad sisendparameetrid.
	 */
	public static void main(String[] args) {
		HargneJaKarbi arvuta;
		String draiv = "H:/";
		
		String testFail1 = draiv + "100.in";
		String testFail2 = draiv + "50.in";
		String testFail3 = draiv + "70.in";
		String testFail4 = draiv + "20.in";
		
		System.out.println("\nHARGNE JA KÄRBI VS. SÜGAVUTI");
		System.out.println("FIGHT!\n");
		
		long algus1 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail1);
		long lopp1 = System.currentTimeMillis();
		System.out.println("Kärpimisega 1: " + testFail1 + " >>> " + (lopp1-algus1) + " ms");
		
		long algus2 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail1);
		long lopp2 = System.currentTimeMillis();
		System.out.println("Sügavutiotsing 1: " + testFail1 + " >>> " + (lopp2-algus2) + " ms\n");	
		
		long algus3 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail2);
		long lopp3 = System.currentTimeMillis();
		System.out.println("Kärpimisega 2: " + testFail2 + " >>> " + (lopp3-algus3) + " ms");
		
		long algus4 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail2);
		long lopp4 = System.currentTimeMillis();
		System.out.println("Sügavutiotsing 2: " + testFail2 + " >>> " + (lopp4-algus4) + " ms\n");
		
		long algus5 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail3);
		long lopp5 = System.currentTimeMillis();
		System.out.println("Kärpimisega 3: " + testFail3 + " >>> " + (lopp5-algus5) + " ms");
		
		long algus6 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, false, testFail3);
		long lopp6 = System.currentTimeMillis();
		System.out.println("Sügavutiotsing 3: " + testFail3 + " >>> " + (lopp6-algus6) + " ms\n");
		
		System.out.println("\nKÄRPIMISEGA VS. KÄRPIMISETA");
		System.out.println("FIGHT!\n");
		
		long algus7 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(true, true, testFail4);
		long lopp7 = System.currentTimeMillis();
		System.out.println("Kärpimisega: " + testFail4 + " >>> " + (lopp7-algus7) + " ms");
		
		long algus8 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(false, true, testFail4);
		long lopp8 = System.currentTimeMillis();
		System.out.println("Kärpimiseta: " + testFail4 + " >>> " + (lopp8-algus8) + " ms\n");
		
		System.out.println("MORTAL KOMBAT!");
	}

}

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
//		long algus1 = System.currentTimeMillis();
//		HargneJaKarbi arvuta = new HargneJaKarbi();
//		arvuta.knapsack(true, true);
//		long lopp1 = System.currentTimeMillis();
		
//		long algus3 = System.currentTimeMillis();
//		arvuta = new HargneJaKarbi();
//		arvuta.knapsack(true, false);
//		long lopp3 = System.currentTimeMillis();
		
		long algus2 = System.currentTimeMillis();
		arvuta = new HargneJaKarbi();
		arvuta.knapsack(false, true);
		long lopp2 = System.currentTimeMillis();
		
//		System.out.println(lopp1-algus1);
//		System.out.println(lopp3-algus3);
		System.out.println(lopp2-algus2);
		
	}

}

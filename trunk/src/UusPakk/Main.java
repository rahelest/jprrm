package UusPakk;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long algus1 = System.currentTimeMillis();
//		JaanuseHargneJaKarbi.arvuta(true, true);
		long lopp1 = System.currentTimeMillis();
		
		long algus2 = System.currentTimeMillis();
		JaanuseHargneJaKarbi.arvuta(false, true);
		long lopp2 = System.currentTimeMillis();
		
		long algus3 = System.currentTimeMillis();
		JaanuseHargneJaKarbi.arvuta(true, false);
		long lopp3 = System.currentTimeMillis();
		
		System.out.println(lopp1-algus1);
		System.out.println(lopp2-algus2);
		System.out.println(lopp3-algus3);
	}

}

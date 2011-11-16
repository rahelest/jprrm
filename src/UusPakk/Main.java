package UusPakk;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long algus1 = System.currentTimeMillis();
		JaanuseHargneJaKarbi.arvuta(true);
		long lopp1 = System.currentTimeMillis();
		
		long algus2 = System.currentTimeMillis();
//		JaanuseHargneJaKarbi.arvuta(false);
		long lopp2 = System.currentTimeMillis();
		
		System.out.println("Koos kärpimisega: " + (lopp1 - algus1));
		System.out.println("Ilma kärpimiseta: " + (lopp2 - algus2));

	}

}

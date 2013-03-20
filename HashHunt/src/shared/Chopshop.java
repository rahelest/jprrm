package shared;

public class Chopshop {
	
	private Writer writer = null;
	
	public Chopshop(Writer writer) {
		this.writer = writer;
	}

	public void stringify(String data) {
		String result = "";
		//TODO kirjuta töötlus
		
		writer.addToQueue(result);
	}

}

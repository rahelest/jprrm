package shared;

public class Chopshop {
	
	private Writer writer = null;
	
	public Chopshop(Writer writer) {
		this.writer = writer;
	}

	public void stringify(String data) {
		data = data.trim();
		String result = "";
		
		String[] words = data.split(" ");
		for (String word : words) {
			result += "\"" + word + "\"" + ",";
			
			
//			if (words. >= words.length) {
//				result += ",";
//			}
		}		
		writer.addToQueue(result);
	}

	public void insert(float latitude, float longitude, String textOrTags) {
		String result = latitude + "," + longitude;
		String[] words = textOrTags.split(" ");
		for (String word : words) {
			result += "," + word;
		}		
		writer.addToQueue(result);
	}

}

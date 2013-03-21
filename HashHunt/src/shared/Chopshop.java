package shared;

public class Chopshop {
	
	private Writer writer = null;
	
	public Chopshop(Writer writer) {
		this.writer = writer;
	}

	public void stringify(String data) {
		data = data.trim();
		String result = "";
		String latitude = "";
		String longitude = "";
		String temp = "";
		
		//TODO kirjuta töötlus universaalseks		
		latitude = "\"" + data.substring(data.indexOf("="),data.indexOf(",")) + "\"";
		temp = data.substring(data.indexOf(", "),data.indexOf("}"));
		longitude = "\"" + temp.substring(temp.indexOf("="),temp.indexOf("}")) + "\"";
		
		
		data = data.substring(data.indexOf("} "));
		String[] words = data.split(" ");
		
		result = latitude + "," + longitude;
		for (String word : words) {
			result += "," + word;
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

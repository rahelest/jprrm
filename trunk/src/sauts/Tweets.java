package sauts;

public class Tweets {
	String name;
	String date;
	String text;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String sdate) {
		int pos; int pos2;
		String a = sdate.substring(0, pos = sdate.indexOf("-"));
		String k = sdate.substring((pos + 1), pos2 = sdate.indexOf("-", (pos + 1)));
		String d = sdate.substring((pos2 + 1), sdate.indexOf("T"));

		this.date = d + "."  + k + "." + a;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		//õ - &#245;
		if (text.indexOf("&#245") != -1) {
			text.replaceAll("&#245", "õ");
		}
		//ä - &#228;
		if (text.indexOf("&#228") != -1) {
			text.replaceAll("&#228", "ä");
		}
		// ö - &#246;
		if (text.indexOf("&#246") != -1) {
			text.replaceAll("&#246", "ö");
		}
		// ü - &#252;
		if (text.indexOf("&#252") != -1) {
			text.replaceAll("ü", "&#252");
		}
		this.text = text;
	}
	
}

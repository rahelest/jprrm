/**
 * 
 */
package twitter;

/**
 * @author Jaanus
 *
 */
public class Atweet {
	/**
	 * This holds the author of the tweet.
	 */
	private String author;
	/**
	 * This holds the posting date of the tweet.
	 */
	private String date;
	/**
	 * This holds the content of the tweet.
	 */
	private String content;
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param aAuthor the author to set
	 */
	public void setAuthor(String aAuthor) {
		author = aAuthor;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param aDate the date to set
	 */
	public void setDate(String aDate) {
		date = aDate;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param aContent the content to set
	 */
	public void setContent(String aContent) {
		content = aContent;
	}
}

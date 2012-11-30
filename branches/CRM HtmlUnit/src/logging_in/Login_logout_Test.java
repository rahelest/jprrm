package logging_in;
import junit.framework.Assert;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Login_logout_Test {
	
	@SuppressWarnings("deprecation")
	@Test
	public void login_test() throws Exception {
		
		String[] usernames = {"matmop0", "matmop0", "jama", "jama", ""};
		String[] passwords = {"tootaja", "jama", "tootaja", "jama", ""};
		Boolean[] results = {true, false, false, false, false};
		
		final int ATTEMPTS = usernames.length;
		
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.setRedirectEnabled(true);
		final HtmlPage page = webClient.getPage("http://linnaruuter.dyndns.org:7878");
		HtmlPage page2;
		
		Assert.assertEquals("Sisselogimine", page.getTitleText());
		
		HtmlForm form = (HtmlForm) page.getByXPath("//body/form").get(0);
		HtmlTextInput username = form.getInputByName("k");
		HtmlPasswordInput password = form.getInputByName("p");
		HtmlSubmitInput submit = (HtmlSubmitInput) form.getElementsByAttribute("input", "type", "submit").get(0);
		
		
		for (int i = 0; i < ATTEMPTS; i++) {
			username.setValueAttribute(usernames[i]);
			password.setValueAttribute(passwords[i]);
			page2 = submit.click();
			
			String pageAsText = page2.asText();
			
			if (!results[i]) {
				System.out.println("Negative: ");
				Assert.assertTrue(pageAsText.contains("Autentimine ebaõnnestus!"));
			} else {
				System.out.println("Positive: ");
				Assert.assertTrue(pageAsText.contains("Tere tulemast kliendihaldussüsteemi!"));
				
				logout(page2, usernames[i], passwords[i]);
			}			
		}
		
		
		//final String pageAsXML = page.asXml();
		//Assert.assertTrue(pageAsXML.contains("<body class=\"small\">"));
		webClient.closeAllWindows();
		
	}
	
	public void logout(HtmlPage page, String user, String pass) throws Exception {
		HtmlForm form = (HtmlForm) page.getByXPath("//body/div/form").get(0);
		HtmlSubmitInput submit = (HtmlSubmitInput) form.getElementsByAttribute("input", "type", "submit").get(0);
		HtmlPage page2 = submit.click();
		System.out.println("Logout check:");
		Assert.assertTrue(page2.asText().contains("Logi sisse:"));
	}

}

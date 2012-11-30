package logging_in;
import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Assert;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Login {
	
	@SuppressWarnings("deprecation")
	@Test
	public HtmlPage login() {
		
		String username = "matmop0";
		String password = "tootaja";
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.setRedirectEnabled(true);
		HtmlPage startPage = null;
		try {
			startPage = webClient.getPage("http://linnaruuter.dyndns.org:7878");
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		HtmlPage page = null;
		
		Assert.assertEquals("Sisselogimine", startPage.getTitleText());
		
		HtmlForm form = (HtmlForm) startPage.getByXPath("//body/form").get(0);
		HtmlTextInput usernameField = form.getInputByName("k");
		HtmlPasswordInput passwordField = form.getInputByName("p");
		HtmlSubmitInput submit = (HtmlSubmitInput) form.getElementsByAttribute("input", "type", "submit").get(0);
		
		

		usernameField.setValueAttribute(username);
		passwordField.setValueAttribute(password);
		try {
			page = submit.click();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return page;
		
	}
}

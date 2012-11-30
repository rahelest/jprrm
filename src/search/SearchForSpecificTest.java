package search;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import logging_in.Login;

public class SearchForSpecificTest {
	
	@Test
	public void SearchForSpecific_Test() throws Exception {
	
	String searchedClient = "Marju";
	
	Login login = new Login();
	HtmlPage page = login.login();
	
	HtmlForm form = (HtmlForm) page.getByXPath("//body/form").get(0);
	
	HtmlTextInput searchField = form.getInputByName("o");
	HtmlSubmitInput submit = (HtmlSubmitInput) form.getElementsByAttribute("input", "value", "Otsi klienti").get(0);
	
	searchField.setValueAttribute(searchedClient);
	HtmlPage page2 = submit.click();
	Assert.assertTrue(page2.asText().contains(searchedClient));
	
	
	}

}

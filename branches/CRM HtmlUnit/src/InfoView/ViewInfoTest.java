package InfoView;

import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import logging_in.Login;

public class ViewInfoTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void InfoTest() throws Exception {
	
	Login login = new Login();
	HtmlPage startPage = login.login();
	
	HtmlForm searchForm = (HtmlForm) startPage.getByXPath("//body/form").get(0);
	HtmlSubmitInput submit = (HtmlSubmitInput) searchForm.getElementsByAttribute("input", "value", "Otsi klienti").get(0);
	HtmlPage page = submit.click();
	
	List<HtmlSubmitInput> forms = (List<HtmlSubmitInput>) page.getByXPath("//td/form/input[@value='Mine']");
	Random rand = new Random();
	System.out.println(forms.size());
	int random = rand.nextInt(forms.size() + 1);
	
	HtmlSubmitInput submit2 = (HtmlSubmitInput) forms.get(random);
	HtmlPage clientPage = submit2.click();
	
	System.out.println(clientPage.asText());
	Assert.assertTrue(clientPage.asText().contains("Isikuandmed"));
	
	
	
	}

}

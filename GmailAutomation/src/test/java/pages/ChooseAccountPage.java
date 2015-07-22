package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseAccountPage extends AbstractPage {

	private final String BASE_URL = "https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1";
	
	
	@FindBy(xpath="//a[@id='account-chooser-link']")
	private WebElement linkChooseAccount;
	
	public ChooseAccountPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void signWithADifferentAccount(){
		
		linkChooseAccount.click();
	}
	
	@Override
	public void openPage() {
		
		webDriver.navigate().to(BASE_URL);
		
	}

}

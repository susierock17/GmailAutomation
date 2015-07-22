package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

	private final String BASE_URL = "https://gmail.com";

	@FindBy(xpath = "//a[@id='gmail-sign-in']")
	private WebElement linkLogin;
	
	public MainPage(WebDriver webdriver) {
		super(webdriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void clickOnLogin(){
		linkLogin.click();
	}

	@Override
	public void openPage() {
		webDriver.navigate().to(BASE_URL);
	}

}

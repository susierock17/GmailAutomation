package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.User;


public class LoginPage extends AbstractPage {

	private final String BASE_URL = "https://accounts.google.com/ServiceLogin?sacu=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail#identifier";
	
	private final Logger logger = Logger.getLogger(LoginPage.class);

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement inputLogin;

	@FindBy(xpath = "//input[@id='Passwd']" )
	private WebElement inputPassword;

	@FindBy(xpath = "//input[@id='next']")
	private WebElement buttonNext;

	@FindBy(xpath = "//input[@id='signIn']")
	private WebElement signInButton;
	
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void login(User user)
	{
		inputLogin.sendKeys(user.getUsername());
		buttonNext.click();
		inputPassword.sendKeys(user.getPassword());
		signInButton.click();
		logger.info("Login performed");
	}
	

	@Override
	public void openPage() {
		
		webDriver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}

}

package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

	protected WebDriver webDriver;
	
	public AbstractPage(WebDriver webDriver) {
		
		this.webDriver = webDriver;
	}
	
	public abstract void openPage();

}

package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.User;

public class TrashPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/u/0/#trash";
	
	@FindBy(xpath="//span[@class='gI']/img[@class='aoj']")
	private WebElement markImp;
	
	@FindBy(xpath="//img[@aria-label='Show details']")
	private WebElement showDetails;

	public TrashPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}

	public void readMessage(User user) throws InterruptedException{
		
		List <WebElement> senders = webDriver.findElements(By.xpath("//tr/td/div[not(text())]/span[@name]"));
		for(int i = 0; i<senders.size();i++){
			if(senders.get(i).getAttribute("email").equals(user.getUsername())){
				senders.get(i).click();
				webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				break;
			}
		}
		
	}
	
	public boolean checkImportance(){
		
		showDetails.click();
		try{
			
			webDriver.findElement(By.xpath("//span[@class='gI']/img[@class='aoj']")).isDisplayed();
		    return true;
			
		}
		catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	public boolean checkAttach(){
		
		try{
			webDriver.findElement(By.xpath("//div[@class='aYA']/img[@title='Image']")).isDisplayed();
			return true;
		}
		
		catch(NoSuchElementException e){
			return false;
		}
	}

	@Override
	public void openPage() {
		
		webDriver.navigate().to(BASE_URL);
	}

}

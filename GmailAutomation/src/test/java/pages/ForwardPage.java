package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import utils.User;

public class ForwardPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/u/0/#settings/fwdandpop";
	
	private Logger logger = Logger.getLogger(ForwardPage.class);
	
	@FindBy(xpath="//div[@class='aos T-I-J3 J-J5-Ji']")
	private WebElement buttonSettings;
	
	@FindBy(xpath="//div[@id='ms']/div[@class='J-N-Jz']")
	private WebElement Settings;
	
	@FindBy(xpath="//a[@href='https://mail.google.com/mail/u/0/#settings/fwdandpop']")
	private WebElement buttonPOP;
	
	@FindBy(xpath="//input[@value='Add a forwarding address']")
	private WebElement buttonForward;
	
	@FindBy(xpath="//div[@class='PN']/input[@*]")
	private WebElement inputAddress;
	
	@FindBy(xpath="//button[@class='J-at1-auR']")
	private WebElement buttonNext;
	
	@FindBy(xpath="//tr/td/input[@type='submit']")
	private WebElement buttonProceed;
	
	@FindBy(xpath="//button[@name='ok']")
	private WebElement buttonOk;
	
	@FindBy(xpath="//td[@class='r9']/div/div[1]/table[2]/tbody/tr[@class='C7']/td/input[@type='radio']")
	private WebElement radioButton;
	
	@FindBy(xpath="//button[@guidedhelpid='save_changes_button']")
	private WebElement buttonSave;
	
	@FindBy(xpath="//a[@href='https://mail.google.com/mail/u/0/#settings/filters']")
	private WebElement linkFilter;
	
	@FindBy(xpath="//tr/td[@class='rG']/span[@class='sA']")
	private WebElement linkCreateFilter;
	
	@FindBy(xpath="//span/input[@class='ZH nr aQa']")
	private WebElement inputUser;
	
	@FindBy(xpath="//div/span[@class='w-Pv ZG']/input[1]")
	private WebElement checkBoxAttachment;
	
	@FindBy(xpath="//div[@class='ZZ']/div[9]/div[2]")
	private WebElement buttonCreateWithSearch;
	
	@FindBy(xpath="//div[@class='nH']/div[6]/input[@type='checkbox']")
	private WebElement checkBoxDelete;
	
	@FindBy(xpath="//div[@class='nH']/div[8]/input[@type='checkbox']")
	private WebElement checkBoxMarkImp;
	
	@FindBy(xpath="//div[@class='T-I J-J5-Ji Zx acL T-I-atl L3']")
	private WebElement buttonCreate;
	
	public ForwardPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void goToSettings(){
		
		buttonSettings.click();
		Settings.click();
	}
	
	public void goToPopImap(){
		
		buttonPOP.click();
	}
	
	public void setForward(User user) throws InterruptedException{
		
		buttonForward.click();
		Thread.sleep(500);
		inputAddress.sendKeys(user.getUsername());
		buttonNext.click();
		Thread.sleep(1000);
		
		for(String handle: webDriver.getWindowHandles()){
			webDriver.switchTo().window(handle);
		}
		
		buttonProceed.click();
		
		Thread.sleep(1000);
		
		for(String handle: webDriver.getWindowHandles()){
			webDriver.switchTo().window(handle);
		}
		
		buttonOk.click();
		
	}
	
	public void manageForward() throws InterruptedException{
		
		radioButton.click();
		buttonSave.click();
		
	}
	
	public void manageFilter(User user) throws InterruptedException{
		
		linkFilter.click();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		linkCreateFilter.click();
		inputUser.sendKeys(user.getUsername());
		checkBoxAttachment.click();
		buttonCreateWithSearch.click();
		Thread.sleep(1000);
		checkBoxDelete.click();
		checkBoxMarkImp.click();
		buttonCreate.click();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
	}

	@Override
	public void openPage() {
		webDriver.navigate().to(BASE_URL);
		
	}

}

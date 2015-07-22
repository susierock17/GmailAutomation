package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import utils.ComputerRobot;

public class ThemePage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/u/0/#settings/oldthemes";
	
	private Logger logger = Logger.getLogger(ThemePage.class);
	
	@FindBy(xpath="//div[@class='aos T-I-J3 J-J5-Ji']")
	private WebElement buttonSettings;
	
	@FindBy(xpath="//div[@id='pbwc']/div[@class='J-N-Jz']")
	private WebElement buttonThemes;

	@FindBy(xpath="//div[@role='button'][contains(text(),'My Photos')]")
	private WebElement buttonMyPhotos;
	
	@FindBy(xpath="//div[@class='Yf-ag-Xb-xh'][contains(text(),'Upload a photo')]")
	private WebElement buttonUploadPhotos;
	
	@FindBy(xpath="//iframe[@class='KA-JQ']")
	private WebElement frameUpload;
	
	@FindBy(xpath="//div[@role='button'][contains(text(),'Select a photo from your computer')]")
	private WebElement buttonSelectPhotos;
	
	//@FindBy(xpath="//div[contains(text(),' is not supported for upload.')]")
	private String errorMessage = "//div[contains(text(),' is not supported for upload.')]";
	
	@FindBy(xpath="//div[@class='a70 aXjCH']/div[1]")
	private WebElement image;
	
	@FindBy(xpath="//div[@class='Kj-JD-Jl a8Y']/div[1]")
	private WebElement buttonSave;
	
	private WebDriverWait wait = new WebDriverWait(webDriver, 2000);
	
	public ThemePage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void goToThemes() throws InterruptedException{
		
		buttonSettings.click();
		buttonThemes.click();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		buttonMyPhotos.click();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		webDriver.switchTo().frame(frameUpload);
		
	}
	
	public boolean loadImage(String path) throws AWTException, InterruptedException{
		
		buttonUploadPhotos.click();
		wait.until(ExpectedConditions.visibilityOf(buttonSelectPhotos));
		buttonSelectPhotos.click();
		ComputerRobot.setComputerRobot(path);
		return uploadError();
	}
	
	public boolean uploadError(){
	
		try{
			return webDriver.findElement(By.xpath(errorMessage)).isDisplayed();
		}
		catch(NoSuchElementException e){
			logger.error(e.getMessage());
			return false;
		}
		 
	}
	
	public void setTheme() throws InterruptedException{
		
		image.click();
		buttonSave.click();
		
	}
	
	@Override
	public void openPage() {
		webDriver.navigate().to(BASE_URL);
		
	}

}

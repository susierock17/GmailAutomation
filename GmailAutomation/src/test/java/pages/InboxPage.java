package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import utils.ComputerRobot;
import utils.User;

public class InboxPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/#inbox";
	
	private Logger logger = Logger.getLogger(InboxPage.class);
	
	@FindBy(xpath="//div[@class='T-I J-J5-Ji T-I-KE L3']")
	private WebElement buttonCompose;
	
	@FindBy(xpath="//textarea[@class='vO']")
	private WebElement inputUserAddress;
	
	@FindBy(xpath="//input[@placeholder='Subject']")
	private WebElement inputSubjectOfMessage;
	
	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private WebElement inputTextOfMessage;
	
	@FindBy(xpath="//tr/td/div/div[@role='button']")
	private WebElement buttonSend;
	
	@FindBy(xpath="//a[@class='gb_ca gb_l gb_r gb_h']")
	private WebElement linkUser;
	
	@FindBy(xpath="//a[@id='gb_71']")
	private WebElement buttonSingOut;
	
	@FindBy(xpath="//div[@class='asl T-I-J3 J-J5-Ji']")
	private WebElement buttonSpam;
	
	@FindBy(xpath="//span[@class='CJ']")
	private WebElement buttonMore;

	@FindBy(xpath="//a[@class='J-Ke']")
	private WebElement Spam;
	
	@FindBy(xpath="//a[@rel='noreferrer']")
	private WebElement linkToGo;
	
	@FindBy(xpath="//div[@class='J-J5-Ji J-Z-I-J6-H']/div[@class='a1 aaA aMZ']")
	private WebElement buttonAttach;
	
	@FindBy(xpath="//img[@aria-label='Show details']")
	private WebElement showDetails;
	
	@FindBy(xpath="//span[@class='gI']/img[@class='aoj']")
	private WebElement markImp;
	
	@FindBy(xpath="//a[@href='https://mail.google.com/mail/u/0/#inbox']")
	private WebElement buttonInbox;
	
	@FindBy(xpath="//div[@class='Kj-JD-Jz']")
	private By warningDialog;
	
	@FindBy(xpath="//div[@class='QT aaA aMZ']")
	private WebElement buttonEmotion;
	
	@FindBy(xpath = "//button[@title = 'Show face emoticons']")
	private WebElement buttonShowEmotions;
	
	private final  String newMessageWindow = "//div[@class='aaZ']";
	
	private final String emotionWindow = "//div[@class='a8B']";
	
	
	@FindBy(xpath="//div[@class='a8C']")
	private WebElement buttonClose;
	
	
	private List<WebElement> emotionList = new ArrayList<WebElement>();
	
	public InboxPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(this.webDriver, this);
	}
	
	public void composeMessage(User user,String subject,String text){
		
		buttonCompose.click();
		inputUserAddress.sendKeys(user.getUsername());
		inputSubjectOfMessage.sendKeys(subject);
		inputTextOfMessage.sendKeys(text);
		
	}
	
	public void sendMessage() {
		
		buttonSend.click();
		logger.info("Send performed");
	}
	
	public void attachFile(String path) throws AWTException, InterruptedException{
		
		buttonAttach.click();
		ComputerRobot.setSimpleComputerRobot(path);
	    webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
	}
	
	public boolean attachBigFile(String path) throws InterruptedException, AWTException{
		
		buttonAttach.click();
		ComputerRobot.setSimpleComputerRobot(path);
	    webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		try{
			
			webDriver.findElement(By.xpath("//div[@class='Kj-JD-K7 Kj-JD-K7-GIHV4']")).isDisplayed();
		    return true;
			
		}
		catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	public List<WebElement> attachEmotion(int number) throws InterruptedException{
		buttonEmotion.click();
		buttonShowEmotions.click();
		List <WebElement> emotions = webDriver.findElements(By.xpath("//div[@class='wVboN']/button[@string]"));
		for(int i = 0;i<number;i++){
			emotions.get(i).click();
			emotionList.add(emotions.get(i));
			webDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
			Thread.sleep(2000);
		}
		buttonClose.click();
		return emotionList;
	}
	
	public void signOut(){
		
		linkUser.click();
		buttonSingOut.click();
		webDriver.manage().deleteAllCookies();
		
	}
	
	public void selectMessage(User user) throws InterruptedException{
		
		List <WebElement> senders = webDriver.findElements(By.xpath("//tr/td/div[not(text())]/span[@name]"));
		List <WebElement> checkBoxes = webDriver.findElements(By.xpath("//div[@role='checkbox']"));
		for(int i = 0; i<senders.size();i++){
			if(senders.get(i).getAttribute("email").equals(user.getUsername())){
				checkBoxes.get(i).click();
				webDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
				Thread.sleep(1000);
				break;
			}
		}
 		
		
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
	
	public void goToLink() throws InterruptedException{
	
		linkToGo.click();
		Thread.sleep(2000);
		for(String handle: webDriver.getWindowHandles()){
			webDriver.switchTo().defaultContent();
		}
		
		Thread.sleep(2000);
	}
	
	public void markMessageAsSpam(){
		
		buttonSpam.click();
		
	}
	
	public void goToSpamFolder(){
		
		buttonMore.click();
		Spam.click();
		
	}
	
	public void goToButtonMore() {
		
		buttonMore.click();
		
	}
	
	public void goToInboxFolder() {
		
		buttonInbox.click();
		
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
	
	public boolean isSpamEmpty(User user){
		
		List <WebElement> senders = webDriver.findElements(By.xpath("//tr/td/div[not(text())]/span[@name]"));
		for(int i = 0; i<senders.size();i++){
			if(senders.get(i).getAttribute("email").equals(user.getUsername())){
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isNewMessageDisplayed(){
		return webDriver.findElement(By.xpath(newMessageWindow)).isDisplayed();
	}
	
	public boolean isEmotionWindowDisplayed(){
		return webDriver.findElement(By.xpath(emotionWindow)).isDisplayed();
	}
	
	public List<WebElement> isEmotionInMessage(){
		List <WebElement> listEmotionsInMessage = webDriver.findElements(By.xpath("//div[@class='ltr']/img[@goomoji]"));
		List <WebElement> list = new ArrayList<WebElement>();
		for(int i = 0;i<listEmotionsInMessage.size();i++){
			if(emotionList.get(i).getAttribute("string").equals(listEmotionsInMessage.get(i).getAttribute("goomoji")));
				list.add(listEmotionsInMessage.get(i));
				
		}
		return list;
		
	}
	
	@Override
	public void openPage() {
		
		webDriver.navigate().to(BASE_URL);
		logger.info("Inbox Page");
		
	}

}

package steps;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.ChooseAccountPage;
import pages.ForwardPage;
import pages.InboxPage;
import pages.LoginPage;
import pages.ThemePage;
import pages.TrashPage;
import utils.User;



public class Steps {

	private WebDriver webDriver;

	private final Logger logger = Logger.getLogger(Steps.class);

	private List<WebElement> listOfEmotions;
	
	public void initBrowser() throws InterruptedException
	{
		webDriver = new FirefoxDriver();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		logger.info("Browser started");
	}
	
	public void closeDriver()
	{
		webDriver.quit();
	}

	public void loginGmail(User user) throws InterruptedException
	{
		LoginPage loginPage = new LoginPage(webDriver);
		loginPage.openPage();
		Thread.sleep(2000);
		loginPage.login(user);
	}
	
	public void sendMessage() throws InterruptedException {
		
		InboxPage inboxPage = new InboxPage(webDriver);
		inboxPage.sendMessage();

	}
	
	public void signOut() throws InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		inboxPage.signOut();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
	}
	
	public void addAccount() throws InterruptedException{
		
		ChooseAccountPage chooseAccountPage = new ChooseAccountPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		chooseAccountPage.signWithADifferentAccount();
		
	}
	
	public void markMessageAsSpam(User user) throws InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		inboxPage.openPage();
		Thread.sleep(2000);
		inboxPage.selectMessage(user);
		inboxPage.markMessageAsSpam();
		
	}
	
	public void confirmRequest(User user) throws InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		//inboxPage.openPage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		inboxPage.readMessage(user);
		inboxPage.goToLink();
	}
	
	public boolean showSpamFolder(User user){
		InboxPage inboxPage = new InboxPage(webDriver);
		inboxPage.openPage();
		return inboxPage.isSpamEmpty(user);
	}
	
	public void goToForwardPage() throws InterruptedException{
		
		ForwardPage forwardPage  = new ForwardPage(webDriver);
		forwardPage.openPage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
	}
	
	public void setForwardUser(User user) throws InterruptedException{
		
		ForwardPage forwardPage = new ForwardPage(webDriver);
		//forwardPage.goToSettings();
		//forwardPage.goToPopImap();
		forwardPage.setForward(user);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}
	
	public void manageFilter(User user) throws InterruptedException{
		
		ForwardPage forwardPage = new ForwardPage(webDriver);
		forwardPage.manageForward();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		forwardPage.goToSettings();
		forwardPage.manageFilter(user);
	}
	
	public void attachFile(String path) throws AWTException, InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		//inboxPage.openPage();
		inboxPage.attachFile(path);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		inboxPage.sendMessage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
	}
	
	public boolean checkTrashAttach(User user) throws InterruptedException{
		
		TrashPage trashPage = new TrashPage(webDriver);
		trashPage.openPage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		trashPage.readMessage(user);
		return trashPage.checkAttach();
	}
	
	public boolean checkInboxAttach(User user) throws InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		//inboxPage.openPage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		inboxPage.readMessage(user);
		return inboxPage.checkAttach();
		
	}
	
	public boolean checkTrashImportance(User user) throws InterruptedException{
		
		TrashPage trashPage = new TrashPage(webDriver);
		/*trashPage.openPage();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		trashPage.readMessage(user);*/
		return trashPage.checkImportance();
	}
	
	public boolean checkInboxImportance(User user) throws InterruptedException{
		
		InboxPage inboxPage = new InboxPage(webDriver);
		//inboxPage.goToInboxFolder();
		//inboxPage.readMessage(user);
		return inboxPage.checkImportance();
	}
	
	public boolean bigAttach(String path) throws InterruptedException, AWTException{
		InboxPage inboxPage = new InboxPage(webDriver);
		return inboxPage.attachBigFile(path);
	}
	
	public boolean setTheme(String path) throws AWTException, InterruptedException{
		ThemePage themePage = new ThemePage(webDriver);
		themePage.goToThemes();
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		themePage.loadImage(path);
		if(themePage.uploadError()){
			
			return true;
		}
		else{
			themePage.setTheme();
			return false;
		}
			
	}
	
	public boolean isNewMessage(){
		InboxPage inboxPage = new InboxPage(webDriver);
		return inboxPage.isNewMessageDisplayed();
		
	}
	
	public boolean isEmotionWindow(){
		InboxPage inboxPage = new InboxPage(webDriver);
		return inboxPage.isEmotionWindowDisplayed();
	}
	
	public List<WebElement> isEmotionInMessage(){
		InboxPage inboxPage = new InboxPage(webDriver);
		return inboxPage.isEmotionInMessage();
	}
	
	public List<WebElement> attachEmotion() throws InterruptedException{
		InboxPage inboxPage = new InboxPage(webDriver);
		listOfEmotions=inboxPage.attachEmotion(3);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		return listOfEmotions;
	}
	
	public void composeMessage(User user,String subject,String text) throws InterruptedException{
		InboxPage inboxPage = new InboxPage(webDriver);
		inboxPage.composeMessage(user, subject, text);
		webDriver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	public void readMessage(User user) throws InterruptedException{
		InboxPage inboxPage = new InboxPage(webDriver);
		inboxPage.readMessage(user);
	}
}

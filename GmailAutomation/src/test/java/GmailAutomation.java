
import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import steps.Steps;
import utils.User;



public class GmailAutomation {

	private Steps steps;
	private final User user1 = new User("user1automationtest@gmail.com","iamuser1");
	private final User user2 = new User("user2automationtest@gmail.com","iamuser2");
	private final User user3 = new User("user3automationtest@gmail.com","iamuser3");
	private final User gmailTeam = new User("forwarding-noreply@google.com"/*,"mail-noreply@google.com",*/,"");
	private final String subject = "subject";
	private final String text = "text";
	private final String PATH_IMAGE = "eclipse.exe - Shortcut"/*"Untitled.png"*/;
	private final String PATH_VIDEO = "2015-04-30 11.04 Selenium WebDriver.mp4";

	@BeforeMethod(description = "Init browser")
	public void startBrowser() throws InterruptedException
	{
		steps = new Steps();
		steps.initBrowser();
	}

	
	/*@Test(description = "The letter from user1 in Spam")
	public void setSpam() throws InterruptedException{
		
		steps.loginGmail(user1);
		steps.composeMessage(user2, subject, text);
		steps.sendMessage();
		steps.signOut();
		steps.addAccount();
		steps.loginGmail(user2);
		steps.markMessageAsSpam(user1);
		steps.signOut();
		steps.addAccount();
		steps.loginGmail(user1);
		steps.composeMessage(user2, subject, text);
		steps.sendMessage();
		steps.signOut();
		steps.addAccount();
		steps.loginGmail(user2);
		Assert.assertEquals(steps.showSpamFolder(user1),true);
		
	}
	
	
	@Test(description = "Check the letter from user1 with attach is in trash")
	public void setForwarding() throws InterruptedException, AWTException{
		
		steps.loginGmail(user2);
		steps.goToForwardPage();
		steps.setForwardUser(user1);
		steps.signOut();
		steps.loginGmail(user1);
		steps.confirmRequest(gmailTeam);
		steps.signOut();
		steps.loginGmail(user2);
		steps.goToForwardPage();
		steps.manageFilter(user3);
		steps.signOut();
		steps.loginGmail(user3);
		steps.composeMessage(user2, subject, text);
		steps.attachFile(PATH_IMAGE);
		steps.sendMessage();
		steps.composeMessage(user2, subject, text);
		steps.sendMessage();
		steps.signOut();
		steps.loginGmail(user2);
		Assert.assertTrue(steps.checkTrashAttach(user3) && steps.checkTrashImportance(user3));
		Assert.assertFalse(steps.checkInboxAttach(user3) && steps.checkInboxImportance(user3));//test failed
		steps.signOut();
		steps.loginGmail(user1);
		Assert.assertFalse(steps.checkInboxAttach(user3));
		
	}
	
	/*@Test(description = "Check warning message,size of file is bigger than 25mb")
	public void attachBigFile() throws InterruptedException, AWTException{
		
		steps.loginGmail(user1);
		Assert.assertEquals(steps.bigAttach(user2, subject, text,path),true);
		
	}*/
	
	/*@Test(description = "Check setting theme")
	public void changeTheme() throws InterruptedException, AWTException{
		
		steps.loginGmail(user1);
		Assert.assertTrue(steps.setTheme(PATH_IMAGE));
	}
*/
	@Test(description = "Check emotions")
	public void attachEmotion() throws InterruptedException{
		steps.loginGmail(user2);
		steps.composeMessage(user1, subject, text);
		Assert.assertTrue(steps.isNewMessage());
		List<WebElement> listOfEmotion = steps.attachEmotion();
		Assert.assertTrue(steps.isEmotionWindow());
		steps.sendMessage();
		Assert.assertFalse(steps.isNewMessage());
		steps.signOut();
		steps.loginGmail(user1);
		steps.readMessage(user2);
		Assert.assertEquals(listOfEmotion, steps.isEmotionInMessage());
		
	}
	
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}
	

}

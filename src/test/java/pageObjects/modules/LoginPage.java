package pageObjects.modules;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class LoginPage extends PageFactoryInitializer {

	@FindBy(xpath = "//input[@type='email']")
	public WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement loginButton;

	@FindBy(xpath = "//div[contains(text(),'Forgot')]")
	public WebElement forgotPassword;

	@FindBy(id = "alert1")
	public WebElement alert;
	
	@FindBy(xpath = "//div[contains(text(),'Forgot Password')]")
	public WebElement forgotPasswordFiled;
	
	@FindBy(css = "input[type='email']")
	public WebElement ForgotEmailFIeld;
	
	@FindBy(css = "input[type='submit']")
	public WebElement sendButton;
	
	@FindBy(css = "p[class='cr-alert__title']")
	public WebElement forgotAertMessagePoup;
	
	@FindBy(xpath = "//td[@class='subject']//a[text()='Password Reset']")
	public List<WebElement> totalMails;
	
	@FindBy(xpath = "(//a)[1]")
	public WebElement confirmMail;
	
	@FindBy(xpath = "//div[contains(text(),'Reset password')]")
	public WebElement restPassword;
	
	@FindBy(css = "input[placeholder='New password']")
	public WebElement newPassField;
	
	@FindBy(css = "input[placeholder='Repeat password']")
	public WebElement repeatPassField;
	
	@FindBy(css="input[type='button']")
	public WebElement changeButton;
	
	@FindBy(css = "p[class='cr-alert__title']")
	public WebElement changePasswordMessagePoup;
	

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());
	
	public void setUsername(String email) {
		username.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickSubmit() {
		loginButton.click();
	}

	public void verifySuccesfullLogin() {

		ExplicitWait.explicitWaitVisibilityOfElement(alert, WAIT_10_SEC);
		Assert.assertTrue(alert.isDisplayed());
		lOGGER.info("Checking whether alert is popping-up upon succesfull login");
	}

	public void verifyLoginFailure() {

		ExplicitWait.explicitWaitVisibilityOfElement(alert, WAIT_10_SEC);
		Assert.assertTrue(alert.isDisplayed());
		Assert.assertFalse(alert.isDisplayed());
		lOGGER.info("Checking whether alert is popping-up upon succesfull login");
	}

	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public String verifyForgotPassword() {
		ExplicitWait.explicitWaitVisibilityOfElement(forgotPassword, WAIT_20_SEC);
		forgotPassword.click();
		forgotPassword.click();
		lOGGER.info("Click of forgot password");
		ExplicitWait.explicitWaitVisibilityOfElement(forgotPasswordFiled, WAIT_20_SEC);
		Assert.assertTrue(forgotPasswordFiled.isDisplayed());
		
		String[] arr={"testautomation001@email.ghostinspector.com", "testautomation002@email.ghostinspector.com", "testautomation003@email.ghostinspector.com"};
      	Random r=new Random();        
      	int randomNumber=r.nextInt(arr.length);
      	System.out.println(arr[randomNumber]);
      	ForgotEmailFIeld.sendKeys(arr[randomNumber]);	
      	
      	lOGGER.info("Enter email");
      	
      	ExplicitWait.explicitWaitVisibilityOfElement(sendButton, WAIT_10_SEC);
      	sendButton.click();
     
      	ExplicitWait.explicitWaitVisibilityOfElement(forgotAertMessagePoup, WAIT_10_SEC);
      	Assert.assertTrue(forgotAertMessagePoup.getText().contains("Password reset link has been sent to your email"));
       	lOGGER.info("Forgot Passwrod Email Sent");
         String mailURL="https://email.ghostinspector.com/";
       	String emailUrl =mailURL.concat(arr[randomNumber]);
       	return emailUrl;  	
		
	}
	
	public String forgotMailLink() {

		String mailLink = "";
		pause(5000);
		driver.navigate().refresh();
		try {
			ExplicitWait.explicitWaitVisibilityOfElement(totalMails.get(0), WAIT_60_SEC * 2);
			totalMails.get(0).click();
			ExplicitWait.explicitWaitVisibilityOfElement(confirmMail, WAIT_10_SEC);
			mailLink = confirmMail.getAttribute("href");
			mailLink = mailLink.substring(mailLink.lastIndexOf("trakx.opendax"), mailLink.indexOf("lang=en") + 7);
			System.out.println(">>>>>>>>>>>>>" + mailLink);
			lOGGER.info("Fetching the link for mail verification");
		} catch (IndexOutOfBoundsException e) {
			driver.navigate().refresh();
			totalMails.get(0).click();
			ExplicitWait.explicitWaitVisibilityOfElement(confirmMail, WAIT_10_SEC);
			mailLink = confirmMail.getAttribute("href");
			mailLink = mailLink.substring(mailLink.lastIndexOf("trakx.opendax"), mailLink.indexOf("lang=en") + 7);
			System.out.println(">>>>>>>>>>>>>" + mailLink);
			lOGGER.info("Fetching the link for mail verification");
		}
		return mailLink;
	}
	
	public void changePassword() {
		ExplicitWait.explicitWaitVisibilityOfElement(restPassword, WAIT_20_SEC);
		Assert.assertTrue(restPassword.isDisplayed());
		
		String pss1 = utils.RandomGenerator.GenerateRandomAlphaNumericCharacters(18);
		String randomPassword =pss1.concat("@AB");
		System.out.println(randomPassword);
		
		ExplicitWait.explicitWaitVisibilityOfElement(newPassField, WAIT_10_SEC);
		newPassField.sendKeys(randomPassword);
		lOGGER.info("Enter new password");
		pause(5000);
		
		ExplicitWait.explicitWaitVisibilityOfElement(repeatPassField, WAIT_10_SEC);
		repeatPassField.sendKeys(randomPassword);
		lOGGER.info("Re enter password again");
		
		ExplicitWait.explicitWaitVisibilityOfElement(changeButton, WAIT_10_SEC);
		changeButton.click();
		lOGGER.info("Click on chnage button");
		
		ExplicitWait.explicitWaitVisibilityOfElement(changePasswordMessagePoup, WAIT_10_SEC);
      	Assert.assertTrue(changePasswordMessagePoup.getText().contains("Password was changed"));
       	lOGGER.info("Password was successful changed");
	}
}
package pageObjects.modules;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class ControlTowerPage extends PageFactoryInitializer {

	@FindBy(xpath = "//input[@id='email']")
	public WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;

	@FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
	public WebElement notRobot;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement signIn;

	private static final Logger lOGGER = LogManager.getLogger(ControlTowerPage.class.getName());

	public void enterEmailField(String Email) {
		ExplicitWait.explicitWaitVisibilityOfElement(email, WAIT_30_SEC);
		sendKeys(email, Email);
		lOGGER.info("Entering the value into Email text field");
	}

	public void enterPasswordField(String Password) {
		ExplicitWait.explicitWaitVisibilityOfElement(password, WAIT_30_SEC);
		sendKeys(password, Password);
		lOGGER.info("Entering the value into Password text field");
	}
	
	public void clickOnCheckbox() {
		ExplicitWait.explicitWaitVisibilityOfElement(notRobot, WAIT_30_SEC);
		click(notRobot);
		lOGGER.info("Clicking on I'm not a robot checkbox");
	}
	
	public void clickOnSignIn() {
		ExplicitWait.explicitWaitVisibilityOfElement(signIn, WAIT_30_SEC);
		click(signIn);
		lOGGER.info("Clicking on sign-In");
	}
}
package pageObjects.modules;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class GhostInspectorPage extends PageFactoryInitializer {

	@FindBy(xpath = "(//a)[1]")
	public WebElement confirmMail;

	@FindBy(xpath = "//td[@class='subject']//a[text()='Registration Confirmation']")
	public List<WebElement> totalMails;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	public WebElement confirmPassword;

	@FindBy(xpath = "//span[@class='cr-checkbox__checkitem']")
	public WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//span[@aria-labelledby='recaptcha-accessible-status']")
	public WebElement captchaCheckBox;

	@FindBy(xpath = "//input[@value='SIGN UP']")
	public WebElement signUp;

	private static final Logger lOGGER = LogManager.getLogger(GhostInspectorPage.class.getName());

	public String getMailLink() {

		String mailLink = "";
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

	public void verifyTotalMails() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		ExplicitWait.explicitWaitVisibilityOfElement(totalMails.get(0), WAIT_10_SEC);
		Assert.assertTrue(totalMails.size() > 1);
		lOGGER.info("Entering the value into Password text field");
	}

	public void enterConfirmPasswordField(String Password) {
		ExplicitWait.explicitWaitVisibilityOfElement(confirmPassword, WAIT_10_SEC);
		sendKeys(confirmPassword, Password);
		lOGGER.info("Entering the value into Confirm Password text field");
	}

	public void clickOnTermsAndConditions() {
		ExplicitWait.explicitWaitVisibilityOfElement(termsAndConditionsCheckBox, WAIT_10_SEC);
		click(termsAndConditionsCheckBox);
		lOGGER.info("Clicking on Terms And Conditions Check-Box of Sign-Up page");
	}

	public void clickOnCaptchaCheckBox() {
//		wait.forElementToBeVisible(captchaCheckBox);
//		wait.forElementToBeClickable(captchaCheckBox);
		click(captchaCheckBox);
		lOGGER.info("Clicking on Captcha Check-Box of Sign-Up page");
	}

	public void clickOnSignUp() {
		ExplicitWait.explicitWaitVisibilityOfElement(signUp, WAIT_10_SEC);
		click(signUp);
		lOGGER.info("Clicking on sign-in of registration page");
	}
}
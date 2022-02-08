package pageObjects.modules;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class VerificationPage extends PageFactoryInitializer {

	@FindBy(xpath = "//button")
	public WebElement resendConfirmation;

	@FindBy(xpath = "//p[contains(text(),'Message was sent')]")
	public WebElement resendConfirmationAlert;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	public WebElement confirmPassword;

	@FindBy(xpath = "//span[@class='cr-checkbox__checkitem']")
	public WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//span[@aria-labelledby='recaptcha-accessible-status']")
	public WebElement captchaCheckBox;

	@FindBy(xpath = "//input[@value='SIGN UP']")
	public WebElement signUp;

	private static final Logger lOGGER = LogManager.getLogger(VerificationPage.class.getName());

	public void clickOnResendConfirmation() {
		ExplicitWait.explicitWaitVisibilityOfElement(resendConfirmation, WAIT_10_SEC);
		click(resendConfirmation);
		click(resendConfirmation);
		lOGGER.info("Clicking on Resend Confirmation mail for verification");
	}

	public void validateResendConfirmation() {
		ExplicitWait.explicitWaitVisibilityOfElement(resendConfirmationAlert, WAIT_10_SEC);
		Assert.assertTrue(resendConfirmationAlert.isDisplayed());
		lOGGER.info("Verifying the Resend Confirmation mail alert pop-up");
	}

	public void clickOnSignUp() {
		ExplicitWait.explicitWaitVisibilityOfElement(signUp, WAIT_10_SEC);
		click(signUp);
		lOGGER.info("Clicking on sign-in of registration page");
	}
}
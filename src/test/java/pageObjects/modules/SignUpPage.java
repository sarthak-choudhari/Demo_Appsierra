package pageObjects.modules;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class SignUpPage extends PageFactoryInitializer {

	@FindBy(xpath = "//input[@type='email']")
	public WebElement email;

	@FindBy(xpath = "(//input[@type='password'])[1]")
	public WebElement password;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	public WebElement confirmPassword;

	@FindBy(xpath = "//span[@class='cr-checkbox__checkitem']")
	public WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//span[@aria-labelledby='recaptcha-accessible-status']")
	public WebElement captchaCheckBox;

	@FindBy(xpath = "//input[@value='SIGN UP']")
	public WebElement signUp;
	
	@FindBy(xpath = "//div[.='VERIFY YOUR EMAIL ADDRESS']")
	public WebElement verifyEmail;

	private static final Logger lOGGER = LogManager.getLogger(SignUpPage.class.getName());

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

	public void enterConfirmPasswordField(String Password) {
		ExplicitWait.explicitWaitVisibilityOfElement(confirmPassword, WAIT_30_SEC);
		sendKeys(confirmPassword, Password);
		lOGGER.info("Entering the value into Confirm Password text field");
	}

	public void clickOnTermsAndConditions() {
		ExplicitWait.explicitWaitVisibilityOfElement(termsAndConditionsCheckBox, WAIT_30_SEC);
		click(termsAndConditionsCheckBox);
		lOGGER.info("Clicking on Terms And Conditions Check-Box of Sign-Up page");
	}

	public void clickOnCaptchaCheckBox() {
		ExplicitWait.explicitWaitVisibilityOfElement(captchaCheckBox, WAIT_30_SEC);
		click(captchaCheckBox);
		lOGGER.info("Clicking on Captcha Check-Box of Sign-Up page");
	}

	public void clickOnSignUp() {
		ExplicitWait.explicitWaitVisibilityOfElement(signUp, WAIT_30_SEC);
		click(signUp);
		lOGGER.info("Clicking on sign-in of registration page");
		
			explicitWaitVisibilityOfElement(verifyEmail, WAIT_10_SEC);
			Assert.assertTrue(verifyEmail.isDisplayed());
			lOGGER.info("check for an email in your inbox with further instruction.");
		
	}

	public void verifyEmailVerification() {

		ExplicitWait.explicitWaitVisibilityOfElement(profilePage().alertMessage, WAIT_10_SEC);
		System.out.println(profilePage().alertMessage.getText());
		Assert.assertTrue(profilePage().alertMessage.isDisplayed());
		lOGGER.info("Verifying wether the email has been successfully verified or not");
	}
}
package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//a[@href='/store/customer/account/']")
	WebElement myAccount;

	@FindBy(xpath = "//span[contains(text(),'my account')]")
	WebElement myAccountButton;

	@FindBy(xpath = "//input[@name='login[username]']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@name='login[password]']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@title='Login']")
	WebElement loginButton;

	@FindBy(xpath = "(//button[@id='send2'])[1]")
	WebElement signinButton;

	@FindBy(xpath = "//div[@id='field_note_login']")
	WebElement errorMessage;

	@FindBy(xpath = "//div[@class='primary']//button")
	WebElement verificationButton;

	@FindBy(xpath = "//div[@class='primary']//button//span")
	WebElement verificationButtonText;

	@FindBy(xpath = "//div[@class='message-success success message']")
	WebElement verificationSuccessMesssage;

	@FindBy(xpath = "//input[@id='firstname-ajax']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='lastname-ajax']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='email-address-ajax']")
	WebElement email;

	@FindBy(xpath = "//input[@id='pass-ajax']")
	WebElement password;

	@FindBy(xpath = "//input[@id='confirmation-ajax']")
	WebElement confirmPassword;

	@FindBy(xpath = "//button[@title='Create an Account']")
	WebElement createAccount;

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void userLoginProcedure(String username, String password) {

		wait.forElementToBeVisible(myAccount);
		js.clickElement(myAccount);
		lOGGER.info("clicked on My Account");

		String pageTitle = driver.getTitle();

		if (pageTitle.contains("Customer Login")) {

			wait.forElementToBeVisible(myAccountButton);
			js.clickElement(myAccountButton);
			lOGGER.info("clicked on My Account");

			wait.forElementToBeVisible(usernameField);
			sendKeys(usernameField, username);
			lOGGER.info("Entering the username/ email");

			wait.forElementToBeVisible(passwordField);
			sendKeys(passwordField, password);
			lOGGER.info("entering the password");

			wait.forElementToBeVisible(signinButton);
			scrollToElementView(signinButton);
			click(signinButton);
			lOGGER.info("clicked on Sign-In Button");
		} else {
			wait.forElementToBeVisible(usernameField);
			sendKeys(usernameField, username);
			lOGGER.info("Entering the username/ email");

			wait.forElementToBeVisible(passwordField);
			sendKeys(passwordField, password);
			lOGGER.info("entering the password");

			wait.forElementToBeVisible(loginButton);
			scrollToElementView(loginButton);
			click(loginButton);
			lOGGER.info("clicked on Login Button");
		}
	}

	public void verifyErrorMessage(String emailID) {

		String actual = "The account for " + emailID
				+ " has not yet been verified. To activate the account, you’ll need to verify your email and set a new password.";
		wait.forElementToBeVisible(errorMessage);
		String expected = errorMessage.getText();
		Assert.assertEquals(expected, actual);
		lOGGER.info("Verifying the alert message getting for non-verified account");
	}

	public void verifyVerificationButtonColor() {

		wait.forElementToBeVisible(verificationButton);
		wait.forElementToBeVisible(verificationButtonText);
		String color = verificationButton.getCssValue("background-color");
		color = color.substring(color.indexOf("(") + 1, color.lastIndexOf(","));
		color = convertRGBToHex(color);
		Assert.assertEquals(color, "#F78F1E");
		lOGGER.info("Verifying the background color of verification button");
	}

	public void verifyVerificationButtonText() {

		String actual = verificationButtonText.getText();
		Assert.assertEquals("Request Verification Email", actual);
		lOGGER.info("Verifying the verification button text");
	}

	public void verifySuccessMessage() {

		wait.forElementToBeVisible(verificationButton);
		click(verificationButton);
		wait.forElementToBeVisible(verificationSuccessMesssage);
		String actual = verificationSuccessMesssage.getText();
		Assert.assertEquals("Please check your email to verify your account and set a new password", actual);
	}

	public void verifyEmailVerifcation(String emailID) {

		try {
			verifyErrorMessage(emailID);
			verifyVerificationButtonColor();
//		verifyVerificationButtonText();
			verifySuccessMessage();
		} catch (TimeoutException e) {
			verifyVerificationButtonColor();
//			verifyVerificationButtonText();
			verifySuccessMessage();
		}
	}

	public void newAccountProcedure(String emailID, String pass) {

		wait.forElementToBeVisible(myAccount);
		js.clickElement(myAccount);
		lOGGER.info("clicked on My Account");

		wait.forElementToBeVisible(firstName);
		sendKeys(firstName, "Test");
		wait.forElementToBeVisible(lastName);
		sendKeys(lastName, "Automation");
		wait.forElementToBeVisible(email);
		sendKeys(email, emailID);
		wait.forElementToBeVisible(password);
		sendKeys(password, pass);
		wait.forElementToBeVisible(confirmPassword);
		sendKeys(confirmPassword, pass);
		wait.forElementToBeVisible(createAccount);
		click(createAccount);
	}
}
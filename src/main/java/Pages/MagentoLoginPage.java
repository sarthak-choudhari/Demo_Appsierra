package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class MagentoLoginPage extends BasePage {

	@FindBy(xpath = "//input[@name='login[username]']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@name='login[password]']")
	WebElement passwordField;

	@FindBy(xpath = "//span[text()='Sign in']")
	WebElement signinButton;

	private static final Logger lOGGER = LogManager.getLogger(MagentoLoginPage.class.getName());

	public MagentoLoginPage(WebDriver driver) {
		super(driver);
	}

	public void userLoginProcedure(String username, String password) {

		wait.forElementToBeVisible(usernameField);
		sendKeys(usernameField, username);
		lOGGER.info("Entering the username/ email");

		wait.forElementToBeVisible(passwordField);
		sendKeys(passwordField, password);
		lOGGER.info("entering the password");

		wait.forElementToBeVisible(signinButton);
		js.clickElement(signinButton);
		lOGGER.info("clicked on Sign-In Button");
	}
}
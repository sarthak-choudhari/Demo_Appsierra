package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class GhostInspectorPage extends BasePage {

	@FindBy(xpath = "//td[@class='subject']//a[text()='Reset your Barcodes, Inc. password']")
	public WebElement passwordResetMail;

	@FindBy(xpath = "//a[text()='Set a New Password']")
	public WebElement setNewPassword;

	@FindBy(xpath = "//h1[@class='page-title']//span")
	public WebElement pageTitle;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement newPassword;

	@FindBy(xpath = "//input[@id='password-confirmation']")
	public WebElement confirmNewPassword;

	private static final Logger lOGGER = LogManager.getLogger(GhostInspectorPage.class.getName());

	public GhostInspectorPage(WebDriver driver) {
		super(driver);
	}

	public String generateRandomEmail() {

		String randomEmail = genRandomString();
		return randomEmail.concat("@email.ghostinspector.com");
	}

	public void verifyPasswordResendMail() {

		wait.forElementToBeVisible(passwordResetMail);
		click(passwordResetMail);
		wait.forElementToBeVisible(setNewPassword);
		click(setNewPassword);
		lOGGER.info("Clicking on the set new password button avavilable in the mail sent to user mail id");
	}

	public void verifyNewPasswordPage() {

		windowHandling();
		wait.forElementToBeVisible(pageTitle);
		Assert.assertEquals(pageTitle.getText(), "Set a New Password");
		Assert.assertTrue(newPassword.isDisplayed());
		Assert.assertTrue(confirmNewPassword.isDisplayed());
	}
}
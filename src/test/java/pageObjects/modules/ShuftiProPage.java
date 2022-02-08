package pageObjects.modules;

import java.awt.event.KeyEvent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class ShuftiProPage extends PageFactoryInitializer {

	@FindBy(xpath = "//input[@id='checkbox-1']")
	public WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//button[@id='btn-agree']")
	public WebElement continueButton;

	@FindBy(xpath = "(//button[@id='btn-stepper'])[2]")
	public WebElement startVerification;

	@FindBy(xpath = "//div[text()='National ID Card']")
	public WebElement nationalIDCard;

	@FindBy(xpath = "//div[text()='Passport']")
	public WebElement passport;

	@FindBy(xpath = "//p[text()='United Kingdom']")
	public WebElement uk;

	@FindBy(xpath = "//div[contains(text(),'Upload')]")
	public WebElement upload;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement uploadArea;

	@FindBy(xpath = "(//button[contains(text(),'Upload')])[2]")
	public WebElement uploadButton;

	@FindBy(xpath = "//button[@id='btn-continue-show-preview']")
	public WebElement yesContinue;

	private static final Logger lOGGER = LogManager.getLogger(ShuftiProPage.class.getName());

	public void clickOnPrivacyPolicyCheckbox() throws Exception {

		pause(7500);
		robotScrollDown();
		pause(3000);
		moveCursor(360, 400);
		mouseClick();
		lOGGER.info("Clicking on agree check-box in privacy policy page pop-up");
	}

	public void clickOnContinueButton() throws Exception {

		pause(7500);
		robotScrollDown();
		pause(3000);
		moveCursor(850, 515);
		mouseClick();
		lOGGER.info("Clicking on continue button of privacy policy page");
	}

	public void clickOnStartVerification() throws Exception {

		pause(7500);
		robotScrollDown();
		pause(3000);
		moveCursor(850, 515);
		mouseClick();
		lOGGER.info("Clicking on start verification");
	}

	public void clickOnNationalIDCard() throws Exception {

		pause(7500);
		scrollDown();
		pause(3000);
		moveCursor(600, 315);
		mouseClick();
		lOGGER.info("Clicking on national ID card for verification");
	}

	public void selectCountry() throws Exception {

		clickOnSearchBar();
		pause(7500);
		enterCountryName();
		pause(3000);
		moveCursor(450, 315);
		mouseClick();
		lOGGER.info("Selecting country among the list of countries");
	}

	public void clickOnSearchBar() throws Exception {

		pause(7500);
		moveCursor(650, 250);
		mouseClick();
		lOGGER.info("Search button clicked");
	}

	public void enterCountryName() {

		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
		robot.delay(500);

		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.delay(500);

		robot.keyPress(KeyEvent.VK_K);
		robot.keyRelease(KeyEvent.VK_K);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_G);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_M);
		robot.keyRelease(KeyEvent.VK_M);
		robot.delay(500);
	}

	public void documentsUploadType() throws Exception {

		pause(7500);
		moveCursor(650, 400);
		mouseClick();
		lOGGER.info("Selecting a method to upload documents");
	}

	public void clickOnUploads() throws Exception {

		pause(7500);
		robotScrollDown();
		pause(3000);
		moveCursor(865, 515);
		mouseClick();
		lOGGER.info("Clicking on uploads button");
	}

	public void uploadDocument(String filePath) throws Exception {

		fileUpload(filePath);
		clickOnStartVerification();
		lOGGER.info("Uploading front side of the real ID proof");
	}	
	
	public void clickOnNext() throws Exception {
		
		pause(7500);
		robotScrollDown();
		pause(3000);
		moveCursor(875, 515);
		mouseClick();
		lOGGER.info("Clicking on Next button");
	}
}
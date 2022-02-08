package tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.RandomGenerator;

public class OnboardingAndKYCTest extends PageFactoryInitializer {

	String mailContent;
	String randomEmail1;
	String randomEmail2;
	String protocol = "https://";
	String DomainName = "email.ghostinspector.com";

//	@BeforeClass
//	public void applicationLogin() {
//		homePage().clickOnAccountIcon();
//		homePage().clickOnSignIn();
//		loginPage().setUsername(EMAIL_ID);
//		loginPage().setPassword(PASSWORD);
//		loginPage().clickSubmit();
	//}

	@Test(priority = 1)
	public void C2362_successfulAccountCreationValidation() throws Exception {

		homePage().clickOnAccountIcon();
		homePage().clickOnSignUp();
		randomEmail1 = RandomGenerator.GenerateRandomEMAILIDs(DomainName);
		System.out.println(randomEmail1);
		signUpPage().enterEmailField(randomEmail1);
		signUpPage().enterPasswordField(TESTPASSWORD);
		signUpPage().enterConfirmPasswordField(TESTPASSWORD);
		signUpPage().clickOnTermsAndConditions();
//		signUpPage().clickOnCaptchaCheckBox();
		signUpPage().clickOnSignUp();

		MAILURL = MAILURL.concat(randomEmail1);
		driver.navigate().to(MAILURL);
		String mailLink = GIPage().getMailLink();
		mailLink = protocol.concat(mailLink);
		driver.navigate().to(mailLink);
		signUpPage().verifyEmailVerification();

		signUpPage().enterEmailField(randomEmail1);
		signUpPage().enterPasswordField(TESTPASSWORD);
		loginPage().clickSubmit();
	}

	@Test(priority = 2)
	public void C2363_resendVerificationEmail() {

		homePage().clickOnAccountIcon();
		homePage().clickOnSignUp();
		randomEmail2 = RandomGenerator.GenerateRandomEMAILIDs(DomainName);
		System.out.println(randomEmail2);
		signUpPage().enterEmailField(randomEmail2);
		signUpPage().enterPasswordField(TESTPASSWORD);
		signUpPage().enterConfirmPasswordField(TESTPASSWORD);
		signUpPage().clickOnTermsAndConditions();
//		signUpPage().clickOnCaptchaCheckBox();
		signUpPage().clickOnSignUp();
		verificationPage().clickOnResendConfirmation();

		String initialURL = MAILURL.substring(MAILURL.lastIndexOf("/") + 1);
		MAILURL = MAILURL.replace(initialURL, randomEmail2);
		System.out.println(MAILURL);
		driver.navigate().to(MAILURL);
		GIPage().verifyTotalMails();
	}
//
//	@Test(priority = 3)
//	public void phoneVerification() {
//
//		String emailID = InitializePropertyFile.property.getProperty("emailID");
//		String pass = InitializePropertyFile.property.getProperty("password");
//		HomePage homepage = new HomePage(driver);
//		homepage.clickOnAccountIcon();
//		homepage.clickOnSignIn();
//		LoginPage login = new LoginPage(driver);
//		login.sendKeys(login.username, emailID);
//		login.sendKeys(login.password, pass);
//		login.click(login.loginButton);
//		homepage.click(homepage.signupDropDown);
//		homepage.click(homepage.profile);
//		homepage.click(homepage.verify);
//	}

//	@Test(priority = 4)
	public void towerControl() throws Exception {
		driver.navigate().to(TOWER_URL);
		controlTowerPage().enterEmailField(EMAIL_ID);
		controlTowerPage().enterPasswordField(PASSWORD);
		controlTowerPage().clickOnCheckbox();
		controlTowerPage().clickOnSignIn();
	}

//	@Test(priority = 5)
	public void C2366_KYCValidation() throws Exception {

		driver.navigate().to(WEBPAGE_URL);
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().sendKeys(loginPage().username, randomEmail1);
		loginPage().sendKeys(loginPage().password, TESTPASSWORD);
		loginPage().click(loginPage().loginButton);
		homePage().click(homePage().signupDropDown);
		homePage().click(homePage().profile);
//		homePage().click(homePage().verify);
//		shuftiProPage().clickOnPrivacyPolicyCheckbox();
//		shuftiProPage().clickOnContinueButton();
//		shuftiProPage().clickOnStartVerification();
//		shuftiProPage().clickOnNationalIDCard();
//		shuftiProPage().selectCountry();
//		shuftiProPage().documentsUploadType();
//		shuftiProPage().clickOnUploads();
//		shuftiProPage().uploadDocument(FRONTSIDEIDPROOF);
//		shuftiProPage().clickOnUploads();
//		shuftiProPage().uploadDocument(BACKSIDEIDPROOF);
//		pause(90000);
//		shuftiProPage().clickOnNext();
//		shuftiProPage().clickOnStartVerification();
//		shuftiProPage().clickOnNationalIDCard();
//		shuftiProPage().selectCountry();
//		shuftiProPage().documentsUploadType();
//		shuftiProPage().clickOnUploads();
//		shuftiProPage().uploadDocument(UTILITYBILL);
//		pause(90000);
//		shuftiProPage().clickOnNext();
//		pause(30000);
//		shuftiProPage().clickOnStartVerification();
//		profilePage().verifyProfileVerificationStatus();
	}

	@Test(priority = 6)
	public void withOutLoggedInValidation() {

		homePage().clickOnAccountIcon();
		homePage().verifySideMenuWithoutSignedin();
		homePage().clickOnAccountIcon();
		homePage().verifyProjectLogo();
		homePage().verifyColorTheme();
	}

	@Test(priority = 7)
	public void withLoggedInValidation() {

		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();
		homePage().verifySideMenuWithSignedin();
		homePage().clickOnAccountIcon();
		homePage().verifyProjectLogo();
		homePage().verifyFavicon();
		homePage().verifyColorTheme();
	}
	
	@Test
	public void Forgotpasswod() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		String forgotEmail=loginPage().verifyForgotPassword();
		driver.navigate().to(forgotEmail);
		
		String Link = loginPage().forgotMailLink();
		String forgotLink = protocol.concat(Link);
		driver.navigate().to(forgotLink);
		
		loginPage().changePassword();
		
		
		
	}
}
package tests;

import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class ProfileTest extends PageFactoryInitializer {

	@BeforeMethod
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();
	}

	@Test(priority = 1)
	public void C2387_profileVerificationStatusValidation() {

		homePage().clickOnAccountIcon();
		homePage().click(homePage().profile);
		profilePage().verifyProfileVerificationStatus();

	}

	@Test(priority = 2)
	public void C2389_passwordChangeValidation() {

		homePage().clickOnAccountIcon();
		homePage().click(homePage().profile);
		profilePage().verifyChangePassword();
	}

	@Test(priority = 3)
	public void C2391_referalLinkValidation() throws HeadlessException, UnsupportedFlavorException, IOException {

		homePage().clickOnAccountIcon();
		homePage().click(homePage().profile);
		profilePage().verifyreferralLink();
	}

	@Test(priority = 4)
	public void C2395_accountActivityValidation() {

		homePage().clickOnAccountIcon();
		homePage().click(homePage().profile);
		profilePage().verifyAccountActivities();
	}
}
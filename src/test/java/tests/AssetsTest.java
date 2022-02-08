package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class AssetsTest extends PageFactoryInitializer {

	@BeforeMethod
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();

	}

	@Test(priority = 1)
	public void C2408_walletValidation() {

		assetsPage().verifyCurrency();
	
		
	}

	@Test(priority = 2)
	public void C2415_CTIsValidation() throws Exception {

		assetsPage().verifyCTIDetailsIcon();
		assetsPage().defaultWindow();
		assetsPage().verifyCTIList();
		assetsPage().verifyTransactionHistory();
		assetsPage().verifyComposition();
	}
}
package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.RandomInputPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_06_MyAssetsTest extends TestBase {

	@Parameters({"URL"})
	@BeforeMethod
	public void openPage(String URL) {
		if(URL.equals("Prod"))
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		else if(URL.equals("Sandbox"))
			driver.get(InitializePropertyFile.property.getProperty("Sandbox_URL"));
		else if(URL.equals("Staging"))
			driver.get(InitializePropertyFile.property.getProperty("Staging_URL"));
		System.out.println(driver.getCurrentUrl());
	}

	@Test(priority = 1)
	public void TC_01_verifySearch() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.selectDefaultView();
		myAsset.validSearchVerification();
		myAsset.invalidSearchVerification();
	}

	@Test(priority = 2)
	public void TC_02_sortingColumns() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.selectDefaultView();
		myAsset.sortingVerification();
	}

	@Test(priority = 3)
	public void TC_03_addAssetPageVerification() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.selectDefaultView();
		myAsset.clickOnAddAsset();
		myAsset.addAssetPageVerification(InitializePropertyFile.property.getProperty("SC_06_Page_Title"));
	}

	@Test(priority = 4)
	public void TC_04_blankColumnVerification() {

		RandomInputPage random = new RandomInputPage(driver);
		String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.selectDefaultView();
		myAsset.blankColumnVerification();
	}
}
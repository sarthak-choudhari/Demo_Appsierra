package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LocationsPage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_13_AdminPanelTest extends TestBase {

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
	public void TC_01_verifyLocationDetailPage() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
		myassets.clickOnLocation();
		LocationsPage location = new LocationsPage(driver);
		location.clickOnShowUser();
		location.verifyLocationDetailPage();
	}

	@Test(priority = 2)
	public void TC_02_verifySearchfield() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
		myassets.clickOnLocation();
		LocationsPage location = new LocationsPage(driver);
		location.liveSearchVerification();
	}

	@Test(priority = 3)
	public void TC_03_verifyAssetTab() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
		myassets.clickOnLocation();
		LocationsPage location = new LocationsPage(driver);
		location.selectUser();
		location.verifyAssetsTab();
	}
}
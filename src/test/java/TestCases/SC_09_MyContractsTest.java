package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.AddContractPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyContractsPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_09_MyContractsTest extends TestBase {

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
	
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
	}

	@Test(priority = 1)
	public void TC_01_contractDetailList() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.myContractsPageVerification(InitializePropertyFile.property.getProperty("SC_09_Page_Title_01"));

	}

	@Test(priority = 2)
	public void TC_02_verifySearch() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.validSearchVerification();
		mycontracts.invalidSearchVerification();

	}

	@Test(priority = 3)
	public void TC_03_sortingColumns() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.sortingVerification();

	}

	@Test(priority = 4)
	public void TC_04_addContract() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.clickOnAddContract();
		AddContractPage addContract = new AddContractPage(driver);
		addContract.addContractPageVerification(InitializePropertyFile.property.getProperty("SC_09_Page_Title_02"));
	}
}
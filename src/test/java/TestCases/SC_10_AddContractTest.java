package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.AddContractPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.MyContractsPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_10_AddContractTest extends TestBase {

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

	@Test()
	public void TC_01_addContract() {

			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
			assetsandservices.clickOnMyAssets();
			MyAssetsPage myAsset = new MyAssetsPage(driver);
			String randomAsset = myAsset.selectRandomAsset();
			assetsandservices.clickOnMyContracts();
			MyContractsPage mycontracts = new MyContractsPage(driver);
			mycontracts.clickOnAddContract();
			AddContractPage addcontract = new AddContractPage(driver);
			addcontract.addingContractProcedure(randomAsset);
	}
}
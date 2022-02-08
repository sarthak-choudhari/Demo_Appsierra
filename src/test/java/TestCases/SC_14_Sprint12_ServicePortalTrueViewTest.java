package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.MyTicketsPage;
import Pages.ReportDashboardPage;
import Pages.ViewReportPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_14_Sprint12_ServicePortalTrueViewTest extends TestBase {

	@Parameters({ "URL" })
	@BeforeMethod
	public void openPage(String URL) {
		if (URL.equals("Prod"))
			driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		else if (URL.equals("Sandbox"))
			driver.get(InitializePropertyFile.property.getProperty("Sandbox_URL"));
		else if (URL.equals("Staging"))
			driver.get(InitializePropertyFile.property.getProperty("Staging_URL"));
		System.out.println(driver.getCurrentUrl());
	}

	@Test(priority = 1)
	public void TC_01_AssetPercentageQuantityValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
		dashboard.clickOnSupportContractReport();
		ViewReportPage report = new ViewReportPage(driver);
		int totalTSPcontracts = report.getTotalcontracts();
		driver.navigate().back();
		dashboard.verifyAssetsDisplayForTSP(totalTSPcontracts);

		dashboard.clickOnOEMContractReport();
		int totalOEMcontracts = report.getTotalcontracts();
		driver.navigate().back();
		dashboard.verifyAssetsDisplayForOEM(totalOEMcontracts);

	}

	@Test(priority = 2)
	public void TC_02_TrueViewLinkValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.verifyTrueViewContents();
		HomePage.javaScriptClick(homepage.createTicket);
		homepage.verifyLink("Create Ticket");
		HomePage.javaScriptClick(homepage.myAssets);
		homepage.verifyLink("My Assets");
		HomePage.javaScriptClick(homepage.myContracts);
		homepage.verifyLink("My Contracts");
		HomePage.javaScriptClick(homepage.myTickets);
		homepage.verifyLink("My Tickets");
		homepage.verifyTrueViewStyle();

	}

	@Test(priority = 3)
	public void TC_03_OEMContractValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
		dashboard.clickOnOEMContractReport();
		ViewReportPage report = new ViewReportPage(driver);
		report.verifyBreadcrumbTrail();
		report.verifyContractColumn();
		report.verifyLocation();

	}

	@Test(priority = 4)
	public void SP_I322_myViewsValidationForTickets() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.verifyMyViews();

	}

	@Test(priority = 5)
	public void SP_I322_myViewsValidationForAssets() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAssets = new MyAssetsPage(driver);
		myAssets.verifyMyViews();
	}
}
package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyTicketsPage;
import Pages.RandomInputPage;
import Pages.ViewReportPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_03_MyTicketsTest extends TestBase {

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
	public void TC_01_pageRenders() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		myTickets.myTicketPageVerification(InitializePropertyFile.property.getProperty("SC_03_Page_Title"));

	}

	@Test(priority = 2)
	public void TC_02_verifySearch() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		myTickets.validSearchVerification();
		myTickets.invalidSearchVerification();

	}

	@Test(priority = 3)
	public void TC_03_sortingColumns() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		myTickets.sortingVerification();

	}

	@Test(priority = 4)
	public void TC_04_verifyPagination() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		ViewReportPage viewReport=new ViewReportPage(driver);
		viewReport.paginationVerification();
	}

	@Test(priority = 5)
	public void TC_05_createTicketPageVerification() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		myTickets.clickOnCreateTicket();
		myTickets.createTicketPageVerification(
				InitializePropertyFile.property.getProperty("SC_03_Create_Ticket_Page_Title"));
	}

	@Test(priority = 6)
	public void TC_06_blankColumnVerification() {

		RandomInputPage random = new RandomInputPage(driver);
		String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.selectDefaultView();
		myTickets.blankColumnVerification(randomUser);
	}
}
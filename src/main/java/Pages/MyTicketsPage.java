package Pages;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyTicketsPage extends BasePage {

	protected static Random r = new Random();
	protected static String actualResult;
	protected static WebElement eachData;
	protected static int k = 2;
	Xls_Reader reader = new Xls_Reader(
			System.getProperty("user.dir") + File.separator + "src/test/resources/BlankData.xlsx");
	String sheetName = "MyTickets";
	XSSFWorkbook workbook = null;

	@FindBy(xpath = "//h1[@class='page-title']")
	List<WebElement> pageHeader;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//input[@value='Create Ticket']")
	WebElement createTicket;

	@FindBy(xpath = "//tbody//tr//td[@class='sorting_1']")
	List<WebElement> textSorting;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> dateSorting;

	@FindBy(xpath = "//th[text()='Ticket ID']")
	WebElement ticketID;

	@FindBy(xpath = "//th[text()='Serial Number']")
	WebElement serialNumber;

	@FindBy(xpath = "//tbody//a[1]")
	WebElement firstTicket;

	@FindBy(xpath = "//th[text()='Title']")
	WebElement title;

	@FindBy(xpath = "//div[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//div[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//div[text()='Issue Type']")
	WebElement type;

	@FindBy(xpath = "//div[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Created Date']")
	WebElement createdDate;

	@FindBy(xpath = "//th[text()='Last Updated']")
	WebElement lastUpdated;

	@FindBy(xpath = "//div[text()='Manufacturer']//ancestor::th//div[@class='sorting-block']")
	WebElement manufacturerFilters;

	@FindBy(xpath = "//div[text()='Model']//ancestor::th//div[@class='sorting-block']")
	WebElement modelFilters;

	@FindBy(xpath = "//div[text()='Type']//ancestor::th//div[@class='sorting-block']")
	WebElement typeFilters;

	@FindBy(xpath = "//input[@value='TBD']")
	WebElement selectTBD;

	@FindBy(xpath = "//input[@value='MC9090']")
	WebElement selectMC9090;

	@FindBy(xpath = "//input[@value='PDT']")
	WebElement selectPDT;

	@FindBy(xpath = "//table//thead//tr//th")
	List<WebElement> columnHeading;

	@FindBy(xpath = "//tbody//tr//td")
	List<WebElement> tableData;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> ticketIDData;

	@FindBy(xpath = "//tbody//tr//td[4]")
	List<WebElement> manufacturerData;

	@FindBy(xpath = "//tbody//tr//td[5]")
	List<WebElement> modelData;

	@FindBy(xpath = "//tbody")
	WebElement tableBody;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//div[@class='dataTables_info']")
	WebElement dataTableInfo;

	@FindBy(xpath = "//a[@class='paginate_button current']")
	WebElement currentPage;

	@FindBy(xpath = "//span[@class='ellipsis']//following-sibling::a")
	WebElement lastPage;

	@FindBy(xpath = "//a[contains(text(),'>')]")
	WebElement nextArrow;

	@FindBy(xpath = "//ul[@itemtype='https://schema.org/BreadcrumbList']")
	WebElement header;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	// ---->>> My views locators

	@FindBy(xpath = "//select[@id='my_views_title']")
	WebElement myViewsDropDown;

	@FindBy(xpath = "//img[@id='edit_my_view']")
	WebElement editViewIcon;

	@FindBy(xpath = "//form[@name='myViewFormAdd']//input[@id='text_3']//following-sibling::img[@alt='my-view-add']")
	WebElement editViewIconInPage;

	@FindBy(xpath = "//img[@id='add_my_view']")
	WebElement addViewIcon;

	@FindBy(xpath = "//form[@name='myViewFormAdd']//span[text()='Add View']")
	WebElement addViewPageTitle;

	@FindBy(xpath = "//input[@value='Filters']")
	List<WebElement> filtersTab;

	@FindBy(xpath = "//input[@value='Actions']")
	List<WebElement> actionsTab;

	@FindBy(xpath = "//div[@class='tab_filter_model rma-my-view-tabs-filter model']//label")
	List<WebElement> filtersSubTab;

	@FindBy(xpath = "//form[@name='myViewFormEdit']//span[text()='Automation']")
	WebElement currentViewPageTitle;

	@FindBy(xpath = "//div[@class='tab-header']//input")
	List<WebElement> tabHeaders;

	@FindBy(xpath = "//div[@class='tab1_box rma-mr-20 search_1']//label")
	List<WebElement> columnSuggestions;

	@FindBy(xpath = "//span[@style='visibility: visible;']")
	List<WebElement> filtersTabSuggestions;

	@FindBy(xpath = "//select[@name='emailto']")
	List<WebElement> sendMail;

	@FindBy(xpath = "//select[@name='user_name']")
	List<WebElement> mailUsers;

	@FindBy(xpath = "//button[@data-role='closeBtn']")
	List<WebElement> closeViewPage;

	private static final Logger lOGGER = LogManager.getLogger(MyTicketsPage.class.getName());

	public MyTicketsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnCreateTicket() {

		wait.forElementToBeVisible(createTicket);
		click(createTicket);
		lOGGER.info("Clicking on Create Ticket button");
	}

	public void myTicketPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader.get(1));
		String actual = pageHeader.get(1).getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");

		wait.forElementToBeVisible(tableBody);
		lOGGER.info("displaying details of page after entering into My Tickets page :----");
		lOGGER.info(tableBody.getText());
	}

	public void validSearchVerification(String searchElement) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchElement);
		lOGGER.info("Entering the required data in search field");
		try {
			actualResult = driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]"))
					.getText();
		} catch (StaleElementReferenceException e) {
			actualResult = driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]"))
					.getText();
		}
		String expectedResult = searchElement;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void clickTicket(String searchElement) {

		wait.forPage();
		driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]")).click();

	}

	public void sortingVerification() {

		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
		lOGGER.info("Sorting the Manufacturer column in Descending order");
		verifyDescendingOrderSorting();

		wait.forElementToBeVisible(model);
		click(model);
		wait.forElementToBeVisible(model);
		click(model);
		lOGGER.info("Sorting the Model column in Descending order");
		verifyDescendingOrderSorting();

		wait.forElementToBeVisible(type);
		click(type);
		lOGGER.info("Sorting the type column in Ascending order");
		verifyAscendingOrderSorting();
	}

	public void verifyAscendingOrderSorting() {

		wait.forPage();
		for (int i = 0; i < textSorting.size() - 1; i++) {
			wait.forElementToBeVisible(textSorting.get(i));
			String data1 = textSorting.get(i).getText().toUpperCase();
			String data2 = textSorting.get(i + 1).getText().toUpperCase();

			int result = data1.compareTo(data2);
//			System.out.println(data1);
//			System.out.println(data2);
//			System.out.println(result);
			Assert.assertTrue(result <= 0);
		}
		lOGGER.info("Verifying the data in table to be sorted in Ascending order");
	}

	public void verifyDescendingOrderSorting() {

		wait.forPage();
		for (int i = 0; i < textSorting.size() - 1; i++) {
			wait.forElementToBeVisible(textSorting.get(i));
			String data1 = textSorting.get(i).getText().toUpperCase();
			String data2 = textSorting.get(i + 1).getText().toUpperCase();

			int result = data1.compareTo(data2);
//			System.out.println(data1);
//			System.out.println(data2);
//			System.out.println(result);
			Assert.assertTrue(result >= 0);
		}
		lOGGER.info("Verifying the data in table to be sorted in descending order");
	}

	public void paginationVerification() {

		boolean pages = lastPage.isDisplayed();

		if (pages == true) {

			int lastpage = Integer.parseInt(lastPage.getText());

			for (int i = 0; i < lastpage - 1; i++) {

				pause(1500);
				wait.forElementToBeVisible(currentPage);
				System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

				wait.forElementToBeVisible(dataTableInfo);
				System.out.println("Total contents in this page are :- " + dataTableInfo.getText());

				wait.forElementToBeVisible(nextArrow);
				click(nextArrow);
			}
		} else {
			wait.forElementToBeVisible(currentPage);
			System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

			wait.forElementToBeVisible(dataTableInfo);
			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		}
		pause(1500);
		wait.forElementToBeVisible(currentPage);
		System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

		wait.forElementToBeVisible(dataTableInfo);
		System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
	}

	public void createTicketPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader.get(0));
		String actual = pageHeader.get(0).getText();
		actual = actual.substring(actual.indexOf("1") + 3);
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");
	}

	public void clickOnFirstTicket() {

		wait.forElementToBeVisible(firstTicket);
		click(firstTicket);
		lOGGER.info("Clicking on first Ticket from the list");
	}

	public String selectRandomTicket() {

		List<WebElement> quotes = driver.findElements(By.xpath("//tbody//tr//td[1]//a"));

		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(quotes.size());
		System.out.println("Selected Random Ticket ID is :------" + quotes.get(nextRandomNumberIndex).getText());
		return quotes.get(nextRandomNumberIndex).getText();
	}

	public void blankColumnVerification(String username) {

		reader.removeColumn(sheetName, 0);
		reader.removeColumn(sheetName, 1);
		reader.removeColumn(sheetName, 2);

		reader.addColumn(sheetName, "USERNAME");
		reader.addColumn(sheetName, "TICKET ID");
		reader.addColumn(sheetName, "COLUMN NAME");

		String userInFile = reader.getCellData(sheetName, "USERNAME", k);
		String nextData = reader.getCellData(sheetName, "TICKET ID", k);
		if ((userInFile.contains(username) == false) && (nextData.isEmpty() == true))
			reader.setCellData(sheetName, "USERNAME", k, username);

		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		try {
			for (int i = 0; i < tableRows.size(); i++) {
				for (int j = 0; j < columnHeading.size() - 2; j++) {

					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j + 1) + "]//a"));
					if ((eachData.getText().isEmpty()) == true) {

						String ticketID = ticketIDData.get(i).getText();
						String columnName = columnHeading.get((j) % columnHeading.size()).getText();
						if (!((columnName.equals("Manufacturer")) || (columnName.equals("Model")))) {
//							System.out.println("One/Many column in this Ticket ID " + ticketID + " is blank ");
//							System.out.println("The blank Column name is " + columnName);
							reader.setCellData(sheetName, "TICKET ID", k, ticketID);
							reader.setCellData(sheetName, "COLUMN NAME", k, columnName);
							k++;
						} else
							reader.setCellData(sheetName, "TICKET ID", k, "There are no Blank data");
					}
				}
			}
			if ((eachData.getText().isEmpty()) == true)
				System.out.println("There are no blank columns in this Ticket lists");
		} catch (NoSuchElementException e) {

			wait.forElementToBeVisible(emptyTable);
			emptyTable.isDisplayed();
			System.out.println(emptyTable.getText());
		}
	}

	public void validSearchVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		int randomNumberIndex = r.nextInt(ticketIDData.size());
		wait.forElementToBeVisible(ticketIDData.get(randomNumberIndex));
		String randomRepairID = ticketIDData.get(randomNumberIndex).getText();
		System.out.println("Valid search element to be entered is  :------" + randomRepairID);

		wait.forElementToBeVisible(search);
		scrollToTop();
		sendKeys(search, randomRepairID);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(
				driver.findElement(By.xpath("//td//a[contains(text()," + "'" + randomRepairID + "'" + ")]")));
		String actualResult = driver
				.findElement(By.xpath("//td//a[contains(text()," + "'" + randomRepairID + "'" + ")]")).getText();
		String expectedResult = randomRepairID;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void invalidSearchVerification() {

		wait.forPage();
		String n = genRandomString();
		wait.forElementToBeVisible(search);
		scrollToTop();
		System.out.println("Invalid search element to be entered is :------" + n);
		lOGGER.info("Entering the required data in search field");
		sendKeys(search, n);
		search.sendKeys(Keys.ENTER);
		try {
			System.out.println(emptyTable.getText());
		} catch (Exception e) {
//			System.out.println(emptyTable.getText());
			driver.navigate().refresh();
			invalidSearchVerification();
		}
		lOGGER.info("Verifying search field with Invalid Ticket ID");
	}

	public void blankColumnVerification() {

		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");
		wait.forPage();

		try {
			for (int i = 0; i < tableRows.size(); i++) {
				for (int j = 0; j < columnHeading.size() - 2; j++) {

					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j + 1) + "]//a"));

					String ticketID = ticketIDData.get(i).getText();
					String columnName = columnHeading.get((j) % columnHeading.size()).getText();
					if (!((columnName.equals("Manufacturer")) || (columnName.equals("Model")))) {
						lOGGER.info("One/Many column in this Ticket ID " + ticketID + " is blank ");
						lOGGER.info("The blank Column name is " + columnName);
					}
				}
			}
			if ((eachData.getText().isEmpty()) == true)
				lOGGER.info("There are no blank columns in this Ticket lists");
		} catch (

		NoSuchElementException e) {

			wait.forElementToBeVisible(emptyTable);
			emptyTable.isDisplayed();
			lOGGER.info(emptyTable.getText());
		}
	}

	public void verifyMyViewsData() {

		wait.forPage();
		for (int i = 0; i < tableData.size() - 1; i++) {
			wait.forElementToBeVisible(tableData.get(i));
			String data1 = tableData.get(i).getText().toUpperCase();
			String data2 = tableData.get(i + 1).getText().toUpperCase();

			int result = data1.compareTo(data2);
//			System.out.println(data1);
//			System.out.println(data2);
//			System.out.println(result);
			Assert.assertTrue(result == 0);
		}
		lOGGER.info("Verifying the data in table to be appeared after applying filter in my views");
	}

	public void verifyAddViewPage() {

		wait.forElementToBeVisible(addViewIcon);
		click(addViewIcon);
		wait.forElementToBeVisible(addViewPageTitle);
		Assert.assertTrue(addViewPageTitle.isDisplayed());
		wait.forElementToBeVisible(closeViewPage.get(1));
		click(closeViewPage.get(1));
		lOGGER.info("Verifying the add view page appearing when clicked on add view + icon");
	}

	public void verifyEditViewPage() {

		wait.forElementToBeVisible(editViewIcon);
		click(editViewIcon);
		wait.forElementToBeVisible(currentViewPageTitle);
		Assert.assertEquals(currentViewPageTitle.getText(), "Automation");
		wait.forElementToBeVisible(closeViewPage.get(2));
		click(closeViewPage.get(2));
		lOGGER.info("Verifying the edit view page appearing when clicked on edit view pencil icon");
	}

	public void verifyAddEditColumns() {

		wait.forElementToBeVisible(addViewIcon);
		click(addViewIcon);

		wait.forElementToBeVisible(editViewIconInPage);
		Assert.assertTrue(editViewIconInPage.isDisplayed());

		for (int i = 0; i < tabHeaders.size() - 3; i++) {
			wait.forElementToBeVisible(tabHeaders.get(i));
			Assert.assertTrue(tabHeaders.get(i).isDisplayed());
		}
		for (int i = 0; i < columnSuggestions.size(); i++) {
			wait.forElementToBeVisible(columnSuggestions.get(i));
			Assert.assertTrue(columnSuggestions.get(i).isDisplayed());
		}
		lOGGER.info("Verifying the contents present in edit or add view page ");
	}

	public void verifyFiltersTab() {

		wait.forElementToBeVisible(editViewIcon);
		click(editViewIcon);
		wait.forElementToBeVisible(filtersTab.get(1));
		click(filtersTab.get(1));
		wait.forElementToBeVisible(filtersTabSuggestions.get(1));
		Assert.assertTrue(filtersTabSuggestions.get(1).isDisplayed());
		for (int i = 0; i < filtersSubTab.size(); i++) {
			wait.forElementToBeVisible(filtersSubTab.get(i));
			Assert.assertTrue(filtersSubTab.get(i).isDisplayed());
		}
		lOGGER.info("Verifying the filters tab in edit view page section");
	}

	public void verifyActionsTab() {

		wait.forElementToBeVisible(actionsTab.get(1));
		click(actionsTab.get(1));
		wait.forElementToBeVisible(sendMail.get(1));
		Assert.assertTrue(sendMail.get(1).isDisplayed());
		wait.forElementToBeVisible(mailUsers.get(1));
		Assert.assertTrue(mailUsers.get(1).isDisplayed());
		lOGGER.info("Verifying the actions tab in edit view page section");
	}

	public void verifyMyViews() {

		wait.forElementToBeVisible(myViewsDropDown);
		dropDownMethod(myViewsDropDown, "VisibleText", "Automation");
		verifyMyViewsData();
		verifyAddViewPage();
		verifyEditViewPage();
		verifyAddEditColumns();
		verifyFiltersTab();
		verifyActionsTab();
	}
}
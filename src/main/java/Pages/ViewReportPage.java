package Pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ViewReportPage extends BasePage {

	protected static int i;
	protected static Random r = new Random();
	protected static ArrayList<String> obtainedList = new ArrayList<String>();
	protected static ArrayList<String> sortedList = new ArrayList<String>();

	@FindBy(xpath = "//div[@class='breadcrumbs']//strong")
	WebElement breadcrumbTitle;

	@FindBy(xpath = "//h1//span")
	WebElement reportHeader1;

	@FindBy(xpath = "//div[@class='box-head page-sub-title']")
	WebElement reportHeader2;

	@FindBy(xpath = "//input[@id='from_date']")
	WebElement fromDate;

	@FindBy(xpath = "//input[@id='to_date']")
	WebElement toDate;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> table;

	@FindBy(xpath = "//tbody//tr//td[9]")
	List<WebElement> dateTable;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> repairIDList;

	@FindBy(xpath = "//tbody//tr//td[2]")
	List<WebElement> serialNoList;

	@FindBy(xpath = "//th[text()='Serial No']")
	WebElement serialNo;

	@FindBy(xpath = "//th[text()='Subject']")
	WebElement subject;

	@FindBy(xpath = "//th[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//th[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//th[text()='Type']")
	WebElement type;

	@FindBy(xpath = "//th[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Last Updated']")
	WebElement lastUpdated;

	@FindBy(xpath = "//div[@class='dataTables_info']")
	WebElement dataTableInfo;

	@FindBy(xpath = "//a[@class='paginate_button current']")
	WebElement currentPage;

	@FindBy(xpath = "//a[@class='paginate_button next']//preceding-sibling::span//a")
	List<WebElement> pages;

	@FindBy(xpath = "//a[contains(text(),'>')]")
	WebElement nextArrow;

	@FindBy(xpath = "//table//th[3]")
	WebElement firstColumn;

	@FindBy(xpath = "//table//tr//td[3]")
	List<WebElement> firstColumnData;

	@FindBy(xpath = "//table//th[4]")
	WebElement secondColumn;

	@FindBy(xpath = "//table//tr//td[4]")
	List<WebElement> secondColumnData;

	@FindBy(xpath = "//table//th[5]")
	WebElement thirdColumn;

	@FindBy(xpath = "//table//tr//td[5]")
	List<WebElement> thirdColumnData;

	@FindBy(xpath = "//table//th[6]")
	WebElement fourthColumn;

	@FindBy(xpath = "//table//tr//td[6]")
	List<WebElement> fourthColumnData;

	@FindBy(xpath = "//table//tr//td[7]")
	List<WebElement> fifthColumnData;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> tableLength;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	@FindBy(xpath = "//div[@class='sorting-block']")
	WebElement contractTypeDropDown;

	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> contractTypes;

	private static final Logger lOGGER = LogManager.getLogger(ViewReportPage.class.getName());

	public ViewReportPage(WebDriver driver) {
		super(driver);
	}

	public void verifyReportDetails(String expected) {

		try {
			wait.forElementToBeVisible(reportHeader1);
			String actual = reportHeader1.getText();
			Assert.assertTrue(expected.contains(actual));
		} catch (TimeoutException e) {
			wait.forElementToBeVisible(reportHeader1);
			String actual = reportHeader1.getText();
			Assert.assertTrue(expected.contains(actual));
		}
		lOGGER.info("Verifying the report details page Heading");
	}

	public void clickOnViewReport(String widgetTitle) {

		WebElement viewReport = driver.findElement(By.xpath(
				"//h3[contains(text()," + "'" + widgetTitle + "'" + ")]//ancestor::div[@class='inner']//div//a"));
		scrollToElementView(viewReport);
		viewReport.click();
	}

	public void validSearchVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		int randomNumberIndex = r.nextInt(repairIDList.size());
		wait.forElementToBeVisible(repairIDList.get(randomNumberIndex));
		String randomRepairID = repairIDList.get(randomNumberIndex).getText();
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

		String n = genRandomString();
		wait.forElementToBeVisible(search);
		scrollToTop();
		System.out.println("Invalid search element to be entered is :------" + n);
		sendKeys(search, n);
		lOGGER.info("Entering the required data in search field");
		try {
			System.out.println(emptyTable.getText());
		} catch (StaleElementReferenceException e) {
			System.out.println(emptyTable.getText());
		}
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}

	public void paginationVerification() {

		int lastPage = 0;
		System.out.println(pages.size());
		if ((pages.size()) == 0) {

//			try {
//				wait.forPage();
//				wait.forElementToBeVisible(dataTableInfo);
//			} catch (TimeoutException e) {
//				driver.navigate().refresh();
//				wait.forPage();
//				wait.forElementToBeVisible(dataTableInfo);
//			}
//			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		} else {
			String last = driver
					.findElement(By.xpath(
							"//a[@class='paginate_button next']//preceding-sibling::span//a[" + pages.size() + "]"))
					.getText();
			try {
				lastPage = Integer.parseInt(last);
			} catch (Exception e) {
				lastPage = 1;
			}
			for (i = 0; i < lastPage - 1; i++) {

				wait.forPage(1500);
				wait.forElementToBeVisible(currentPage);
				Assert.assertEquals(currentPage.getText(), Integer.toString(i + 1));
//				System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

				wait.forElementToBeVisible(dataTableInfo);
//				System.out.println("Total contents in this page are :- " + dataTableInfo.getText());

				wait.forElementToBeVisible(nextArrow);
				click(nextArrow);
			}

			try {
			wait.forPage(1500);
			wait.forElementToBeVisible(currentPage);
			Assert.assertEquals(currentPage.getText(), Integer.toString(i + 1));
//			System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

			wait.forElementToBeVisible(dataTableInfo);
//			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		}catch(TimeoutException e) {
		}
			}
	}

	public void dateRangeVerification(String startDate, String endDate,String url) {

		wait.forPage();
		driver.navigate().to(url+"store/rma_assetmanagement/report/totalserviced?sdate="
				+ startDate + "&todate=" + endDate + "");
		wait.forPage();
		click(lastUpdated);
		wait.forPage();
//		wait.forElementToBeVisible(dateTable.get(0));
//		List<WebElement> dateContent = dateTable;
//		for (int i = 0; i < dateContent.size(); i++) {
//			System.out.println(
//					"displaying details of table after sorting of column :----" + dateContent.get(i).getText());
//		}
		verifyDate();
	}

	public void verifyDate() {

		for (int i = 0; i < dateTable.size() - 1; i++) {
			wait.forElementToBeVisible(dateTable.get(i));
			String date1 = dateTable.get(i).getText();
			String date2 = dateTable.get(i + 1).getText();

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date parsedDate1 = dateFormat.parse(date1);
				Date parsedDate2 = dateFormat.parse(date2);
				int result = parsedDate1.compareTo(parsedDate2);
//				System.out.println(parsedDate1);
//				System.out.println(parsedDate2);
//				System.out.println(result);
				Assert.assertTrue(result <= 0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		lOGGER.info("Verifying the dates in proper order");
	}

	public void verifyAscendingOrderSorting() {

		wait.forPage();
		for (int i = 0; i < table.size() - 1; i++) {
			wait.forElementToBeVisible(table.get(i));
			String data1 = table.get(i).getText().toUpperCase();
			String data2 = table.get(i + 1).getText().toUpperCase();

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
		for (int i = 0; i < table.size() - 1; i++) {
			wait.forElementToBeVisible(table.get(i));
			String data1 = table.get(i).getText().toUpperCase();
			String data2 = table.get(i + 1).getText().toUpperCase();

			int result = data1.compareTo(data2);
//			System.out.println(data1);
//			System.out.println(data2);
//			System.out.println(result);
			Assert.assertTrue(result >= 0);
		}
		lOGGER.info("Verifying the data in table to be sorted in descending order");
	}

	public void sortingVerification() {

		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		lOGGER.info("Sorting the " + firstColumn.getText() + " column in Descending order");
		verifyDescendingOrderSorting();

		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		lOGGER.info("Sorting the " + secondColumn.getText() + " column in Descending order");
		verifyDescendingOrderSorting();

		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		lOGGER.info("Sorting the " + thirdColumn.getText() + " column in Descending order");
		verifyAscendingOrderSorting();

		wait.forElementToBeVisible(fourthColumn);
		click(fourthColumn);
		lOGGER.info("Sorting the " + fourthColumn.getText() + " column in Ascending order");
		verifyAscendingOrderSorting();
	}

	public void blankRepairIDVerification(String widgetTitle) {

		try {
			System.out.println("The Ticket Widget title selected is :------" + reportHeader1.getText());
		} catch (TimeoutException e) {
			System.out.println("The Ticket Widget title selected is :------" + reportHeader2.getText());
		}
		int count = 0;
		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		try {
			for (int i = 0; i < repairIDList.size(); i++) {

				String repairID = repairIDList.get(i).getText();
				String serialNo = serialNoList.get(i).getText();
				if (repairID.equals("")) {
					System.out.println("There is a blank Repair ID in " + widgetTitle
							+ " widget and Serial No associated with it is " + serialNo);
					count = count + 1;
				}
			}
			System.out.println("Total number of blank Repair ID's are " + count);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(emptyTable.getText());
		}
	}

	public int getTotalcontracts() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");
		wait.forPage();
		lOGGER.info("Fetching the total assets under TSP contracts from the table");
		wait.forElementToBeVisible(tableLength.get(0));
		return tableLength.size();
	}

	public void verifyBreadcrumbTrail() {

		wait.forElementToBeVisible(breadcrumbTitle);
		wait.forElementToBeVisible(reportHeader2);
		Assert.assertEquals(breadcrumbTitle.getText(), reportHeader2.getText());
	}

	public void verifyContractColumn() {

		wait.forElementToBeVisible(fourthColumn);
		click(fourthColumn);
		contractTypeData();
		wait.forElementToBeVisible(contractTypeDropDown);
		click(contractTypeDropDown);
		wait.forElementToBeVisible(contractTypes.get(0));
		click(contractTypes.get(0));
		contractTypeData();
		lOGGER.info("Verifying the data to be sorted and filtered out in contract type column");
	}

	public void contractTypeData() {

		for (int i = 0; i < fourthColumnData.size(); i++)
			Assert.assertEquals(fourthColumnData.get(i).getText(), "OEM Service Contracts");
	}

	public void verifyLocation() {

		for (int i = 0; i < fifthColumnData.size(); i++) {
			wait.forElementToBeVisible(fifthColumnData.get(i));
			if (fifthColumnData.get(i).getText() != "-NA-")
				click(fifthColumnData.get(i));
		}
		System.out.println("There are no locations assigned to the contracts");
	}
}
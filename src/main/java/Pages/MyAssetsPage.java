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
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyAssetsPage extends BasePage {

	protected static String actualResult;
	protected static Random r = new Random();
	protected static WebElement eachData;
	protected static List<WebElement> assetDataList = new ArrayList<WebElement>();
	protected static String locationData;

	@FindBy(xpath = "//h1//span")
	WebElement pageHeader;

	@FindBy(xpath = "//div[@class='buttons-bottom']//input[@value='Add Asset']")
	WebElement addAsset;

	@FindBy(xpath = "//div[@class='top']//input[@value='Add Contract']")
	WebElement addContract;

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

	@FindBy(xpath = "//tbody")
	WebElement tableBody;

	@FindBy(xpath = "//table//thead//tr//th")
	List<WebElement> columnHeading;

	@FindBy(xpath = "//tbody//tr//td")
	List<WebElement> tableData;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//tbody//tr//td[1]//a")
	List<WebElement> assetSerialNumber;

	@FindBy(xpath = "//tbody//tr//td[7]")
	List<WebElement> OEMContract;

	@FindBy(xpath = "//label[text()='OEM Contract']//following-sibling::span")
	WebElement OEMContractDetails;

	@FindBy(xpath = "//tbody//tr//td[8]")
	List<WebElement> trueSupportContract;

	@FindBy(xpath = "//tbody//tr//td[10]//a")
	List<WebElement> location;

	@FindBy(xpath = "//label[text()='Last Address Shipped To: ']//following-sibling::span")
	WebElement locationDetails;

	@FindBy(xpath = "//tbody//a[1]")
	WebElement firstAsset;

	@FindBy(xpath = "//tbody//tr//td[@class='sorting_1']")
	List<WebElement> textSorting;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> dateSorting;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//th[text()='Subject']")
	WebElement subject;

	@FindBy(xpath = "//th//div[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//th//div[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//th//div[text()='Type']")
	WebElement type;

	@FindBy(xpath = "//th//div[text()='Created Date']")
	WebElement createdDate;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//ul[@itemtype='https://schema.org/BreadcrumbList']")
	WebElement header;

	@FindBy(xpath = "//tbody//td[1]//a")
	List<WebElement> assets;

	@FindBy(xpath = "//select[@name='assetsTable_length']")
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

	private static final Logger lOGGER = LogManager.getLogger(MyAssetsPage.class.getName());

	public MyAssetsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnAddAsset() {

		wait.forElementToBeVisible(addAsset);
		click(addAsset);
		lOGGER.info("Clicking on Add Asset button");

	}

	public void clickOnAddContract() {

		wait.forElementToBeVisible(addContract);
		click(addContract);
		lOGGER.info("Clicking on Add Contract button");
	}

	public void verifyAsset(String random) {

		wait.forPage();
		WebElement element = driver.findElement(By.xpath("//a[contains(text()," + "'" + random + "'" + ")]"));
		wait.forElementToBeVisible(element);
		js.clickElement(element);
	}

	public void clickOnTBDFilter() {

		wait.forElementToBeVisible(selectTBD);
		click(selectTBD);
		lOGGER.info("Clicking on TDB Filter button");
	}

	public void clickOnMC9090Filter() {

		wait.forElementToBeVisible(selectMC9090);
		click(selectMC9090);
		lOGGER.info("Clicking on MC9090 Filter button");
	}

	public void clickOnPDTFilter() {

		wait.forElementToBeVisible(selectPDT);
		click(selectPDT);
		lOGGER.info("Clicking on PDT Filter button");
	}

	public void enterSearchField(String searchSerialNumber) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchSerialNumber);
		lOGGER.info("Entering the required data in search field");
	}

	public void applyFilters() {

		pause(5000);
		wait.forElementToBeVisible(header);
		click(header);
		lOGGER.info("Clicking on Header of the page");

		wait.forElementToBeVisible(manufacturerFilters);
		Actions action = new Actions(driver);
		action.moveToElement(manufacturerFilters).click().build().perform();
		lOGGER.info("Clicking on Manufacturer Filters button");
		clickOnTBDFilter();

		wait.forElementToBeVisible(modelFilters);
		click(modelFilters);
		lOGGER.info("Clicking on Model Filters button");
		clickOnMC9090Filter();

		wait.forElementToBeVisible(typeFilters);
		click(typeFilters);
		lOGGER.info("Clicking on Type Filters button");
		clickOnPDTFilter();

		pause(5000);
		wait.forElementToBeVisible(tableBody);
		lOGGER.info("displaying details of table after applying filters :----" + tableBody.getText());
	}

	public void validSearchVerification(String searchSerialNumber) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchSerialNumber);
		lOGGER.info("Entering the required data in search field");
		try {
			actualResult = driver.findElement(By.xpath("//a[contains(text()," + "'" + searchSerialNumber + "'" + ")]"))
					.getText();
		} catch (StaleElementReferenceException e) {
			actualResult = driver.findElement(By.xpath("//a[contains(text()," + "'" + searchSerialNumber + "'" + ")]"))
					.getText();
		}
		String expectedResult = searchSerialNumber;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void sortingVerification() {

		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
		lOGGER.info("Sorting the Manufacturer column in Descending order");
		verifyDescendingOrderSorting();

		driver.navigate().refresh();
		wait.forPage();
		wait.forElementToBeVisible(model);
		click(model);
		wait.forElementToBeVisible(model);
		click(model);
		lOGGER.info("Sorting the Model column in Descending order");
		verifyDescendingOrderSorting();

		driver.navigate().refresh();
		wait.forPage();
		wait.forElementToBeVisible(type);
		click(type);
		lOGGER.info("Sorting the type column in Ascending order");
		verifyAscendingOrderSorting();

		driver.navigate().refresh();
		wait.forPage();
		wait.forElementToBeVisible(createdDate);
		click(createdDate);
		lOGGER.info("Sorting the Created Date column in Ascending order");
		verifyDate();
	}

	public void verifyDate() {

		wait.forPage();
		for (int i = 0; i < dateSorting.size() - 1; i++) {
			wait.forElementToBeVisible(dateSorting.get(i));
			String date1 = dateSorting.get(i).getText();
			String date2 = dateSorting.get(i + 1).getText();

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

	public void addAssetPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");
	}

	public void clickOnFirstAsset() {

		wait.forElementToBeVisible(firstAsset);
		click(firstAsset);
		lOGGER.info("Clicking on first Asset from the list");
	}

	public String selectRandomAsset() {

		String ignoredAssets = "12345 00001 Not Available";
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");

		wait.forPage(2000);
		int randomNumberIndex = r.nextInt(assets.size());
		String randomAsset = assets.get(randomNumberIndex).getText();
		System.out.println("The Asset serial no before ignoring is :------" + randomAsset);
		while ((ignoredAssets.contains(randomAsset)) == true) {
			randomNumberIndex = r.nextInt(assets.size());
			randomAsset = assets.get(randomNumberIndex).getText();
			System.out.println("The Asset serial no after ignoring is :------" + randomAsset);
		}

		return randomAsset;
	}

	public void blankColumnVerification() {

//		wait.forElementToBeVisible(tableLengthDropDown);
//		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		for (int i = 0; i < tableRows.size(); i++) {
			for (int j = 0; j < columnHeading.size(); j++) {

				if ((j % 9) > 3) {
					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j) + "]"));

					if ((eachData.getText().isEmpty()) == true) {
						String serialNumber = assetSerialNumber.get(i).getText();
						String columnName = columnHeading.get((j) % columnHeading.size()).getText();
						System.out.println("One/Many column in this Asset " + serialNumber + " is blank ");
						System.out.println("The blank Column name is " + columnName);
					}
				}
			}
		}
		if ((eachData.getText().isEmpty()) == false)
			System.out.println("There are no blank columns in this Asset listing");
	}

	public void contractDetailsVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");
		wait.forPage();

		for (int i = 0; i < OEMContract.size(); i++) {
			String OEMContractData = OEMContract.get(i).getText();
			String trueSupportData = trueSupportContract.get(i).getText();
			if ((OEMContractData.equals("No") == false) || (trueSupportData.equals("No") == false)) {
				WebElement assetData = assetSerialNumber.get(i);
				assetDataList.add(assetData);
			}
		}
		if (assetDataList.size() == 0)
			System.out.println("There are no contracts associated with this asset");
		else {
			int randomNumberIndex = r.nextInt(assetDataList.size());
			wait.forPage();
			js.clickElement(assetDataList.get(randomNumberIndex));
//			AssetDetailsPage assetdetail = new AssetDetailsPage(driver);
//			assetdetail.getFirstContractName();
			wait.forElementToBeVisible(OEMContractDetails);
			Assert.assertTrue(OEMContractDetails.isDisplayed() == true);
			assetDataList.clear();
		}
	}

	public void locationVerfication() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");
		wait.forPage();

		if (location.size() > 0) {
			int randomNumberIndex = r.nextInt(location.size());
			locationData = location.get(randomNumberIndex).getText();
			System.out.println("Selected location is ----------> " + locationData + " ----->");
			js.clickElement(location.get(randomNumberIndex));
			AssetDetailsPage assetdetail = new AssetDetailsPage(driver);
			assetdetail.locationValidation(locationData);
//			System.out.println("Selected location is ----------2" + locInPage);

		} else
			System.out.println("There is no location mentioned for these list of Assets");
	}

	public void validSearchVerification() {

		String ignoredAssets = "Not Available";
		String randomRepairID;
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");

		int randomNumberIndex = r.nextInt(assetSerialNumber.size());
		wait.forElementToBeVisible(assetSerialNumber.get(randomNumberIndex));
		try {
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
		} catch (StaleElementReferenceException e) {
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
		}
		while ((ignoredAssets.contains(randomRepairID)) == true) {
			randomNumberIndex = r.nextInt(assetSerialNumber.size());
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
			System.out.println("The Asset after ignoring is :------" + randomRepairID);
		}
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
		search.sendKeys(Keys.ENTER);
		lOGGER.info("Entering the required data in search field");
		try {
			wait.forElementToBeVisible(emptyTable);
			System.out.println(emptyTable.getText());
		} catch (StaleElementReferenceException e) {
			wait.forElementToBeVisible(emptyTable);
			System.out.println(emptyTable.getText());
		}
		lOGGER.info("Verifying search field with Invalid Serial Number");
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
		for (int i = 0; i < columnSuggestions.size() / 2; i++) {
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
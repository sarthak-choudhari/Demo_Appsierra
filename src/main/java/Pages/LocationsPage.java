package Pages;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class LocationsPage extends BasePage {

	Actions action = new Actions(driver);
	Random random = new Random();

	@FindBy(xpath = "//div[@class='breadcrumbs']//strong")
	WebElement pageTitle;

	@FindBy(xpath = "//input[@value='Add User']")
	WebElement addUser;

	@FindBy(xpath = "//input[@value='Add Asset']")
	WebElement addAssetButton;

	@FindBy(xpath = "//div[text()='Add Asset']")
	WebElement addAssetTitle;

	@FindBy(xpath = "//input[@type='select-one']")
	WebElement assetSearchField;

	@FindBy(xpath = "//div[@class='selectize-dropdown-content']")
	WebElement assetsSuggestions;

	@FindBy(xpath = "//tbody//td[3]")
	List<WebElement> noOfAssets;

	@FindBy(xpath = "//a[text()='Show Users']")
	List<WebElement> showUser;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//td[2]//a")
	List<WebElement> address;

	@FindBy(xpath = "//a[text()='Assets']")
	WebElement assetsTab;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	private static final Logger lOGGER = LogManager.getLogger(HomePage.class.getName());

	public LocationsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnShowUser() {

		driver.navigate().refresh();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");
		wait.forPage();
		int randomNumberIndex = random.nextInt(showUser.size());
		showUser.get(randomNumberIndex).click();
		lOGGER.info("Clicking on random user");
	}

	public void verifyLocationDetailPage() {

		wait.forElementToBeVisible(pageTitle);
		Assert.assertEquals(pageTitle.getText(), "Locations");
		wait.forElementToBeVisible(addUser);
		Assert.assertTrue(addUser.isDisplayed() == true);
		lOGGER.info("Verifying the page title and Users sub-tab of Location details page");
	}

	public void liveSearchVerification() {

		driver.navigate().refresh();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

//		int randomNumberIndex = random.nextInt(address.size());
//		wait.forElementToBeVisible(address.get(randomNumberIndex));
//		String randomAddress = address.get(randomNumberIndex).getText();
		String randomAddress = "Tes test test ab India 123";
		System.out.println("Valid search element to be entered is  :------" + randomAddress);

		wait.forElementToBeVisible(search);
		scrollToTop();
		sendKeys(search, randomAddress);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(
				driver.findElement(By.xpath("//td//a[contains(text()," + "'" + randomAddress + "'" + ")]")));
		String actualResult = driver
				.findElement(By.xpath("//td//a[contains(text()," + "'" + randomAddress + "'" + ")]")).getText();
		String expectedResult = randomAddress;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void selectUser() {

		driver.navigate().refresh();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		for (int i = 0; i < noOfAssets.size(); i++) {
			wait.forElementToBeVisible(noOfAssets.get(i));
			driver.navigate().refresh();
			if (noOfAssets.get(i).getText() != "0") {
				wait.forElementToBeVisible(showUser.get(i));
				js.clickElement(showUser.get(i));
				break;
			}
		}
	}

	public void verifyAssetsTab() {

		wait.forElementToBeVisible(assetsTab);
		js.clickElement(assetsTab);
		Assert.assertTrue(noOfAssets.size() > 0);
		wait.forElementToBeVisible(addAssetButton);
		js.clickElement(addAssetButton);
		wait.forElementToBeVisible(addAssetTitle);
		Assert.assertTrue(addAssetTitle.isDisplayed() == true);
		wait.forElementToBeVisible(assetSearchField);
		sendKeys(assetSearchField, "123");
		wait.forElementToBeVisible(assetsSuggestions);
		Assert.assertTrue(assetsSuggestions.isDisplayed() == true);
	}
}

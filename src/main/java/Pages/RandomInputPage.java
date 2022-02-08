package Pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class RandomInputPage extends BasePage {

	protected static String username;
	protected static String[] randomUsers;
	protected static Random r = new Random();

	Xls_Reader reader = new Xls_Reader(
			System.getProperty("user.dir") + File.separator + "src/test/resources/Banners.xlsx");
	XSSFWorkbook workbook = null;

	@FindBy(xpath = "//input[@id='serial_no']")
	WebElement assetSerialNumber;

	@FindBy(xpath = "//div[@class='airtrack-banner' or @class='airtrack-banner general']")
	WebElement airTrackBanner;

	@FindBy(xpath = "//div[@class='airtrack-banner']//h4")
	WebElement bannerText;

	@FindBy(xpath = "//h1")
	WebElement productTitle;

	private static final Logger lOGGER = LogManager.getLogger(RandomInputPage.class.getName());

	public RandomInputPage(WebDriver driver) {
		super(driver);
	}

	public String selectRandomInput() {

		int min = 10000;
		int max = 100000000;
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return Integer.toString((r.nextInt((max - min) + 1) + min));
	}

	public String selectRandomUsername() {

		randomUsers = new String[] { "dmech+alaskan@barcodesinc.com", "dmech+alphia@barcodesinc.com",
				"dmech+arrowhead@barcodesinc.com", "dmech+banner@barcodesinc.com", "dmech+barrett@barcodesinc.com",
				"dmech+becker@barcodesinc.com", "dmech+bergstrom@barcodesinc.com", "dmech+db@barcodesinc.com",
				"dmech+dominium@barcodesinc.com", "dmech+englewood@barcodesinc.com",
				"dmech+fashionforms@barcodesinc.com", "dmech+futurefoam@barcodesinc.com", "dmech+gicon@barcodesinc.com",
				"dmech+greco@barcodesinc.com", "dmech+greenworks@barcodesinc.com", "dmech+houston@barcodesinc.com",
				"dmech+ims@barcodesinc.com", "dmech+inwk@barcodesinc.com", "dmech+jfc@barcodesinc.com",
				"dmech+kaltire@barcodesinc.com", "dmech+landair@barcodesinc.com", "dmech+marvin@barcodesinc.com",
				"dmech+medspeed@barcodesinc.com", "dmech+meijer@barcodesinc.com", "dmech+menasha@barcodesinc.com",
				"dmech+newhudson@barcodesinc.com", "dmech+nordic@barcodesinc.com", "dmech+ocr@barcodesinc.com",
				"dmech+penske@barcodesinc.com", "dmech+purfoods@barcodesinc.com", "dmech+rakuten2@barcodesinc.com",
				"dmech+rawlings@barcodesinc.com", "dmech+ruan@barcodesinc.com", "dmech+sanford@barcodesinc.com",
				"dmech+satellite@barcodesinc.com", "dmech+sheetz1@barcodesinc.com", "dmech+siemens@barcodesinc.com",
				"dmech+siemens1@barcodesinc.com", "dmech+snapon@barcodesinc.com", "dmech+ts@barcodesinc.com",
				"dmech+usexpediters@barcodesinc.com", "dmech+veritiv@ocr.ca", "dmech+wineshipping@barcodesinc.com",
				"dmech+worley@barcodesinc.com", };
		int randomNumberIndex = r.nextInt(randomUsers.length);
		String randomUsername = randomUsers[randomNumberIndex];
		randomNumberIndex = r.nextInt(randomUsers.length);
		randomUsername = randomUsers[randomNumberIndex];
		System.out.println("The Username selected is :------" + randomUsername);

		return randomUsername;
	}

	public String fetchRandomURL(String sheetName) {

		List<String> url = new ArrayList<String>();
		int row = reader.getRowCount(sheetName);
		for (int i = 0; i < row; i++) {
			url.add(reader.getCellData(sheetName, "URL", i));
		}

		int randomURL = random.nextInt(url.size());
		System.out.println(url.get(randomURL));
		driver.navigate().to(url.get(randomURL));
		lOGGER.info("Fetching the random URL from the list from the excel sheet");
		return url.get(randomURL);
	}

	public void verifyBanner(String sheetName, String expected) {

		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);
		lOGGER.info("Verifying the current URL with the fetched URL from the sheet");

		if (sheetName == "AirTrackBanner") {
			Assert.assertTrue(airTrackBanner.isDisplayed());
			lOGGER.info("Verifying whether the banner is present in the page or not");

			wait.forElementToBeClickable(airTrackBanner);
			click(airTrackBanner);
			lOGGER.info("Verifying whether the banner is clickable or not");

			int rowNum = reader.getCellRowNum(sheetName, "URL", expected);
			String expectedURL = reader.getCellData(sheetName, "Banner Re-direct", rowNum);
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
			lOGGER.info("Verifying when clicked on banner redirects correctly or not");
		} else {

			System.out.println(bannerText.getText());
			Assert.assertTrue(bannerText.getText().contains("Try Zebra Compatible"));
			lOGGER.info("Verifying whether the text in banner is as expected or not");

			wait.forElementToBeClickable(airTrackBanner);
			click(airTrackBanner);
			lOGGER.info("Verifying whether the banner is clickable or not");

			int rowNum = reader.getCellRowNum(sheetName, "URL", expected);
			String expectedURL = reader.getCellData(sheetName, "Banner Re-direct", rowNum);
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
			lOGGER.info("Verifying when clicked on banner redirects correctly or not");
		}
	}

	public void verifyTopNavContainers(String sheetName, String expected) {

		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual);
		lOGGER.info("Verifying the current URL with the fetched URL from the sheet");

		int rowNum = reader.getCellRowNum(sheetName, "URL", expected);
		String expectedProduct = reader.getCellData(sheetName, "Title", rowNum);
		String actualProduct = productTitle.getText();
		Assert.assertEquals(actualProduct, expectedProduct);
		lOGGER.info("Verifying when clicked on banner redirects correctly or not");
	}
}
package Pages;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class HomePage extends BasePage {

	Random r = new Random();
	String pageTitle = driver.getTitle();
	Actions action = new Actions(driver);

	@FindBy(xpath = "//a[@class='logo']")
	public static WebElement barcodesLogo;
	
	@FindBy(xpath = "//div[@class='promo_banner_html promo_nbr_1']")
	WebElement promoBanner1;

	@FindBy(xpath = "//div[@class='promo_banner_html promo_nbr_2']")
	WebElement promoBanner2;

	@FindBy(xpath = "//div[@class='breadcrumbs']//strong")
	WebElement pageHeader;

	@FindBy(xpath = "//a[@class='logo']//following-sibling::div[@class='search-area']//input[@type='text']")
	WebElement searchField;

	@FindBy(xpath = "//form[@id='search_mini_form']//input[@id='search']")
	WebElement storeSearchField;

	@FindBy(xpath = "//a[@class='logo']//following-sibling::div[@class='search-area']//input[@type='submit']")
	WebElement searchButton;

	@FindBy(xpath = "//form[@id='search_mini_form']//button[@type='submit']")
	WebElement storeSearchButton;

	@FindBy(xpath = "//span[contains(text(),'my account')]")
	public WebElement myAccount;

	@FindBy(xpath = "//a[text()='account dashboard']")
	public WebElement accountDashboard;

	@FindBy(xpath = "//a[text()='my orders']")
	public WebElement myOrders;

	@FindBy(xpath = "(//span[contains(text(),'TRUE') or contains(text(),'True')])[1]")
	public WebElement trueView;

	@FindBy(xpath = "//a[text()='Create Ticket']")
	public WebElement createTicket;

	@FindBy(xpath = "//a[text()='My Tickets']")
	public WebElement myTickets;

	@FindBy(xpath = "//a[text()='My Assets']")
	public WebElement myAssets;

	@FindBy(xpath = "//a[text()='My Contracts']")
	public WebElement myContracts;

	@FindBy(xpath = "//a[text()='Report Dashboard']")
	public WebElement reportDashboard;

	@FindBy(xpath = "//button[@name='agree']")
	public WebElement agreeButton;

	@FindBy(xpath = "//span[text()='Labels &']")
	public WebElement labelsAndSuppliers;

	@FindBy(xpath = "//span[text()='Barcode Labels']")
	public WebElement barcodesLabels;

	@FindBy(xpath = "//ul[@class='top-nav']//li//a[text()='Mobile ']")
	public WebElement mobileComputing;

	@FindBy(xpath = "(//li//span[text()='POS']//ancestor::ul[@class='top-nav']//ul[@class='sub-sub-nav triple'])[2]")
	public WebElement mobileComputingSize;

	@FindBy(xpath = "//li//span[text()='Barcode']")
	public WebElement barcodeScanning;

	@FindBy(xpath = "(//li//span[text()='POS']//ancestor::ul[@class='top-nav']//ul[@class='sub-sub-nav triple'])[3]")
	public WebElement barcodeScanningSize;

	@FindBy(xpath = "//li//a[text()='Computing & Tablets']")
	public WebElement computingTablets;

	@FindBy(xpath = "//a[text()='Mobile Handheld Computer']")
	public WebElement mobileHandheldComputers;

	@FindBy(xpath = "//li[@class='barcode-printers-nav']")
	public WebElement barcodePrinting;

	@FindBy(xpath = "//li//span[text()='Desktop Printers']")
	public WebElement desktopPrinters;

	@FindBy(xpath = "//li//span[text()='POS']")
	public WebElement POS;

	@FindBy(xpath = "(//li//span[text()='POS']//ancestor::ul[@class='top-nav']//ul[@class='sub-sub-nav triple'])[6]")
	public WebElement POSSize;

	@FindBy(xpath = "//span[text()='Items in']")
	public WebElement itemsInCart;

	@FindBy(xpath = "//a[text()='View Cart ']")
	public WebElement cart;

	@FindBy(xpath = "//div[@id='global-nav']//a[text()='My Products']")
	public WebElement myProducts;

	@FindBy(xpath = "//input[@alt='Add to Cart']")
	public List<WebElement> products;

	@FindBy(xpath = "//button[@id='finish_and_checkout']")
	public WebElement goToCart;

	private static final Logger lOGGER = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnMyAssetsAndServices() {

//		if (pageTitle.contains("To access the Assets & Services portal")) {
//
//			wait.forElementToBeVisible(agreeButton);
//			js.clickElement(agreeButton);
//			lOGGER.info("clicked on terms & conditions Agree button");
//		}
//
//		wait.forElementToBeVisible(myAssets);
//		click(myAssets);
//		lOGGER.info("clicked on My Assets & Services Button");
//
//		if (pageTitle.contains("To access the Assets & Services portal")) {
//
//			wait.forElementToBeVisible(agreeButton);
//			js.clickElement(agreeButton);
//			lOGGER.info("clicked on terms & conditions Agree button");

		wait.forPage(3000);
		try {
			if (pageTitle.contains("To access the Assets & Services portal")) {

				wait.forElementToBeVisible(agreeButton);
				js.clickElement(agreeButton);
				lOGGER.info("clicked on terms & conditions Agree button");
			}

			wait.forElementToBeVisible(trueView);
			action.moveToElement(trueView).perform();
			js.clickElement(reportDashboard);
			lOGGER.info("clicked on report dashboard Button");

		} catch (TimeoutException f) {
			wait.forElementToBeVisible(myAssets);
			wait.forElementToBeClickable(myAssets);
			js.clickElement(myAssets);
			lOGGER.info("clicked on My Assets & Services Button");
		}

		if (pageTitle.contains("To access the Assets & Services portal")) {

			wait.forElementToBeVisible(agreeButton);
			js.clickElement(agreeButton);
			lOGGER.info("clicked on terms & conditions Agree button");
		}
	}

	public void verifyServicePortal() {

		try {
			wait.forElementToBeVisible(trueView);
			boolean actual = trueView.isDisplayed();
			Assert.assertTrue(actual);
			lOGGER.info("Verifying the presence of True View Button");
		} catch (Exception e) {

			wait.forElementToBeVisible(myAssets);
			boolean actual = myAssets.isDisplayed();
			Assert.assertTrue(actual);
			lOGGER.info("Verifying the presence of My Assets & Services Button");
		}
	}

	public void selectMobileScanner() {

		wait.forPage();
		wait.forElementToBeVisible(mobileComputing);
		action.moveToElement(mobileComputing).perform();
		js.clickElement(computingTablets);
		lOGGER.info("clicked on computing tablets from mobile computing dropdown");
		wait.forElementToBeVisible(mobileHandheldComputers);
		js.clickElement(mobileHandheldComputers);
		lOGGER.info("clicked on mobile handheld computers from computing&Tablets page");
	}

	public void selectDesktopPrinter() {

		wait.forPage();
		wait.forElementToBeVisible(barcodePrinting);
		action.moveToElement(barcodePrinting).perform();
		js.clickElement(desktopPrinters);
		lOGGER.info("clicked on barcode printing from desktop printers dropdown");
	}

	public void verifyTrueViewContents() {

		wait.forElementToBeVisible(trueView);
		action.moveToElement(trueView).perform();
		wait.forElementToBeVisible(createTicket);
		Assert.assertTrue(createTicket.isDisplayed());
		wait.forElementToBeVisible(reportDashboard);
		Assert.assertTrue(reportDashboard.isDisplayed());
		wait.forElementToBeVisible(myTickets);
		Assert.assertTrue(myTickets.isDisplayed());
		wait.forElementToBeVisible(myAssets);
		Assert.assertTrue(myAssets.isDisplayed());
		wait.forElementToBeVisible(myContracts);
		Assert.assertTrue(myContracts.isDisplayed());
		lOGGER.info("Verifying all the contents presence under true view drop down");
	}

	public void verifyLink(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		driver.navigate().back();
	}

	public void verifyTrueViewStyle() {

		wait.forElementToBeVisible(trueView);
		Assert.assertTrue(trueView.getText().equals("TrueView"));
	}

	public void searchProduct() {

		wait.forPage();
		if (driver.getCurrentUrl().equals("https://shop.ocr.ca/store/")) {
			wait.forElementToBeVisible(storeSearchField);
			sendKeys(storeSearchField, "mc9300");
			wait.forElementToBeVisible(storeSearchButton);
			click(storeSearchButton);
		} else {
			wait.forElementToBeVisible(searchField);
			sendKeys(searchField, "mc9300");
			wait.forElementToBeVisible(searchButton);
			click(searchButton);
		}
		lOGGER.info("Entering the product to be searched in the serch field");
	}

	public void selectLabelsAndSuppliers() {

		wait.forPage();
		wait.forElementToBeVisible(labelsAndSuppliers);
		action.moveToElement(labelsAndSuppliers).perform();
		js.clickElement(barcodesLabels);
		lOGGER.info("clicked on barcodes labels from labels and suppliers dropdown");
	}

	public void selectViewCart() {

		wait.forPage();
		try {
			wait.forElementToBeVisible(itemsInCart);
			action.moveToElement(itemsInCart).doubleClick().perform();
			lOGGER.info("Clicking on cart to view the items in it");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void verifyPromoBanner(Map<String, String> selectedBanner) {

		for (String key : selectedBanner.keySet()) {
			if (selectedBanner.get(key).contains("Yes")) {
				if (key.contains("1")) {
					wait.forElementToBeVisible(promoBanner1);
					Assert.assertTrue(promoBanner1.isDisplayed());
				} else {
					wait.forElementToBeVisible(promoBanner2);
					Assert.assertTrue(promoBanner2.isDisplayed());
				}
			}
		}

//		try {
//			wait.forElementToBeVisible(promoBanner);
//			Assert.assertTrue(promoBanner.isDisplayed());
//		} catch (TimeoutException e) {
//			System.out.println("Please make sure that Promo banner has been enabled from Magento side");
//		}
	}

	public void clickOnMyProducts() {

		wait.forElementToBeVisible(myProducts);
		js.clickElement(myProducts);
		lOGGER.info("Clicking on my products to view the products in it for purchasing");
	}

	public void clickOnAccountDashboard() {

		wait.forElementToBeVisible(myAccount);
		action.moveToElement(myAccount).perform();
		js.clickElement(accountDashboard);
		lOGGER.info("clicked on account dashboard Button");
	}

	public void clickOnMyOrders() {

		wait.forElementToBeVisible(myAccount);
		action.moveToElement(myAccount).perform();
		js.clickElement(myOrders);
		lOGGER.info("clicked on my orders Button");
	}

	public void addRandomProductToCart() {

		wait.forPage();
		int randomIndex = r.nextInt(products.size());
		scrollToElementView(products.get(3));
		click(products.get(3));
		wait.forPage();
		wait.forElementToBeVisible(goToCart);
		click(goToCart);
	}

	public void getSize() {

		wait.forElementToBeVisible(barcodeScanning);
		action.moveToElement(barcodeScanning).perform();
		wait.forElementToBeVisible(barcodeScanningSize);
		Dimension barcodeSize = barcodeScanningSize.getSize();
		System.out.println(barcodeSize.height);

		wait.forElementToBeVisible(mobileComputing);
		action.moveToElement(mobileComputing).perform();
		wait.forElementToBeVisible(mobileComputingSize);
		Dimension mobileSize = mobileComputingSize.getSize();
		System.out.println(mobileSize.height);

		wait.forElementToBeVisible(POS);
		action.moveToElement(POS).perform();
		wait.forElementToBeVisible(POSSize);
		Dimension POSsize = POSSize.getSize();
		System.out.println(POSsize.height);
		Assert.assertTrue(POSsize.height > mobileSize.height);
		Assert.assertTrue(POSsize.height > barcodeSize.height);
	}
}
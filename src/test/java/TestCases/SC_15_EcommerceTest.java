package TestCases;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.DiscountPage;
import Pages.GhostInspectorPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MagentoLoginPage;
import Pages.MagentoPage;
import Pages.MyAccountPage;
import Pages.MyProductsPage;
import Pages.OrdersPage;
import Pages.ProductsPage;
import Pages.RandomInputPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_15_EcommerceTest extends TestBase {

	String sheetName1 = "AirTrackBanner";
	String sheetName2 = "ZebraConsumbalesBanner";
	String sheetName3 = "Scanners";
	String sheetName4 = "MobileComputers";
	String mailURL = "https://email.ghostinspector.com/";
	String protocol = "https://";

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
	public void TC_01_ECO_I962_SearchValidation() {

		driver.get(InitializePropertyFile.property.getProperty("BarcodesCannada_URL"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.searchProduct();
		ProductsPage products = new ProductsPage(driver);
		products.verifyProduct();

		driver.navigate().to("https://shop.ocr.ca/store/");
		homepage.searchProduct();
		products.verifyProduct();
	}

	@Test(priority = 2)
	public void TC_02_ECO_I969_LabelsPageValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.selectLabelsAndSuppliers();
		ProductsPage product = new ProductsPage(driver);
		product.verifyLabelPage();
	}

	@Test(priority = 3)
	public void TC_03_ECO_I840_shippingAddressValidation() {

		driver.navigate().to(InitializePropertyFile.property.getProperty("Magento_URL"));
		MagentoLoginPage magentoLoginpage = new MagentoLoginPage(driver);
		magentoLoginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("Magento_Username"),
				InitializePropertyFile.property.getProperty("Magento_Password"));
		MagentoPage magento = new MagentoPage(driver);
		boolean flag = magento.editCustomerGroup();
		driver.navigate().to(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.selectViewCart();
		CartPage cart = new CartPage(driver);
		cart.clickOnCheckout();
		cart.verifyShippingAddress(flag);
		driver.navigate().to(InitializePropertyFile.property.getProperty("Magento_URL"));
		magento.editCustomerGroup();
	}

	@Test(priority = 4)
	public void TC_04_ECO_I890_promoBannerValidation() {

		driver.navigate().to(InitializePropertyFile.property.getProperty("Magento_URL"));
		MagentoLoginPage magentoLoginpage = new MagentoLoginPage(driver);
		magentoLoginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("Magento_Username"),
				InitializePropertyFile.property.getProperty("Magento_Password"));
		MagentoPage magento = new MagentoPage(driver);
		Map<String, String> selectedBanner = magento.editPromoBanner();
		driver.navigate().to(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.verifyPromoBanner(selectedBanner);
	}

	@Test(priority = 5)
	public void TC_05_ECO_I881_requestOrderApprovalValidation() {

		driver.get(InitializePropertyFile.property.getProperty("BarcodesCannada_URL"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure("dmech+veritiv@ocr.ca", InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.selectViewCart();
		CartPage cart = new CartPage(driver);
		cart.removeAllItems();
		HomePage.click(HomePage.barcodesLogo);
		homepage.clickOnMyProducts();
		MyProductsPage myProducts = new MyProductsPage(driver);
		myProducts.selectProduct();
		cart.clickOnCheckout();
		cart.verifyPaymentMethods();

//			driver.navigate().to(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
//			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
//					InitializePropertyFile.property.getProperty("password"));
//			homepage.selectViewCart();
//			cart.removeAllItems();
//			HomePage.click(HomePage.barcodesLogo);
//			homepage.clickOnMyProducts();
//			myProducts.selectProduct();
//			cart.clickOnCheckout();
//			cart.verifyPaymentMethods();
	}

	@Test(priority = 6)
	public void TC_06_ECO_I960_MyOrdersUpdatesValidation() {

		LoginPage login = new LoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage home = new HomePage(driver);
		home.clickOnAccountDashboard();
		MyAccountPage accounts = new MyAccountPage(driver);
		accounts.verifyContentsOfOrders();
		accounts.verifyBackgroundColor();
		OrdersPage orders = new OrdersPage(driver);
		orders.verifyOrderDetailsPageTitle();

		home.clickOnMyOrders();
		orders.verifyTotalProductsOnPage();
		orders.verifyOrdersSeperation();
		orders.verifyOrders();
		orders.verifyReorderButton();
		orders.verifyOrderDetailsPage();
	}

//	@Test(priority = 7)
	public void TC_07_ECO_I935_AutomaticNewAccountCreationValidation() {

		HomePage home = new HomePage(driver);
		GhostInspectorPage ghost = new GhostInspectorPage(driver);
		String randomEmail = ghost.generateRandomEmail();
		System.out.println(randomEmail);
		home.addRandomProductToCart();
		CartPage cart = new CartPage(driver);
		cart.clickOnCheckout();
		cart.enterShippingDetails(randomEmail);
		cart.proceedToCheckout();
		String orderID1 = cart.getOrderID();

		driver.navigate().to(InitializePropertyFile.property.getProperty("MagentoSandbox_URL"));
		MagentoLoginPage magentoLoginpage = new MagentoLoginPage(driver);
		magentoLoginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("MagentoSandbox_Username"),
				InitializePropertyFile.property.getProperty("MagentoSandbox_Password"));
		MagentoPage magento = new MagentoPage(driver);
		magento.verifyCustomerUpdate(randomEmail);
		magento.verifyOrderID(orderID1);

		driver.navigate().to(InitializePropertyFile.property.getProperty("Sandbox_URL"));
		home.addRandomProductToCart();
		cart.clickOnCheckout();
		cart.enterShippingDetails(randomEmail);
		cart.proceedToCheckout();
		String orderID2 = cart.getOrderID();
		String passwordStrength = cart.getPasswordStatus();

		driver.navigate().to(InitializePropertyFile.property.getProperty("MagentoSandbox_URL"));
		magento.verifyCustomerUpdate(randomEmail);
		magento.verifyOrderID(orderID2);
		String flag = magento.verifyAccountActivation(passwordStrength);
		System.out.println(flag);

		if (flag.equalsIgnoreCase("Yes")) {
			driver.navigate().to(InitializePropertyFile.property.getProperty("Sandbox_URL"));
			LoginPage login = new LoginPage(driver);
			login.userLoginProcedure(randomEmail, login.genRandomString());
			login.verifyEmailVerifcation(randomEmail);
			mailURL = mailURL.concat(randomEmail);
			driver.navigate().to(mailURL);
			ghost.verifyPasswordResendMail();
			ghost.verifyNewPasswordPage();
		}
		if (flag.equalsIgnoreCase("Yes")) {
			driver.navigate().to(InitializePropertyFile.property.getProperty("Sandbox_URL"));
			LoginPage login = new LoginPage(driver);
			login.newAccountProcedure(randomEmail, login.genRandomString());
			login.verifyEmailVerifcation(randomEmail);
			driver.navigate().to(mailURL);
			ghost.verifyPasswordResendMail();
			ghost.verifyNewPasswordPage();
		}
	}

	@Test(priority = 8)
	public void TC_08_ECO_I753_calloutBannerOnZebraPagesValidation() {

		RandomInputPage random = new RandomInputPage(driver);
		String url = random.fetchRandomURL(sheetName1);
		random.verifyBanner(sheetName1, url);
	}

	@Test(priority = 9)
	public void TC_09_ECO_I811_addAirtrackBannersConsumablesValidation() {

		RandomInputPage random = new RandomInputPage(driver);
		String url = random.fetchRandomURL(sheetName2);
		random.verifyBanner(sheetName2, url);
	}

	@Test(priority = 10)
	public void TC_10_ECO_I974_accountDashboardMyOrdersValidation() {

//			RandomInputPage random = new RandomInputPage(driver);
//			String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure("jamey.r.koehler@cub.com",
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnAccountDashboard();
		MyAccountPage accounts = new MyAccountPage(driver);
		for (int i = 0; i < 5; i++) {
			ArrayList<String> orderDetails = new ArrayList<String>();
			orderDetails = accounts.getOrderIDDetails(i);
			System.out.println(orderDetails);
			homepage.clickOnMyOrders();
			accounts.verifyOrderDetails(orderDetails);
			homepage.clickOnAccountDashboard();
		}
	}

	@Test(priority = 11)
	public void TC_11_ECO_I876_topNavigationChangesValidation() {

		driver.get(InitializePropertyFile.property.getProperty("BarcodesCannada_URL"));
		RandomInputPage random = new RandomInputPage(driver);
		String url1 = random.fetchRandomURL(sheetName3);
		random.verifyTopNavContainers(sheetName3, url1);

		String url2 = random.fetchRandomURL(sheetName4);
		random.verifyTopNavContainers(sheetName4, url2);

		driver.navigate().to(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		HomePage home = new HomePage(driver);
		home.getSize();
	}

	@Test(priority = 12)
	public void TC_12_ECO_I810_searchspringIntegrationValidation() throws AWTException {

		driver.navigate().to(InitializePropertyFile.property.getProperty("BarcodesDiscount_URL"));
		DiscountPage discount = new DiscountPage(driver);
//			discount.verifyBreadcrumbs();
		discount.verifyPageRedirect();
		discount.verifySearchSuggestions();
		discount.verifyProductsInCategories();
		discount.verifyPriceAndAddToCart();
	}
}
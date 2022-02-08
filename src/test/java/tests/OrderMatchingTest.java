package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class OrderMatchingTest extends PageFactoryInitializer {

	protected static Map<String, String> orderDetails = new HashMap<String, String>();
	protected static String USDcBalance;
	protected static String lockedAmount;

	@BeforeMethod
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();
	}

	@Test(priority = 1)
	public void C2376_buyMarketOrderMatchingValidation() throws Exception {

//		ExplicitWait.explicitWaitVisibilityOfElement(assetsPage().lockedAmount, WAIT_20_SEC);
//		homePage().clickOnOrders();
//		ordersPage().verifyFilters();
//		ordersPage().verifyDate();

		homePage().click(homePage().faviconAssets);
		USDcBalance = assetsPage().getUSDcBalance();
		lockedAmount = assetsPage().getLockedAmount();
		homePage().click(homePage().faviconTrade);
		tradePage().selectAdvancedTrading();
		orderDetails = tradePage().buyOrderProcessForMarket();
		System.out.println(orderDetails);
		homePage().clickOnOrders();

		ordersPage().verifyMarketOrder(orderDetails);
		ordersPage().verifyUSDcForBuyMarket(USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyFilters();
		ordersPage().verifyDate();
		ordersPage().verifyCostRemaining(lockedAmount, USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyPagination();
	}
	
	@Test(priority = 2)
	public void C2376_sellMarketOrderMatchingValidation() throws Exception {

//		ExplicitWait.explicitWaitVisibilityOfElement(assetsPage().lockedAmount, WAIT_20_SEC);
//		homePage().clickOnOrders();
//		ordersPage().verifyFilters();
//		ordersPage().verifyDate();

		homePage().click(homePage().faviconAssets);
		USDcBalance = assetsPage().getUSDcBalance();
		lockedAmount = assetsPage().getLockedAmount();
		homePage().click(homePage().faviconTrade);
		tradePage().selectAdvancedTrading();
		orderDetails = tradePage().sellOrderProcessForMarket();
		System.out.println(orderDetails);
		homePage().clickOnOrders();

		ordersPage().verifyMarketOrder(orderDetails);
		ordersPage().verifyUSDcForSellMarket(USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyFilters();
		ordersPage().verifyDate();
		ordersPage().verifyCostRemaining(lockedAmount, USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyPagination();
	}

//	@Test(priority = 3)
	public void C2377_buyLimitOrderMatchingValidation() {

		homePage().click(homePage().faviconAssets);
		USDcBalance = assetsPage().getUSDcBalance();
		lockedAmount = assetsPage().getLockedAmount();
		homePage().click(homePage().faviconTrade);
		tradePage().selectAdvancedTrading();
		orderDetails = tradePage().buyOrderProcess();
		System.out.println(orderDetails);
		homePage().clickOnOrders();

		ordersPage().verifyLimitOrder(orderDetails);
		ordersPage().verifyUSDcForBuyLimit(USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyFilters();
		ordersPage().verifyDate();
		ordersPage().verifyCostRemaining(lockedAmount, USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyPagination();
	}

//	@Test(priority = 4)
	public void C2377_sellLimitOrderMatchingValidation() {

		homePage().click(homePage().faviconAssets);
		USDcBalance = assetsPage().getUSDcBalance();
		homePage().click(homePage().faviconTrade);
		tradePage().selectAdvancedTrading();
		orderDetails = tradePage().sellOrderProcess();
		System.out.println(orderDetails);
		homePage().clickOnOrders();

		ordersPage().verifyLimitOrder(orderDetails);
		ordersPage().verifyUSDcForSellLimit(USDcBalance);
		homePage().clickOnOrders();
		ordersPage().verifyPagination();

		ordersPage().verifyFilters();
		ordersPage().verifyDate();
		ordersPage().verifyCostRemaining(lockedAmount, USDcBalance);
	}

//	@Test(priority = 5)
	public void C2378_limitPartialOrderMatchingValidation() {

	}
}
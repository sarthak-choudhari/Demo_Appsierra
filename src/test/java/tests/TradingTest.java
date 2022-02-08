package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class TradingTest extends PageFactoryInitializer {

	@BeforeMethod
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();

	}

	@Test(priority = 1)
	public void C2413_marketTickerValidation() throws Exception {

		homePage().clickOnTrade();
		homePage().selectAdvancedTrade();
		tradePage().searchForRandomCTI();
		tradePage().verifySorting();
//		tradePage().verifyMouseHover();
		tradePage().verifyCTIClick();
	}

	@Test(priority = 2)
	public void C2414_orderBookValidation() {

		homePage().clickOnTrade();
		homePage().selectAdvancedTrade();
		tradePage().verifySellingCreds();
		tradePage().verifyBuyingCreds();
		tradePage().verifyMarketPrice();
	}

	@Test(priority = 3)
	public void C2416_openOrdersValidation() {
        
		homePage().clickOnTrade();
		homePage().selectAdvancedTrade();
		
		tradePage().verifyOpenOrders();
		tradePage().opendOrderCancel();
		//tradePage().verifyRecentPriceAndLastMarketPrice();
	}

	@Test(priority = 4)
	public void C2417_recentTradesValidation() {

		homePage().clickOnTrade();
		homePage().selectAdvancedTrade();
		tradePage().selectRandomCTI();
		tradePage().selectAdvancedTrading();
		tradePage().verifyRecentTrades();
	}
	
	@Test(priority = 5)
	public void C2418_tradingChartValidation() {

		homePage().clickOnTrade();
		homePage().selectSimpleTrade();
		tradePage().selectRandomCTI();
		int randomMode=tradePage().verifyChartAttributes();
		tradePage().verifyGraph(randomMode);
	}
}
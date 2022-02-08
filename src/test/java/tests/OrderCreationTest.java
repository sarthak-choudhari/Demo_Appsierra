package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class OrderCreationTest extends PageFactoryInitializer {

	@BeforeMethod
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();
	}

	@Test(priority = 1)
	public void C2372_createBuyLimitOrderInputingQuantityValidation() {

		String intialLockedAmount = assetsPage().getLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().selectRandomCTI();
		int randomNumberIndex = tradePage().verifyBuyingPrice();
		String randomQuantity = tradePage().selectRandomQuantityForLimit(randomNumberIndex);
		tradePage().clickOnBuyButton();
		tradePage().verifyOrderCreationForRandomQuantity(randomQuantity);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForBuyOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyLockedAmount(expected);
	}

	@Test(priority = 2)
	public void C2373_createBuyLimitOrderInputingUSDcValidation() {

		String intialLockedAmount = assetsPage().getLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().selectRandomCTI();
		tradePage().verifyBuyingPrice();
		String randomAmount = tradePage().selectRandomAmountForLimit();
		tradePage().clickOnBuyButton();
		tradePage().verifyOrderCreationForRandomAmount(randomAmount);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForBuyOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyLockedAmount(expected);
	}

	@Test(priority = 3)
	public void C2374_createBuyMarketOrderInputingQuantityValidation() {

		String intialLockedAmount = assetsPage().getLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().selectRandomCTI();
		tradePage().selectMarketOrderType();
		tradePage().selectRandomQuantityForMarket();
		tradePage().clickOnBuyButton();
//		tradePage().verifyOrderCreationForRandomQuantity(randomQuantity);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForBuyOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyLockedAmount(expected);
	}

//	@Test(priority = 4)
	public void C2375_createBuyMarketOrderInputingUSDcValidation() {

		String intialLockedAmount = assetsPage().getLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().selectRandomCTI();
		tradePage().selectMarketOrderType();
		tradePage().selectRandomAmountForMarket();
		tradePage().clickOnBuyButton();
//		tradePage().verifyOrderCreationForRandomAmount(randomAmount);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForBuyOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyLockedAmount(expected);
	}

	@Test(priority = 5)
	public void C2380_createSellLimitOrderInputingQuantityValidation() {

		String intialLockedAmount = assetsPage().centralisedExchangesLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		int randomNumberIndex = tradePage().verifySellingPrice();
		tradePage().selectSellOrderType();
		String randomQuantity = tradePage().selectRandomQuantityForLimit(randomNumberIndex);
		tradePage().clickOnSellButton();
		tradePage().verifyOrderCreationForRandomQuantity(randomQuantity);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForSellOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyCTILockedAmount(expected);
	}

	@Test(priority = 6)
	public void C2381_createSellLimitOrderInputingUSDcValidation() {
		String intialLockedAmount = assetsPage().centralisedExchangesLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().verifySellingPrice();
		tradePage().selectSellOrderType();
		String randomAmount = tradePage().selectRandomAmountForLimit();
		tradePage().clickOnSellButton();
		tradePage().verifyOrderCreationForsell();
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForSellOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyCTILockedAmount(expected);
	}

//	@Test(priority = 7)
	public void C2382_createSellMarketOrderInputingQuantityValidation() {

		String intialLockedAmount = assetsPage().getLockedAmount();
		homePage().clickOnTrade();
		tradePage().selectAdvancedTrading();
		tradePage().selectRandomCTI();
		tradePage().selectSellOrderType();
		tradePage().selectMarketOrderType();
		tradePage().selectRandomQuantityForMarket();
		tradePage().clickOnSellButton();
//		tradePage().verifyOrderCreationForRandomQuantity(randomQuantity);
		homePage().clickOnOrders();
		String expected = ordersPage().verifyTotalAmountForBuyOpenedOrders(intialLockedAmount);
		homePage().click(homePage().faviconAssets);
		assetsPage().verifyLockedAmount(expected);
	}

//	@Test(priority = 8)
	public void C2383_createSellMarketOrderInputingUSDcValidation() {

		homePage().click(homePage().faviconTrade);
		tradePage().selectRandomCTI();
		tradePage().selectAdvancedTrading();
		tradePage().selectSellOrderType();
		tradePage().selectMarketOrderType();
		String randomAmount = tradePage().selectRandomAmountForMarket();
//		tradePage().clickOnSellButton();
//		tradePage().verifyOrderCreationForRandomAmount(randomAmount);
//		homePage().clickOnOrders();
//		String expected = ordersPage().verifyTotalAmountForOpenedOrders();
//		homePage().click(homePage().faviconAssets);
//		assetsPage().verifyLockedAmount(expected);
	}
}
package pageObjects.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class OrdersPage extends PageFactoryInitializer {

	Random r = new Random();
	double totalLockedAmount;

	@FindBy(xpath = "//tbody//td[9]//span[@class='pg-history-elem-opened']")
	public List<WebElement> openedOrder;

	@FindBy(xpath = "//tbody//td[8]")
	public List<WebElement> costRemaining;

	@FindBy(xpath = "//div//label[text()='Order type']//parent::div//button")
	public WebElement orderType;

	@FindBy(xpath = "//div//label[text()='Order type']//parent::div//a[@class='dropdown-item']")
	public List<WebElement> orderTypeDropdownList;

	@FindBy(xpath = "//div//label[text()='Indices']//parent::div//button")
	public WebElement indices;

	@FindBy(xpath = "//div//label[text()='Indices']//parent::div//a[@class='dropdown-item']")
	public List<WebElement> indicesDropdownList;

	@FindBy(xpath = "//div//label[text()='Status']//parent::div//button")
	public WebElement status;

	@FindBy(xpath = "//div//label[text()='Status']//parent::div//a[@class='dropdown-item']")
	public List<WebElement> statusDropdownList;

	@FindBy(xpath = "//a[text()='All']")
	public List<WebElement> all;

	@FindBy(xpath = "//button[@class='cancel-all btn btn-outline-secondary']")
	public WebElement cancelAll;

	@FindBy(xpath = "//table//td[1]")
	public List<WebElement> dateInTable;

	@FindBy(xpath = "//table//td[2]//span")
	public List<WebElement> orderTypeInTable;

	@FindBy(xpath = "//table//td[3]")
	public List<WebElement> CTIsInTable;

	@FindBy(xpath = "//table//td[4]")
	public List<WebElement> priceInTable;

	@FindBy(xpath = "//table//td[5]")
	public List<WebElement> totalQuantityInTable;

	@FindBy(xpath = "//table//td[6]")
	public List<WebElement> executedQuantityInTable;

	@FindBy(xpath = "//table//td[7]")
	public List<WebElement> remainingQuantityInTable;

	@FindBy(xpath = "//table//td[8]")
	public List<WebElement> costRemainingInTable;

	@FindBy(xpath = "//table//td[9]//span//span")
	public List<WebElement> statusInTable;

	@FindBy(xpath = "//div[@class='pg-history-elem__pagination']//p")
	public WebElement pages;

	@FindBy(xpath = "//button[@class='pg-history__pagination-next']")
	public WebElement nextPage;

	@FindBy(xpath = "//p[@class='pg-history-elem__empty']")
	public WebElement emptyTable;

	private static final Logger lOGGER = LogManager.getLogger(OrdersPage.class.getName());

	public String verifyTotalAmountForBuyOpenedOrders(String intialLockedAmount) {
          //  System.out.println(openedOrder.size());
		for (int i = 0; i < openedOrder.size(); i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(openedOrder.get(i), WAIT_10_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(costRemaining.get(i), WAIT_10_SEC);
			if (openedOrder.get(i).isDisplayed() == true && orderTypeInTable.get(i).getText().contains("Buy")) {
				totalLockedAmount = Double.parseDouble(intialLockedAmount)
						+ Double.parseDouble(costRemaining.get(i).getText());
			}
			else
				totalLockedAmount = Double.parseDouble(intialLockedAmount);
			break;
			}
		System.out.println("-------> " + totalLockedAmount);
		lOGGER.info("Calculating total locked amount for the opened orders");
		return Double.toString(totalLockedAmount);
	}
	public String verifyTotalAmountForSellOpenedOrders(String intialLockedAmount) {
        //  System.out.println(openedOrder.size());
		for (int i = 0; i < openedOrder.size(); i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(openedOrder.get(i), WAIT_10_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(costRemaining.get(i), WAIT_10_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(orderTypeInTable.get(i), WAIT_10_SEC);
			if (openedOrder.get(i).isDisplayed() == true && orderTypeInTable.get(i).getText().contains("Sell")) {
				totalLockedAmount = Double.parseDouble(intialLockedAmount)
						+ Double.parseDouble(costRemaining.get(i).getText());
			}
			else
				totalLockedAmount = Double.parseDouble(intialLockedAmount);
			break;
			}
		System.out.println("-------> " + totalLockedAmount);
		lOGGER.info("Calculating total locked amount for the opened orders");
		return Double.toString(totalLockedAmount);
	}

	public void verifyFilters() {

		verifyOrderTypeFilter();
		verifyStatusFilter();
	}

	public void verifyOrderTypeFilter() {

		ExplicitWait.explicitWaitVisibilityOfElement(orderType, WAIT_10_SEC);
		click(orderType);
		int randomFilter = r.nextInt(orderTypeDropdownList.size());
		ExplicitWait.explicitWaitVisibilityOfElement(orderTypeDropdownList.get(randomFilter), WAIT_10_SEC);
		String selectedFilter = orderTypeDropdownList.get(randomFilter).getText();
		click(orderTypeDropdownList.get(randomFilter));
		System.out.println("selected filter is " + selectedFilter);
		if (selectedFilter.equalsIgnoreCase("All")) {
			for (int i = 0; i < orderTypeInTable.size(); i++) {
				try {
					Assert.assertEquals(orderTypeInTable.get(i).getText(), "Sell limit");
				} catch (AssertionError e) {
					try {
						Assert.assertEquals(orderTypeInTable.get(i).getText(), "Buy limit");
					} catch (AssertionError f) {
						try {
							Assert.assertEquals(orderTypeInTable.get(i).getText(), "Buy market");
						} catch (AssertionError g) {
							Assert.assertEquals(orderTypeInTable.get(i).getText(), "Sell market");
						}
					}
				}
			}
		} else if (selectedFilter.equalsIgnoreCase("Sell limit")) {
			for (int i = 0; i < orderTypeInTable.size(); i++) {
				Assert.assertEquals(orderTypeInTable.get(i).getText(), "Sell limit");
			}
		} else if (selectedFilter.equalsIgnoreCase("Buy limit")) {
			for (int i = 0; i < orderTypeInTable.size(); i++) {
				Assert.assertEquals(orderTypeInTable.get(i).getText(), "Buy limit");
			}
		} else
			System.out.println("There are no orders to show");
//		wait.forElementToBeVisible(orderType);
//		click(orderType);
//		wait.forElementToBeVisible(all.get(0));
//		click(all.get(0));
		driver.navigate().refresh();
		lOGGER.info("Verifying the filters for order type");
	}

	public void verifyStatusFilter() {

		ExplicitWait.explicitWaitVisibilityOfElement(status, WAIT_10_SEC);
		click(status);
		int randomFilter = r.nextInt(statusDropdownList.size());
		ExplicitWait.explicitWaitVisibilityOfElement(statusDropdownList.get(randomFilter), WAIT_10_SEC);
		String selectedFilter = statusDropdownList.get(randomFilter).getText();
		click(statusDropdownList.get(randomFilter));
		System.out.println("selected filter is " + selectedFilter);
		if (selectedFilter.equalsIgnoreCase("All")) {
			for (int i = 0; i < statusInTable.size(); i++) {
				try {
					Assert.assertEquals(statusInTable.get(i).getText(), "Executed");
				} catch (AssertionError e) {
					try {
						Assert.assertEquals(statusInTable.get(i).getText(), "Open");
					} catch (AssertionError f) {
						Assert.assertEquals(statusInTable.get(i).getText(), "Stopped");
					}
				}
			}
		} else if (selectedFilter.equalsIgnoreCase("Open")) {
			for (int i = 0; i < statusInTable.size(); i++) {
				Assert.assertEquals(statusInTable.get(i).getText(), "Open");
			}
		} else if (selectedFilter.equalsIgnoreCase("Executed")) {
			for (int i = 0; i < statusInTable.size(); i++) {
				Assert.assertEquals(statusInTable.get(i).getText(), "Executed");
			}
		} else if (selectedFilter.equalsIgnoreCase("Canceled")) {
			for (int i = 0; i < statusInTable.size(); i++) {
				Assert.assertEquals(statusInTable.get(i).getText(), "Stopped");
			}
		} else
			System.out.println("There are no orders to show");
//		wait.forElementToBeVisible(status);
//		click(status);
//		wait.forElementToBeVisible(all.get(1));
//		click(all.get(1));
		driver.navigate().refresh();
		lOGGER.info("Verifying the filters for status type");
	}

	public void verifyCancelAll() {

		ExplicitWait.explicitWaitVisibilityOfElement(cancelAll, WAIT_10_SEC);
		click(cancelAll);
	}

	public void verifyDate() {

		driver.navigate().refresh();
		for (int i = 0; i < dateInTable.size() - 1; i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(dateInTable.get(i), WAIT_10_SEC);
			String date1 = dateInTable.get(i).getText();
			String date2 = dateInTable.get(i + 1).getText();

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Date parsedDate1 = dateFormat.parse(date1);
				Date parsedDate2 = dateFormat.parse(date2);
				int result = parsedDate1.compareTo(parsedDate2);
//				System.out.println(parsedDate1);
//				System.out.println(parsedDate2);
//				System.out.println(result);
				Assert.assertTrue(result >= 0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		lOGGER.info("Verifying the dates in proper order");
	}

//	public void verifyCostRemaining() {
//
//		double approxCost;
//		for (int i = 0; i < priceInTable.size(); i++) {
//			wait.forElementToBeVisible(priceInTable.get(i));
//			wait.forElementToBeVisible(remainingQuantityInTable.get(i));
//			wait.forElementToBeVisible(costRemainingInTable.get(i));
//			double price = Double.parseDouble(priceInTable.get(i).getText());
//			double quantity = Double.parseDouble(remainingQuantityInTable.get(i).getText());
//			double cost = Double.parseDouble(costRemainingInTable.get(i).getText());
//			String actualCost = Double.toString(quantity * price);
//			try {
//				approxCost = Double.parseDouble(actualCost.substring(0, actualCost.indexOf(".") + 3));
//			} catch (StringIndexOutOfBoundsException e) {
//				approxCost = Double.parseDouble(actualCost.substring(0, actualCost.indexOf(".") + 2));
//			}
//			Assert.assertEquals(cost, approxCost);
//		}
//		driver.navigate().refresh();
//	}

	public void verifyOrderType(String orderType) {

		ExplicitWait.explicitWaitVisibilityOfElement(orderTypeInTable.get(0), WAIT_30_SEC);
		String actual = orderTypeInTable.get(0).getText();
		actual = actual.substring(actual.indexOf(" ") + 1);
		String expected = orderType;
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
		lOGGER.info("Verifying the Order type present in Order tab with recently made order from trade tab");
	}

	public void verifyCTI(String CTI) {

		ExplicitWait.explicitWaitVisibilityOfElement(CTIsInTable.get(0), WAIT_30_SEC);
		String actual = CTIsInTable.get(0).getText();
		String expected = CTI;
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the CTI present in Order tab with recently made order from trade tab");
	}

	public void verifyPrice(String price) {

		ExplicitWait.explicitWaitVisibilityOfElement(priceInTable.get(0), WAIT_30_SEC);
		String actual = priceInTable.get(0).getText();
		String expected = price;
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the price present in Order tab with recently made order from trade tab");
	}

	public void verifyUSDcForBuyLimit(String USDcBalance) {

		ExplicitWait.explicitWaitVisibilityOfElement(costRemainingInTable.get(0), WAIT_10_SEC);
		Double buyPrice = Double.parseDouble(costRemainingInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getUSDcBalance();
		String expected = Double.toString((Double.parseDouble(USDcBalance)) - buyPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the Buy price deducted from the USDc");
	}

	public void verifyUSDcForBuyMarket(String USDcBalance) {

		ExplicitWait.explicitWaitVisibilityOfElement(priceInTable.get(0), WAIT_10_SEC);
		Double buyPrice = Double.parseDouble(priceInTable.get(0).getText())
				* Double.parseDouble(totalQuantityInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getUSDcBalance();
		String expected = Double.toString((Double.parseDouble(USDcBalance)) - buyPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		actual = actual.substring(0, 4);
		expected = expected.substring(0,4);				
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertTrue(actual.contains(expected));
		lOGGER.info("Verifying the Buy price deducted from the USDc");
	}

	public void verifyUSDcForSellMarket(String USDcBalance) {

		ExplicitWait.explicitWaitVisibilityOfElement(priceInTable.get(0), WAIT_10_SEC);
		Double sellPrice = Double.parseDouble(priceInTable.get(0).getText())
				* Double.parseDouble(totalQuantityInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getUSDcBalance();
		String expected = Double.toString((Double.parseDouble(USDcBalance)) + sellPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		actual = actual.substring(0, 4);
		expected = expected.substring(0, 4);
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertTrue(expected.matches(actual));
		lOGGER.info("Verifying the Buy price deducted from the USDc");
	}

	public void verifyLockedAmountForOpenOrderBuyLimit(String lockedAmount) {

		ExplicitWait.explicitWaitVisibilityOfElement(costRemainingInTable.get(0), WAIT_10_SEC);
		Double buyPrice = Double.parseDouble(costRemainingInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getLockedAmount();
		String expected = Double.toString((Double.parseDouble(lockedAmount)) + buyPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
//		System.out.println(actual);
//		System.out.println(expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the Buy price added to the Locked Amount");
	}

	public void verifyLockedAmountForExecutedOrderBuyLimit(String lockedAmount) {

		ExplicitWait.explicitWaitVisibilityOfElement(costRemainingInTable.get(0), WAIT_10_SEC);
		Double buyPrice = Double.parseDouble(costRemainingInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getLockedAmount();
		String expected = Double.toString((Double.parseDouble(lockedAmount)) - buyPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
//		System.out.println(actual);
//		System.out.println(expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the Buy price added to the Locked Amount");
	}

	public void verifyLockedAmountForCancelledOrderBuyLimit(String lockedAmount, String USDcBalance) {

		verifyLockedAmountForExecutedOrderBuyLimit(lockedAmount);
		verifyUSDcForSellLimit(USDcBalance);
	}

	public void verifyUSDcForSellLimit(String USDcBalance) {

		ExplicitWait.explicitWaitVisibilityOfElement(executedQuantityInTable.get(0), WAIT_10_SEC);
		ExplicitWait.explicitWaitVisibilityOfElement(priceInTable.get(0), WAIT_10_SEC);
		Double sellPrice = Double.parseDouble(executedQuantityInTable.get(0).getText())
				* Double.parseDouble(priceInTable.get(0).getText());
		homePage().click(homePage().faviconAssets);
		String actual = assetsPage().getUSDcBalance();
		String expected = Double.toString((Double.parseDouble(USDcBalance)) + sellPrice);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the sell price added to the USDc");
	}

	public void verifyQuantities(String quantity) {

		ExplicitWait.explicitWaitVisibilityOfElement(totalQuantityInTable.get(0), WAIT_10_SEC);
		ExplicitWait.explicitWaitVisibilityOfElement(executedQuantityInTable.get(0), WAIT_10_SEC);
		ExplicitWait.explicitWaitVisibilityOfElement(remainingQuantityInTable.get(0), WAIT_10_SEC);
		double quantitybuyed = Double.parseDouble(quantity);
		double totalQuantity = Double.parseDouble(totalQuantityInTable.get(0).getText());
		double executedQuantity = Double.parseDouble(executedQuantityInTable.get(0).getText());
		double remainingQuantity = Double.parseDouble(remainingQuantityInTable.get(0).getText());
		Assert.assertEquals(quantitybuyed, totalQuantity);
		Assert.assertEquals(quantitybuyed - executedQuantity, remainingQuantity);
		lOGGER.info("Verifying the Buy price deducted from the USDc");
	}

	public void verifyLimitOrder(Map<String, String> orderDetails) {

		verifyOrderType(orderDetails.get("Order Type"));
		verifyCTI(orderDetails.get("CTI"));
		verifyPrice(orderDetails.get("Price"));
		verifyQuantities(orderDetails.get("Quantity"));
	}

	public void verifyPagination() {

		pause(5000);
		driver.navigate().refresh();
		for (int i = 0; statusInTable.size() == 25; i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(pages, WAIT_30_SEC);
			String totalCount = pages.getText();
			int initialCount = Integer.parseInt(totalCount.substring(0, totalCount.indexOf("-") - 1));
			int finalCount = Integer.parseInt(totalCount.substring(totalCount.indexOf("-") + 2));
			int totalOrders = (finalCount - initialCount + 1);
//			System.out.println(initialCount);
//			System.out.println(finalCount);
//			System.out.println(statusInTable.size());
			Assert.assertEquals(totalOrders, statusInTable.size());
			try {
//				if (emptyTable.isDisplayed() == true) {
//					pause(5000);
//					driver.navigate().refresh();
				ExplicitWait.explicitWaitVisibilityOfElement(nextPage, WAIT_30_SEC);
				click(nextPage);
//				}
			} catch (Exception e) {
				pause(5000);
				driver.navigate().refresh();
				ExplicitWait.explicitWaitVisibilityOfElement(nextPage, WAIT_30_SEC);
				click(nextPage);
			}
		}
	}

	public void verifyCostRemaining(String lockedAmount, String USDcBalance) {

		ExplicitWait.explicitWaitVisibilityOfElement(orderTypeInTable.get(0), WAIT_10_SEC);
		String orderType = orderTypeInTable.get(0).getText();
		ExplicitWait.explicitWaitVisibilityOfElement(statusInTable.get(0), WAIT_10_SEC);
		String status = statusInTable.get(0).getText();
		ExplicitWait.explicitWaitVisibilityOfElement(priceInTable.get(0), WAIT_10_SEC);
		String price = priceInTable.get(0).getText();
		ExplicitWait.explicitWaitVisibilityOfElement(remainingQuantityInTable.get(0), WAIT_10_SEC);
		String remainingQuantity = remainingQuantityInTable.get(0).getText();
		String CTI = CTIsInTable.get(0).getText();
		if (orderType.equals("Buy limit") && status.equals("Open")) {
			verifyOpenBuyLimitOrders(CTI, price, remainingQuantity, lockedAmount);
		} else if (orderType.equals("Buy limit") && status.equals("Executed")) {
			verifyLockedAmountForExecutedOrderBuyLimit(lockedAmount);
		} else if (orderType.equals("Buy limit") && status.equals("Cancelled")) {
			verifyLockedAmountForCancelledOrderBuyLimit(lockedAmount, USDcBalance);
		}

	}

	public void verifyOpenBuyLimitOrders(String CTI, String price, String remainingQuantity, String lockedAmount) {

		homePage().click(homePage().faviconTrade);
		WebElement selectedCTI = driver.findElement(By.xpath(
				"(//div[@id='trakx-market-selector']//div[@class='trakx-currency' and text()='" + CTI + "'])[2]"));
		click(selectedCTI);
		String expectedPrice = tradePage().getOpenOrderPrice();
		String expectedQuantity = tradePage().getOpenOrderQuantity();
		Assert.assertEquals(price, expectedPrice);
		Assert.assertEquals(remainingQuantity, expectedQuantity);
		homePage().click(homePage().faviconOrders);
		verifyLockedAmountForOpenOrderBuyLimit(lockedAmount);
	}

	public void verifyMarketOrder(Map<String, String> orderDetails) {

		verifyOrderType(orderDetails.get("Order Type"));
		verifyCTI(orderDetails.get("CTI"));
//		verifyPrice(orderDetails.get("Price"));
		verifyQuantities(orderDetails.get("Quantity"));
	}
}
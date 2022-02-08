package pageObjects.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;
import utils.RandomGenerator;

public class TradePage extends PageFactoryInitializer {

	protected static Random r = new Random();
	Actions action = new Actions(driver);
	List<Double> allMarketList = new ArrayList<Double>();
	Map<String, String> orderDetails = new HashMap<String, String>();

	@FindBy(xpath = "//div[@class='pg-header__navbar-main']//span[text()='Trade']")
	public WebElement faviconTrade;

	@FindBy(xpath = "//div[text()='Simple']")
	public WebElement simple;

	@FindBy(xpath = "//div[text()='Advanced']")
	public WebElement advanced;

	@FindBy(xpath = "//div[@class='trakx-sidelist']//input[@class='pg-trading-header-selector-search-field']")
	public WebElement searchField;

	@FindBy(xpath = "//div[@class='trakx-sidelist']//div[@class='pg-trading-header-selector-search-icon']//img")
	public WebElement searchButton;

	@FindBy(xpath = "//div[@class='trakx-sidelist']//span[text()='Change']")
	public WebElement changeButton;

	@FindBy(xpath = "//div[@class='trakx-sidelist']//div[@class='trakx-currency']")
	public List<WebElement> CTIs;

	@FindBy(xpath = "//div[@class='trakx-sidelist']//span[@class='pg-dropdown-markets-list-container__negative']")
	public List<WebElement> currentMarketPrice;

//	@FindBy(xpath = "//div[@class='trakx-sidelist']//div[@class='current-market-info-description']")
//	public WebElement currentMarketInfo;
	// span[@class='trakx-currency__tooltip-active']
	@FindBy(xpath = "//span[@class='trakx-currency__tooltip-active']")
	public WebElement currentMarketInfo;

	@FindBy(xpath = "//div[@id='tv_chart_container']")
	public WebElement graph;

	@FindBy(xpath = "//div[text()='Buy']")
	public WebElement buy;

	@FindBy(xpath = "//div[@class='cr-tab-content cr-tab-content__active']//div[@class='dropdown']")
	public WebElement orderTypeDropdown;

	@FindBy(xpath = "//a[text()='Market']")
	public WebElement market;

	@FindBy(xpath = "//a[text()='Limit']")
	public WebElement limit;

	@FindBy(xpath = "//button[text()='Buy']")
	public WebElement buyButton;

	@FindBy(xpath = "//div[text()='Sell']")
	public WebElement sell;

	@FindBy(xpath = "//button[text()='Sell']")
	public WebElement sellButton;

	@FindBy(xpath = "//div[@class='pg-constituents']//div[@class='cr-title-component']")
	public WebElement constituents;

	@FindBy(xpath = "//div[@class='custom-detail']")
	public WebElement CTIDetails;

	@FindBy(xpath = "//div[@class='cr-combined-order-book']")
	public WebElement orderBook;

	@FindBy(xpath = "//div[@class='cr-open-orders']")
	public WebElement openOrders;

	@FindBy(xpath = "//div[@class='pg-recent-trades__markets']")
	public WebElement recentTrades;

	@FindBy(xpath = "//div[@class='cr-order-book'][1]//td[1]//span")
	public List<WebElement> sellingPrice;

	@FindBy(xpath = "//div[@class='cr-order-book'][1]//td[2]//span")
	public List<WebElement> sellingQuantity;

	@FindBy(xpath = "//div[@class='cr-order-book'][1]//td[3]//span")
	public List<WebElement> sellingCumulativeQuantity;

	@FindBy(xpath = "//div[@class='cr-order-book'][2]//td[1]//span")
	public List<WebElement> buyingPrice;

	@FindBy(xpath = "//div[@class='cr-order-book'][2]//td[2]//span")
	public List<WebElement> buyingQuantity;
	
	@FindBy(xpath = "//p[.='Order was created']")
	public WebElement createdOrderNotification;

	@FindBy(xpath = "//div[@class='cr-order-book'][2]//td[3]//span")
	public List<WebElement> buyingCumulativeQuantity;

	@FindBy(xpath = "//div[@class='cr-tab-content cr-tab-content__active']//input[@placeholder='Price']")
	public WebElement limitPrice;

	@FindBy(xpath = "//div[@class='cr-order-input__fieldset__input']//span")
	public WebElement marketPrice;

	@FindBy(xpath = "//div[@class='cr-tab-content cr-tab-content__active']//input[@placeholder='Quantity']")
	public WebElement quantity;

	@FindBy(xpath = "//div[@class='cr-tab-content cr-tab-content__active']//input[@placeholder='Amount']")
	public WebElement amount;

	@FindBy(xpath = "(//span[@class='react-resizable-handle react-resizable-handle-se'])[5]")
	public WebElement orderBookResizeable;

	@FindBy(xpath = "//span[text()='About Us']")
	public WebElement aboutUs;

	@FindBy(xpath = "//div[@class='cr-combined-order-book__market']//span")
	public WebElement lastMarketPrice;

	@FindBy(xpath = "//span[@class='cr-table-header__close']")
	public WebElement cancelAll;

	@FindBy(xpath = "//button[@class='cr-close-button']")
	public List<WebElement> cancelOrder;
	
	@FindBy(xpath = "(//span[@class='react-resizable-handle react-resizable-handle-se'])[6]")
	public WebElement openOrderResizeable;
	
    @FindBy(xpath = "//p[.='Order is being cancelled']")
    public WebElement cancelNotification;
    
    @FindBy(xpath = "//p[.='All orders are being canceled']")
    public WebElement cancelAllNotification;
    
	@FindBy(xpath = "//div[@class='cr-open-orders']//tbody//td//span[@style='color: var(--color-red);']")
	public WebElement openOrderSellColor;

	@FindBy(xpath = "//div[@class='cr-open-orders']//tbody//td//span[@style='color: var(--color-green);']")
	public WebElement openOrderBuyColor;

	@FindBy(xpath = "(//div[@class='cr-open-orders']//tbody//td[2]//span)[1]")
	public WebElement openOrderPrice;

	@FindBy(xpath = "(//div[@class='cr-open-orders']//tbody//td[3]//span)[1]")
	public WebElement openOrderQuantity;

	@FindBy(xpath = "//tbody//td[3]//span[@style='color: var(--color-green);']")
	public WebElement openOrderQuantityColor;

	@FindBy(xpath = "//tbody//td[4]//span[@style='color: var(--color-green);']")
	public WebElement openOrderAmountColor;

	@FindBy(xpath = "//div[@class='pg-recent-trades']//tbody//td[1]")
	public List<WebElement> recentTradePrice;

	@FindBy(xpath = "//div[@class='pg-recent-trades']//tbody//td[2]")
	public List<WebElement> recentTradeQuantity;

	@FindBy(xpath = "//div[@class='pg-recent-trades']//tbody//td[3]")
	public List<WebElement> recentTradeCumulativeQuantity;

	@FindBy(xpath = "//div[@class='pg-recent-trades']//tbody//td[3]")
	public List<WebElement> recentTradeTime;

	@FindBy(xpath = "//div[@aria-label='Dismiss']")
	public WebElement dismiss;

	@FindBy(xpath = "//div[@class='pane-legend-line pane-legend-wrap study']")
	public WebElement graphField;

	@FindBy(xpath = "//div[@class='trakx-simple__chart-switch-mode']//span")
	public List<WebElement> graphSwitchModes;

	@FindBy(xpath = "//input[@value='YES']")
	public WebElement MRPUpdateConfirmation;

	@FindBy(xpath = "//div[@class='trakx-modal-order-body']")
	public WebElement MRPUpdateBody;

	@FindBy(xpath = "//div[@class='error-message error-message--visible']")
	public WebElement errorMessage;

	private static final Logger lOGGER = LogManager.getLogger(TradePage.class.getName());

	public void searchForRandomCTI() {

		int randomNumberIndex = r.nextInt(CTIs.size());
		String randomCTI = CTIs.get(randomNumberIndex).getText();
		System.out.println("Valid search element to be entered is  :------" + randomCTI);

		ExplicitWait.explicitWaitVisibilityOfElement(searchField, WAIT_30_SEC);
		sendKeys(searchField, randomCTI);
		lOGGER.info("Entering the search field with valid data");
		verifySearch(randomCTI);
	}

	public String selectRandomCTI() {

		int randomNumberIndex = r.nextInt(CTIs.size());
		WebElement randomCTI = CTIs.get(randomNumberIndex);
		click(randomCTI);
		lOGGER.info("Clicking on random CTI among the list of CTIs");
		return randomCTI.getText();
	}

	public String selectRandomBuyPrice() {

		expandOrderBook();
		int randomNumberIndex = r.nextInt(buyingPrice.size());
		WebElement randomPrice = buyingPrice.get(randomNumberIndex);
		click(randomPrice);
		lOGGER.info("Clicking on buying price among the list of prices");
		return randomPrice.getText().replaceAll(",", "");
	}

	public String selectQuantityForRandomBuyPrice() {

		int randomNumberIndex = r.nextInt(buyingPrice.size());
		WebElement randomPrice = buyingPrice.get(randomNumberIndex);
		click(randomPrice);
		WebElement randomQuantity = buyingQuantity.get(randomNumberIndex);
		lOGGER.info("Clicking on buying price among the list of prices");
		return randomQuantity.getText();
	}

	public void selectRandomSellPrice() {

		int randomNumberIndex = r.nextInt(sellingPrice.size());
		WebElement randomPrice = sellingPrice.get(randomNumberIndex);
		click(randomPrice);
		lOGGER.info("Clicking on selling price among the list of prices");
	}

	public String selectQuantityForRandomSellPrice() {

		expandOrderBook();
		int randomNumberIndex = r.nextInt(sellingPrice.size());
		WebElement randomPrice = sellingPrice.get(randomNumberIndex);
		ExplicitWait.explicitWaitVisibilityOfElement(randomPrice, WAIT_30_SEC);
		click(randomPrice);
		WebElement randomQuantity = sellingQuantity.get(randomNumberIndex);
		lOGGER.info("Clicking on buying price among the list of prices");
		return randomQuantity.getText();
	}

	public String genRandomQuantity() {

		String randomQuantity = RandomGenerator.GenerateRandomNumber(2);
		randomQuantity = randomQuantity.substring(0, randomQuantity.lastIndexOf(".") + 3);
		return randomQuantity;
	}

	public void insertQuantity(String randomQuantity) {

		ExplicitWait.explicitWaitVisibilityOfElement(quantity, WAIT_30_SEC);
		System.out.println("------> " + randomQuantity);
		sendKeys(quantity, randomQuantity);
		lOGGER.info("Inserting random quantity of units to buy");
	}

	public String getAmount() {

		ExplicitWait.explicitWaitVisibilityOfElement(amount, WAIT_30_SEC);
		System.out.println("-----> " + amount.getAttribute("value"));
		lOGGER.info("Geting the amount being auto generated for respective price and quantity");
		return amount.getAttribute("value");
	}

	public void selectSellOrderType() {

		ExplicitWait.explicitWaitVisibilityOfElement(sell, WAIT_30_SEC);
		click(sell);
		lOGGER.info("Selecting the Sell type of order");
	}

	public void clickOnBuyButton() {

		try {
			ExplicitWait.explicitWaitVisibilityOfElement(buyButton, WAIT_30_SEC);
			click(buyButton);
			lOGGER.info("Clicking on Buy button");
			if (MRPUpdateConfirmation.isDisplayed() == true)
				click(MRPUpdateConfirmation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnSellButton() {

		ExplicitWait.explicitWaitVisibilityOfElement(sellButton, WAIT_30_SEC);
		click(sellButton);
		lOGGER.info("Clicking on Sell button");
	}

	public void verifySearch(String expected) {

		String actual = CTIs.get(0).getText();
		Assert.assertEquals(actual, expected);
	}

	public void verifySorting() {

		driver.navigate().refresh();
		ExplicitWait.explicitWaitVisibilityOfElement(changeButton, WAIT_30_SEC);
		click(changeButton);
		ascendingOrder();
		click(changeButton);
		descendingOrder();
	}

	public void ascendingOrder() {

		for (int i = 0; i < currentMarketPrice.size(); i++) {
			allMarketList.add(Double.parseDouble((currentMarketPrice.get(i).getText().replaceAll("%", ""))));
		}
		for (int i = 0; i < allMarketList.size() - 1; i++) {
			Assert.assertTrue(allMarketList.get(i) < allMarketList.get(i + 1));
		}
		allMarketList.clear();
		lOGGER.info("Verifying whether the market price has been sorted in ascending order or not");
	}

	public void descendingOrder() {

		for (int i = 0; i < currentMarketPrice.size(); i++) {
			allMarketList.add(Double.parseDouble((currentMarketPrice.get(i).getText().replaceAll("%", ""))));
		}
		for (int i = 0; i < allMarketList.size() - 1; i++) {
			Assert.assertTrue(allMarketList.get(i) > allMarketList.get(i + 1));
		}
		allMarketList.clear();
		lOGGER.info("Verifying whether the market price has been sorted in descending order or not");
	}

	public void verifyMouseHover() {

		int randomNumberIndex = r.nextInt(CTIs.size());
		WebElement randomCTI = CTIs.get(randomNumberIndex);
		ExplicitWait.explicitWaitVisibilityOfElement(randomCTI, WAIT_30_SEC);
//		scrollToElementView(CTIs.get(4));
//		click(CTIs.get(0));
		ExplicitWait.explicitWaitVisibilityOfElement(graphField, WAIT_30_SEC);
		mousehover(CTIs.get(4));
//		System.out.println(getTooltipText(currentMarketInfo));
//		System.out.println("----> " + currentMarketInfo.getText());
//		ExplicitWait.explicitWaitVisibilityOfElement(currentMarketInfo,WAIT_30_SEC);
//		Assert.assertTrue(action.moveToElement(randomCTI).moveToElement(currentMarketInfo).perform());
	}

	public void verifyCTIClick() {

		selectRandomCTI();
//		action.moveToElement(faviconTrade).perform();
//		click(advanced);
		Assert.assertTrue(graph.isDisplayed() == true);
//		String actual = (constituents.getText()).substring(0, constituents.getText().lastIndexOf("C"));
//		Assert.assertEquals(actual, randomCTI.getText());
		Assert.assertTrue(constituents.isDisplayed() == true);
		Assert.assertTrue(orderBook.isDisplayed() == true);
		Assert.assertTrue(openOrders.isDisplayed() == true);
		Assert.assertTrue(recentTrades.isDisplayed() == true);
		Assert.assertTrue(CTIDetails.isDisplayed() == true);
		lOGGER.info("Verifying the CTI details upon clicking on a random CTI");
	}

	public void verifySellingCreds() {

		selectRandomCTI();
		action.moveToElement(faviconTrade).perform();
		click(advanced);
		expandOrderBook();
		for (int i = 0; i < sellingPrice.size(); i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(sellingPrice.get(i), WAIT_30_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(sellingQuantity.get(i), WAIT_30_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(sellingCumulativeQuantity.get(i), WAIT_30_SEC);
			Assert.assertFalse(sellingPrice.get(i).getText().isEmpty());
			Assert.assertFalse(sellingQuantity.get(i).getText().isEmpty());
			Assert.assertFalse(sellingCumulativeQuantity.get(i).getText().isEmpty());
		}
		lOGGER.info("Verifying the selling properties present on the webpage or not");
	}

	public int verifySellingPrice() {

		int randomNumberIndex = r.nextInt(sellingPrice.size());
		WebElement randomPrice = sellingPrice.get(randomNumberIndex);
		expandOrderBook();
		scrollToElementView(randomPrice);
		JSclick(randomPrice);
		selectSellOrderType();
//		System.out.println("1----> " + randomPrice.getText());
//		System.out.println("2----> " + price.getAttribute("value"));
		String actual = randomPrice.getText().replaceAll(",", "");
		String expected = limitPrice.getAttribute("value");
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the selling price of sell limit order type");
		return randomNumberIndex;
	}

	public void verifyBuyingCreds() {

		selectRandomCTI();
		action.moveToElement(faviconTrade).perform();
		click(advanced);
		expandOrderBook();
		for (int i = 0; i < buyingPrice.size(); i++) {
			scrollToElementView(buyingPrice.get(i));
			ExplicitWait.explicitWaitVisibilityOfElement(buyingPrice.get(i), WAIT_30_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(buyingQuantity.get(i), WAIT_30_SEC);
			ExplicitWait.explicitWaitVisibilityOfElement(buyingCumulativeQuantity.get(i), WAIT_30_SEC);
			Assert.assertFalse(buyingPrice.get(i).getText().isEmpty());
			Assert.assertFalse(buyingQuantity.get(i).getText().isEmpty());
			Assert.assertFalse(buyingCumulativeQuantity.get(i).getText().isEmpty());
		}
		lOGGER.info("Verifying the buying properties present on the webpage or not");
	}

	public int verifyBuyingPrice() {

		int randomNumberIndex = r.nextInt(buyingPrice.size());
		WebElement randomPrice = buyingPrice.get(randomNumberIndex);
		scrollToElementView(randomPrice);
		click(randomPrice);
//		System.out.println("1----> " + randomPrice.getText());
//		System.out.println("2----> " + price.getAttribute("value"));
		String actual = randomPrice.getText().replaceAll(",", "");
		String expected = limitPrice.getAttribute("value");
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the buying price of buy limit order type");
		return randomNumberIndex;
	}

	public void verifyMarketPrice() {

		scrollToElementView(lastMarketPrice);
		ExplicitWait.explicitWaitVisibilityOfElement(lastMarketPrice, WAIT_30_SEC);
		Assert.assertFalse(lastMarketPrice.getText().isEmpty());
		scrollToElementView(orderBookResizeable);
		action.dragAndDropBy(orderBookResizeable, 50, 50).perform();
		lOGGER.info("Verifying the last market price updated and block adjustment");
	}
	
	public void clickCti(String name) {
		for (int i = 0; i < CTIs.size(); i++) {
			if (CTIs.get(i).getText().equals(name))
				click(CTIs.get(i));
		}
	}

	public void verifyOpenOrders() {

		
		clickCti("Top 10 NFT");
		tradePage().verifyBuyingPrice();
		sendKeys(quantity, "1.5");
		buyButton.click();
		explicitWaitVisibilityOfElement(openOrderBuyColor, WAIT_20_SEC);
		scrollToElementView(openOrderBuyColor);
		ExplicitWait.explicitWaitVisibilityOfElement(openOrderBuyColor, WAIT_30_SEC);
		Assert.assertTrue(openOrderBuyColor.getAttribute("style").contains("--color-green"));
		lOGGER.info("Verifying the color of the Buy orders");
		
		
		clickCti("Top 10 NFT");
		tradePage().verifySellingPrice();
		sendKeys(quantity, "1");
		tradePage().clickOnSellButton();
		
		driver.navigate().refresh();
		try {
			implicitlywait(WAIT_20_SEC);
		} catch (Exception e) {
			
		}
		
		clickCti("Top 10 NFT");
		
		scrollToElementView(openOrderSellColor);
		
		ExplicitWait.explicitWaitVisibilityOfElement(openOrderSellColor, WAIT_30_SEC);
		Assert.assertTrue(openOrderSellColor.getAttribute("style").contains("--color-red"));
		lOGGER.info("Verifying the color of the Buy/Sell orders");
//		
//		try {
//		scrollToElementView(openOrderSellColor);
//	
//		ExplicitWait.explicitWaitVisibilityOfElement(openOrderSellColor, WAIT_30_SEC);
//		Assert.assertTrue(openOrderSellColor.getAttribute("style").contains("--color-red"));
//		lOGGER.info("Verifying the color of the Buy/Sell orders");
//		}
//		catch(org.openqa.selenium.StaleElementReferenceException ae) {
//			scrollToElementView(openOrderSellColor);
//			
//			ExplicitWait.explicitWaitVisibilityOfElement(openOrderSellColor, WAIT_30_SEC);
//			Assert.assertTrue(openOrderSellColor.getAttribute("style").contains("--color-red"));
//			lOGGER.info("Verifying the color of the Buy/Sell orders");
//		}
		
	}
	
	public void expandOpenOrder() {

		ExplicitWait.explicitWaitVisibilityOfElement(openOrderResizeable, WAIT_30_SEC);
		scrollToElementView(openOrderResizeable);
		action.dragAndDropBy(openOrderResizeable, 75, 75).perform();
		lOGGER.info("Adjusting the Open Orders block");
	}
	
	
	public void opendOrderCancel() {
		expandOpenOrder();
		
		int randomNumberIndex = r.nextInt(cancelOrder.size());
		WebElement randomPrice = cancelOrder.get(randomNumberIndex);
		explicitWaitElementToBeClickable(randomPrice, WAIT_20_SEC);
		randomPrice.click();
		lOGGER.info("Clicked on cancel cross button");
    	explicitWaitVisibilityOfElement(cancelNotification, WAIT_20_SEC);
		Assert.assertTrue(cancelNotification.isDisplayed());
		lOGGER.info("Order is being cancelled display");
		explicitWaitElementToBeClickable(cancelAll, WAIT_20_SEC);
		//cancelAll.click();
		JSclick(cancelAll);
		lOGGER.info("Clicked on cancel all button");
		explicitWaitVisibilityOfElement(cancelAllNotification, WAIT_20_SEC);
		Assert.assertTrue(cancelAllNotification.isDisplayed());
		lOGGER.info("All orders are being canceled display");
	}

	public void selectSimpleTrading() {

		ExplicitWait.explicitWaitVisibilityOfElement(faviconTrade, WAIT_30_SEC);
		action.moveToElement(faviconTrade).perform();
		ExplicitWait.explicitWaitVisibilityOfElement(simple, WAIT_30_SEC);
		click(simple);
		lOGGER.info("Selecting simple type of trading");
	}

	public void selectAdvancedTrading() {

		ExplicitWait.explicitWaitVisibilityOfElement(faviconTrade, WAIT_30_SEC);
		action.moveToElement(faviconTrade).perform();
		ExplicitWait.explicitWaitVisibilityOfElement(advanced, WAIT_30_SEC);
		click(advanced);
		lOGGER.info("Selecting advanced type of trading");
	}

	public void selectMarketOrderType() {

		orderTypeDropdown.click();
		ExplicitWait.explicitWaitVisibilityOfElement(market, WAIT_30_SEC);
		click(market);
		lOGGER.info("Selecting market type of order type for trading");
	}

	public void expandOrderBook() {

		ExplicitWait.explicitWaitVisibilityOfElement(orderBookResizeable, WAIT_30_SEC);
		scrollToElementView(orderBookResizeable);
		action.dragAndDropBy(orderBookResizeable, 75, 75).perform();
		lOGGER.info("Adjusting the Order book block");
	}

	public String selectRandomQuantityForLimit(int randomQuantityIndex) {

//		int randomQuantity1 = genRandomNumber(2);
//		int randomQuantity2 = genRandomNumber(2);
//		String randomQuantity3 = Integer.toString(randomQuantity1).concat("." + Integer.toString(randomQuantity2));
//		double randomQuantity = Double.parseDouble(randomQuantity3);
//		wait.forElementToBeVisible(quantity);
////		System.out.println("------> "+randomQuantity);
//		sendKeys(quantity, Double.toString(randomQuantity));
//		lOGGER.info("Inserting random quantity of units to buy");
//
//		wait.forElementToBeVisible(amount);
////		System.out.println("-----> "+amount.getAttribute("value"));
//		String priceEntered = price.getAttribute("value");
//		String actual = amount.getAttribute("value");
//		String expected = Double.toString(randomQuantity * (Double.parseDouble(priceEntered)));
//		expected = expected.substring(0, expected.lastIndexOf(".") + 3);
////		System.out.println("------> " + expected);
//		Assert.assertEquals(expected, actual);
//		lOGGER.info("Verifying the amount calculated for respective price and quantity");
//
//		verifyOrderCreationForRandomQuantity(Double.toString(randomQuantity));

//		------>
//		expandOrderBook();
//		double randomQuantity1 = Double.parseDouble(RandomGenerator.GenerateRandomNumber(2));
//		String randomQuantity = Double.toString(randomQuantity1);
//		randomQuantity = randomQuantity.substring(0, randomQuantity.lastIndexOf(".") + 3);
//		ExplicitWait.explicitWaitVisibilityOfElement(quantity, WAIT_30_SEC);
//		System.out.println("------> " + randomQuantity);
         
		String randomQuantity1 = RandomGenerator.GenerateRandomNumber(1);
		String randomQuantity = randomQuantity1.concat("." +"80");
		//String randomQuantity = buyingQuantity.get(randomQuantityIndex).getText();
		sendKeys(quantity, randomQuantity);
		lOGGER.info("Inserting random quantity of units to buy");

		ExplicitWait.explicitWaitVisibilityOfElement(amount, WAIT_30_SEC);
		System.out.println("-----> " + amount.getAttribute("value"));
		String priceEntered = limitPrice.getAttribute("value");
		String actual = amount.getAttribute("value");
		String expected = Double.toString((Double.parseDouble(randomQuantity) * (Double.parseDouble(priceEntered))));
		if (expected.length() > 4)
			expected = expected.substring(0, expected.lastIndexOf(".") + 3); // decimal k bd 2 point lega
		System.out.println("------> " + expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the amount calculated for respective price and quantity");

		return randomQuantity;
	}

	public String selectRandomQuantityForMarket() {

		int randomQuantityIndex = r.nextInt(buyingQuantity.size());
		String randomQuantity = buyingQuantity.get(randomQuantityIndex).getText();
		sendKeys(quantity, randomQuantity);
		lOGGER.info("Inserting random quantity of units to buy");

//		double randomQuantity1 = Double.parseDouble(RandomGenerator.GenerateRandomNumber(2));
//		String randomQuantity = Double.toString(randomQuantity1);
//		randomQuantity = randomQuantity.substring(0, randomQuantity.lastIndexOf(".") + 3);
//		ExplicitWait.explicitWaitVisibilityOfElement(quantity, WAIT_30_SEC);
//		System.out.println("------> " + randomQuantity);
//		sendKeys(quantity, randomQuantity);
//		lOGGER.info("Inserting random quantity of units to buy");

		ExplicitWait.explicitWaitVisibilityOfElement(amount, WAIT_30_SEC);
		System.out.println("-----> " + amount.getAttribute("value"));
		String priceEntered = marketPrice.getText();
		priceEntered = priceEntered.replaceAll(",", "");
		String actual = amount.getAttribute("value");
		String expected = Double.toString((Double.parseDouble(randomQuantity) * (Double.parseDouble(priceEntered))));
		if (expected.length() > 4)
			expected = expected.substring(0, expected.lastIndexOf(".") + 3);
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		actual = actual.substring(0, 3);
		expected = expected.substring(0, 3);
		System.out.println(actual + "------> " + expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the amount calculated for respective price and quantity");
		System.out.println("------> " + randomQuantity);
		return randomQuantity;
	}

	public void verifyOrderCreationForRandomQuantity(String expected) {

		try {
			ExplicitWait.explicitWaitVisibilityOfElement(openOrderQuantity, WAIT_30_SEC);
			explicitWaitTextToBePresentInElement(openOrderQuantity, WAIT_30_SEC, expected);
			String actual = openOrderQuantity.getText();
			Assert.assertEquals(expected, actual);
		} catch (TimeoutException e) {
			ExplicitWait.explicitWaitVisibilityOfElement(openOrderQuantityColor, WAIT_30_SEC);
			explicitWaitTextToBePresentInElement(openOrderQuantityColor, WAIT_30_SEC, expected);
			String actual = openOrderQuantityColor.getText();
			Assert.assertEquals(expected, actual);
		}
		lOGGER.info("Verifying the quantity selected by user and quantity displayed after order creation");
	}

	public String selectRandomAmountForLimit() {

		int randomAmount1 = Integer.parseInt(RandomGenerator.GenerateRandomNumber(4));
		int randomAmount2 = Integer.parseInt(RandomGenerator.GenerateRandomNumber(2));
		String randomAmount3 = Integer.toString(randomAmount1).concat("." + Integer.toString(randomAmount2));
		double randomAmount = Double.parseDouble(randomAmount3);
		ExplicitWait.explicitWaitVisibilityOfElement(amount, WAIT_30_SEC);// System.out.println("------>
																			// "+randomAmount);
		sendKeys(amount, Double.toString(randomAmount));
		lOGGER.info("Inserting random amount to buy the units");

		ExplicitWait.explicitWaitVisibilityOfElement(quantity, WAIT_30_SEC);// System.out.println("----->
																			// "+quantity.getAttribute("value"));
		String priceEntered = limitPrice.getAttribute("value");
		String actual = quantity.getAttribute("value");
//		System.out.println(actual);
		String expected = Double.toString(randomAmount / (Double.parseDouble(priceEntered)));
		expected = expected.substring(0, expected.lastIndexOf(".") + 3);
	//	System.out.println("------> " + expected);
		Assert.assertEquals(expected, actual);
		lOGGER.info("Verifying the quantity calculated for respective price and amount");

		return Double.toString(randomAmount);
	}

	public String selectRandomAmountForMarket() {

		int randomAmount1 = Integer.parseInt(RandomGenerator.GenerateRandomNumber(4));
		int randomAmount2 = Integer.parseInt(RandomGenerator.GenerateRandomNumber(2));
		String randomAmount3 = Integer.toString(randomAmount1).concat("." + Integer.toString(randomAmount2));
		double randomAmount = Double.parseDouble(randomAmount3);
		ExplicitWait.explicitWaitVisibilityOfElement(amount, WAIT_30_SEC);
		System.out.println("------> " + randomAmount);
		sendKeys(amount, Double.toString(randomAmount));
		lOGGER.info("Inserting random amount to buy the units");

		ExplicitWait.explicitWaitVisibilityOfElement(quantity, WAIT_30_SEC);
		System.out.println("-----> " + quantity.getAttribute("value"));
		String priceEntered = marketPrice.getText();
		priceEntered = priceEntered.replaceAll(",", "");
		String expected = Double.toString(randomAmount / (Double.parseDouble(marketPrice.getText())));
		expected = expected.substring(0, expected.lastIndexOf(".") + 3);
		String actual = quantity.getAttribute("value");
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		System.out.println(actual + "------> " + expected);
		Assert.assertEquals(expected, actual);
		lOGGER.info("Verifying the quantity calculated for respective price and amount");

		return (Double.toString(randomAmount));
	}

	public void verifyOrderCreationForRandomAmount(String expected) {

		String actual = openOrderAmountColor.getText();
		System.out.println(actual);
//		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the amount selected by user and amount displayed after order creation");
	}
	
	public void verifyOrderCreationForsell() {

		explicitWaitVisibilityOfElement(createdOrderNotification, WAIT_20_SEC);
		Assert.assertTrue(createdOrderNotification.isDisplayed());
		lOGGER.info("Verifying the amount selected by user and amount displayed after order creation");
	}
	
	public void verifyRecentTrades() {

		ExplicitWait.explicitWaitVisibilityOfElement(recentTradePrice.get(0), WAIT_30_SEC);
		scrollToElementView(recentTradePrice.get(0));
		JSclick(recentTradePrice.get(0));
		String actual = recentTradePrice.get(0).getText().replaceAll(",", "");
		ExplicitWait.explicitWaitVisibilityOfElement(limitPrice, WAIT_30_SEC);
		String expected = limitPrice.getAttribute("value");
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertEquals(actual, expected);
		ExplicitWait.explicitWaitVisibilityOfElement(sell, WAIT_30_SEC);
		click(sell);
		Assert.assertEquals(actual, expected);

		ExplicitWait.explicitWaitVisibilityOfElement(recentTradeQuantity.get(0), WAIT_30_SEC);
		WebElement randomQuantity = recentTradeQuantity.get(0);
		scrollToElementView(randomQuantity);
		Assert.assertTrue(randomQuantity.isDisplayed());

		ExplicitWait.explicitWaitVisibilityOfElement(recentTradeTime.get(0), WAIT_30_SEC);
		WebElement randomTime = recentTradeTime.get(0);
		scrollToElementView(randomTime);
		Assert.assertTrue(randomTime.isDisplayed());
		lOGGER.info("Verifying the funtionalities present in recent trade table");
	}

	public void verifyRecentPriceAndLastMarketPrice() {

		ExplicitWait.explicitWaitVisibilityOfElement(lastMarketPrice, WAIT_30_SEC);
		String actual = lastMarketPrice.getText();
		ExplicitWait.explicitWaitVisibilityOfElement(recentTradePrice.get(0), WAIT_30_SEC);
		String expected = recentTradePrice.get(0).getText();
		expected = expected.concat(" USDc");
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying whether the recent trade price is same as of Last market price");
	}

	public Map<String, String> buyOrderProcess() {

		String CTI = selectRandomCTI();
		orderDetails.put("CTI", CTI);
		selectRandomBuyPrice();
		String price = limitPrice.getAttribute("value");
		orderDetails.put("Price", price);
		String quantity = genRandomQuantity();
		orderDetails.put("Quantity", quantity);
		insertQuantity(quantity);
		String amount = getAmount();
		orderDetails.put("Amount", amount);
		String orderType = orderTypeDropdown.getText();
		orderDetails.put("Order Type", orderType);
		clickOnBuyButton();
		return orderDetails;
	}

	public Map<String, String> sellOrderProcess() {

		String CTI = selectRandomCTI();
		orderDetails.put("CTI", CTI);
		String quantity = selectQuantityForRandomSellPrice();
		selectSellOrderType();
		String price = limitPrice.getAttribute("value");
		orderDetails.put("Price", price);
		orderDetails.put("Quantity", quantity);
		insertQuantity(quantity);
		String amount = getAmount();
		orderDetails.put("Amount", amount);
		String orderType = orderTypeDropdown.getText();
		orderDetails.put("Order Type", orderType);
		clickOnSellButton();
		return orderDetails;
	}

	public Map<String, String> buyOrderProcessForMarket() throws Exception {

		String CTI = selectRandomCTI();
		orderDetails.put("CTI", CTI);
		selectMarketOrderType();
//		String price = marketPrice.getText().replace(",", "");
//		System.out.println("------.......>>" + price);
//		orderDetails.put("Price", price);
		String quantity = genRandomQuantity();
		orderDetails.put("Quantity", quantity);
		insertQuantity(quantity);
		try {
			errorMessage.isDisplayed();
			System.out.println(errorMessage.getText());
			throw new Exception(errorMessage.getText() + ". CTI selected is " + CTI);
		} catch (NoSuchElementException e) {
			String amount = getAmount();
			orderDetails.put("Amount", amount);
			String orderType = orderTypeDropdown.getText();
			orderDetails.put("Order Type", orderType);
			try {
				clickOnBuyButton();
//			String latestPrice = MRPUpdateBody.getText();
//			System.out.println("-------->>>>>>>" + latestPrice);
//			latestPrice = latestPrice.substring(latestPrice.lastIndexOf("last traded price is") + 21,
//					latestPrice.lastIndexOf("USDc") - 1);
//			System.out.println("-------->>>>>>>" + latestPrice);
//			orderDetails.get("Price").replace(price, latestPrice);
//			orderDetails.put("Price", latestPrice);
				click(MRPUpdateConfirmation);
			} catch (NoSuchElementException f) {
				clickOnBuyButton();
			}
		}
//		String price = marketPrice.getText().replace(",", "");
//		System.out.println("------.......>>" + price);
//		orderDetails.put("Price", price);
		return orderDetails;
	}

	public Map<String, String> sellOrderProcessForMarket() throws Exception {

		String CTI = selectRandomCTI();
		orderDetails.put("CTI", CTI);
		selectSellOrderType();
		selectMarketOrderType();
//		String price = marketPrice.getText().replace(",", "");
//		System.out.println("------.......>>" + price);
//		orderDetails.put("Price", price);
		String quantity = genRandomQuantity();
		orderDetails.put("Quantity", quantity);
		insertQuantity(quantity);
		try {
			errorMessage.isDisplayed();
			System.out.println(errorMessage.getText());
			throw new Exception(errorMessage.getText() + ". CTI selected is " + CTI);
		} catch (NoSuchElementException e) {
			String amount = getAmount();
			orderDetails.put("Amount", amount);
			String orderType = orderTypeDropdown.getText();
			orderDetails.put("Order Type", orderType);
			try {
				clickOnSellButton();
//			String latestPrice = MRPUpdateBody.getText();
//			System.out.println("-------->>>>>>>" + latestPrice);
//			latestPrice = latestPrice.substring(latestPrice.lastIndexOf("last traded price is") + 21,
//					latestPrice.lastIndexOf("USDc") - 1);
//			System.out.println("-------->>>>>>>" + latestPrice);
//			orderDetails.get("Price").replace(price, latestPrice);
//			orderDetails.put("Price", latestPrice);
				click(MRPUpdateConfirmation);
			} catch (NoSuchElementException f) {
				clickOnSellButton();
			}
		}
//		String price = marketPrice.getText().replace(",", "");
//		System.out.println("------.......>>" + price);
//		orderDetails.put("Price", price);
		return orderDetails;
	}

	public String getOpenOrderPrice() {

		ExplicitWait.explicitWaitVisibilityOfElement(openOrderPrice, WAIT_30_SEC);
		System.out.println(openOrderPrice.getText());
		lOGGER.info("Fetching the open order price");
		return openOrderPrice.getText().replaceAll(",", "");
	}

	public String getOpenOrderQuantity() {

		ExplicitWait.explicitWaitVisibilityOfElement(openOrderQuantity, WAIT_30_SEC);
		System.out.println(openOrderQuantity.getText());
		lOGGER.info("Fetching the open order quantity");
		return openOrderQuantity.getText();
	}

	public void getOpenOrderDetails() {

		getOpenOrderPrice();
		getOpenOrderQuantity();
	}

	public int verifyChartAttributes() {

		int randomSwitchMode = r.nextInt(graphSwitchModes.size());
		WebElement randomMode = graphSwitchModes.get(randomSwitchMode);
		click(randomMode);
		lOGGER.info("Clicking on random modes among the list of switch modes");
		return randomSwitchMode;
	}

	public void verifyGraph(int randomSwitchMode) {

		System.out.println(randomSwitchMode);
		switch (randomSwitchMode) {
		// Case statements
		case 1:
			System.out.println("1");
			break;
		case 2:
			System.out.println("2");
			break;
		case 3:
			System.out.println("3");
			break;
		case 4:
			System.out.println("4");
			break;
		case 5:
			System.out.println("5");
			break;
		default:
			System.out.println("6");
		}
	}
}
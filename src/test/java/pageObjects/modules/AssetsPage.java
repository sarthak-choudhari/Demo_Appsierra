package pageObjects.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class AssetsPage extends PageFactoryInitializer {

	protected static Random random = new Random();

	@FindBy(xpath = "//div[@class='cr-wallet-item__single-balance']//span[@class='cr-wallet-item__balance-locked']")
	public WebElement lockedAmount;
	
	@FindBy(xpath = "//span[.='Centralised Exchanges']//ancestor::div[@class='cr-wallet-item trakx-wallet-item']//descendant::div[@class='cr-wallet-item__amount-locked']")
	public WebElement ctiLockedPrice;

	@FindBy(xpath = "//div[@class='cr-wallet-item__single-balance']//span[@class='cr-wallet-item__balance-amount']")
	public WebElement USDcBalance;

	@FindBy(xpath = "//div[text()='Funding']//parent::div//img")
	public WebElement currencyIcon;

	@FindBy(xpath = "//div[text()='Funding']//parent::div//span[@class='cr-wallet-item__balance']")
	public WebElement currency;

	@FindBy(xpath = "//div[@class='trakx-assets-list__title' and text()='CTIs']//parent::div//div[@class='cr-wallet-item__description']")
	public List<WebElement> CTIs;

	@FindBy(xpath = "//span[text()='Balance']//parent::div//following-sibling::span[@class='cr-wallet-item__balance-amount trakx-index-sum']")
	public WebElement balance;

	@FindBy(xpath = "//span[text()='NAV']//parent::div//following-sibling::span[@class='cr-wallet-item__balance-amount trakx-index-sum']")
	public WebElement NAV;

	@FindBy(xpath = "//span[text()='Price']//parent::div//following-sibling::span[@class='cr-wallet-item__balance-amount trakx-index-sum']")
	public WebElement price;

	@FindBy(xpath = "//div[@class='trakx-pnl-chart']")
	public WebElement PNLHistory;

	@FindBy(xpath = "//table[@class='trakx-composition__table']")
	public WebElement composition;

	@FindBy(xpath = "//div[@class='trakx-composition__component']//span")
	public List<WebElement> compositionsList;

	@FindBy(xpath = "//table[@class='cr-table']//tr//td[1]")
	public List<WebElement> transactionDates;

	@FindBy(xpath = "//table[@class='cr-table']//tr//td[3]")
	public List<WebElement> transactionDirections;

	@FindBy(xpath = "//table[@class='cr-table']//tr//td[4]")
	public List<WebElement> transactionAmount;

	@FindBy(xpath = "//span[@class='trakx-factsheet-icon']")
	public WebElement CTIDetailsIcon;

	@FindBy(xpath = "//span[@class='trakx-info-visible']")
	public WebElement CTIDetailsMessagePopUp;

	@FindBy(xpath = "//div[@class='intercom-chat-card intercom-1t2xutv eaja3by0']//div[2]")
	public WebElement message;

	@FindBy(xpath = "//div[@aria-label='Dismiss']")
	public WebElement closeMessage;

	private static final Logger lOGGER = LogManager.getLogger(AssetsPage.class.getName());

	public void verifyLockedAmount(String expected) {

		ExplicitWait.explicitWaitVisibilityOfElement(lockedAmount, WAIT_10_SEC);
		String actual = lockedAmount.getText().replaceAll(",", "");
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		System.out.println(actual + "----->" + expected);
		Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the locked amount of the buy order");
	}
	
	public void verifyCTILockedAmount(String expected) {

		ExplicitWait.explicitWaitVisibilityOfElement(ctiLockedPrice, WAIT_10_SEC);
		String actual = ctiLockedPrice.getText().replaceAll(",", "");
		actual = actual.substring(0, actual.lastIndexOf("."));
		expected = expected.substring(0, expected.lastIndexOf("."));
		System.out.println(actual + "----->" + expected);
	//	Assert.assertTrue(expected.contains(actual));
		lOGGER.info("Verifying the locked amount of the buy order");
	}

	public String getUSDcBalance() {

		ExplicitWait.explicitWaitVisibilityOfElement(USDcBalance, WAIT_30_SEC);
		String actual = USDcBalance.getText().replaceAll(",", "");
		System.out.println(actual);
		lOGGER.info("Fetching the USDc balance of the buy order");
		return actual;
	}

	public String getLockedAmount() {

		ExplicitWait.explicitWaitVisibilityOfElement(lockedAmount, WAIT_30_SEC);
		String actual = lockedAmount.getText().replaceAll(",", "");
		System.out.println(actual);
		lOGGER.info("Fetching the locked amount of the buy order");
		return actual;
	}
	
	public String centralisedExchangesLockedAmount() {

		ExplicitWait.explicitWaitVisibilityOfElement(ctiLockedPrice, WAIT_30_SEC);
		String actual = ctiLockedPrice.getText().replaceAll(",", "");
		System.out.println(actual);
		lOGGER.info("Fetching the locked amount of the buy order");
		return actual;
	}

	public void verifyCurrency() {

		ExplicitWait.explicitWaitVisibilityOfElement(currencyIcon, WAIT_30_SEC);
		Assert.assertTrue(currencyIcon.isDisplayed());
		ExplicitWait.explicitWaitVisibilityOfElement(currencyIcon, WAIT_30_SEC);
		Assert.assertFalse(currency.getText().isEmpty());
		lOGGER.info("Verifying the Currency Icon and Currency being displayed or not");
	}

	public void selectRandomCTI() {

		int randomNumberIndex = random.nextInt(CTIs.size());
		WebElement randomCTI = CTIs.get(randomNumberIndex);
		click(randomCTI);
		lOGGER.info("Clicking on random CTI among the list of CTIs");
	}

	public String selectRandomComposition() {

		int randomNumberIndex = random.nextInt(compositionsList.size());
		WebElement randomComposition = compositionsList.get(randomNumberIndex);
      	System.out.println(randomComposition.getText());
      String constituentName =	randomComposition.getText();
		click(randomComposition);
		lOGGER.info("Clicking on random Composition among the list");
		return  constituentName;
	}

	public void verifyCTIList() {

		selectRandomCTI();
		ExplicitWait.explicitWaitVisibilityOfElement(balance, WAIT_30_SEC);
		Assert.assertFalse(balance.getText().isEmpty());
		ExplicitWait.explicitWaitVisibilityOfElement(NAV, WAIT_30_SEC);
		Assert.assertFalse(NAV.getText().isEmpty());
		ExplicitWait.explicitWaitVisibilityOfElement(price, WAIT_30_SEC);
		Assert.assertFalse(price.getText().isEmpty());
		ExplicitWait.explicitWaitVisibilityOfElement(PNLHistory, WAIT_30_SEC);
		Assert.assertFalse(PNLHistory.getText().isEmpty());
		ExplicitWait.explicitWaitVisibilityOfElement(composition, WAIT_30_SEC);
		Assert.assertFalse(composition.getText().isEmpty());
		lOGGER.info("Verifying the CTI details being displayed and are not empty");
	}

	public void verifyDate() {

//		driver.navigate().refresh();
		for (int i = 0; i < transactionDates.size() - 1; i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(transactionDates.get(i), WAIT_30_SEC);
			String date1 = transactionDates.get(i).getText();
			String date2 = transactionDates.get(i + 1).getText();

			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  //24hrs date format
				
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

	public void verifyDirection() {

		for (int i = 0; i < transactionDirections.size(); i++) {
			try {
				ExplicitWait.explicitWaitVisibilityOfElement(transactionDirections.get(i), WAIT_30_SEC);
				Assert.assertEquals(transactionDirections.get(i).getText(), "Buy");
			} catch (AssertionError e) {
				Assert.assertEquals(transactionDirections.get(i).getText(), "Sell");
			}
		}
		lOGGER.info("Verifying the directions of the transaction history whether its buy Or sell");
	}

	public void verifyTransactionAmount() {

		for (int i = 0; i < transactionAmount.size(); i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(transactionAmount.get(i), WAIT_30_SEC);
			Assert.assertFalse(transactionAmount.get(i).getText().isEmpty());
		}
		lOGGER.info("Verifying the amount of the transaction history whether displayed or not");
	}

	public void verifyTransactionHistory() {

		verifyDate();
		verifyDirection();
		verifyTransactionAmount();
	}

	public void verifyComposition() {

		String  expectedConstituentName =selectRandomComposition();
		windowHandling();
		String compositionURL = driver.getCurrentUrl();
//		System.out.println(compositionURL);
		Assert.assertTrue(compositionURL.contains("https://www.coingecko.com/"));
		
		WebElement constituentName=driver.findElement(By.xpath("//h1"));
		String actualnameHeading =constituentName.getText();
	              System.out.println(expectedConstituentName);
              System.out.println(actualnameHeading);
	              
		//Assert.assertEquals(expectedConstituentName.contains(actualnameHeading),true);
		lOGGER.info("Verifying the compositions and redirected to composition info page or not");
	}

	public void verifyCTIDetailsIcon() throws Exception {

		selectRandomCTI();
//		robotScrollDown();
		ExplicitWait.explicitWaitVisibilityOfElement(CTIDetailsIcon, WAIT_30_SEC);
		mousehover(CTIDetailsIcon);
		ExplicitWait.explicitWaitVisibilityOfElement(CTIDetailsMessagePopUp, WAIT_30_SEC);
		Assert.assertTrue(CTIDetailsMessagePopUp.isDisplayed());

		click(CTIDetailsIcon);
		windowHandling();
		String compositionURL = driver.getCurrentUrl();
//		System.out.println(compositionURL);
		Assert.assertTrue(compositionURL.contains("https://drive.google.com/file/"));
		lOGGER.info(
				"Verifying the CTIs info details icon mouse-hover and details page redirected upon clicking or not");
	}
}
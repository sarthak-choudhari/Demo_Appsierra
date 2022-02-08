package pageObjects.modules;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class HomePage extends PageFactoryInitializer {

	@FindBy(xpath = "//div[@class='pg-logo']")
	public WebElement projectLogo;

	@FindBy(xpath = "//div[@class='pg-header__navbar-main']//span[text()='Trade']")
	public WebElement faviconTrade;

	@FindBy(xpath = "//div[@class='dropdown-menu-mode-container']//span[@class='icon']")
	public WebElement tradeDropDown;

	@FindBy(xpath = "//div[text()='Simple']")
	public WebElement simple;

	@FindBy(xpath = "//div[text()='Advanced']")
	public WebElement advanced;

	@FindBy(xpath = "//div[@class='pg-header__navbar-main']//span[text()='Assets']")
	public WebElement faviconAssets;

	@FindBy(xpath = "//div[@class='pg-header__navbar-main']//span[text()='Orders']")
	public WebElement faviconOrders;

	@FindBy(xpath = "//img[@alt='flag']")
	public WebElement faviconFlag;

	@FindBy(xpath = "//div[@class='switcher-item switcher-item--active']")
	public WebElement colorThemeActive;

	@FindBy(xpath = "//div[@class='switcher-item']")
	public WebElement colorThemeInactive;

	@FindBy(xpath = "//div[@class='pg-sidebar__toggler ']//span[@class='icon']")
	public WebElement signupDropDown;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper-nav']//span[text()='Sign In']")
	public WebElement signIn;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper-nav']//span[text()='Sign Up']")
	public WebElement signUp;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper-nav']//span[text()='Trade']")
	public WebElement sideMenuTrade;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper  pg-sidebar-wrapper--active']//span[text()='Profile']")
	public WebElement profile;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper-nav']//span[text()='Assets']")
	public WebElement sideMenuAssets;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper-nav']//span[text()='Orders']")
	public WebElement sideMenuOrders;

	@FindBy(xpath = "//div[@class='pg-sidebar-wrapper  pg-sidebar-wrapper--active']//span[text()='Logout']")
	public WebElement logOut;

	@FindBy(xpath = "//span[text()='Verify']")
	public WebElement verify;

	@FindBy(xpath = "//h1")
	public List<WebElement> sectionHeading;

	private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

	public void clickOnAccountIcon() {
		explicitWaitElementToBeClickable(signupDropDown, WAIT_30_SEC).click();
		logger.info("Clicking on section for registration page pop-up");
	}

	public void clickOnSignIn() {
		explicitWaitVisibilityOfElement(signIn, WAIT_20_SEC).click();
		logger.info("Clicking on sign-in of registration page");
	}

	public void clickOnSignUp() {
		explicitWaitVisibilityOfElement(signUp, WAIT_20_SEC).click();
		logger.info("Clicking on sign-up of registration page");
	}

	public void verifySideMenuWithoutSignedin() {
		explicitWaitVisibilityOfElement(signIn, WAIT_20_SEC);
		Assert.assertTrue(signIn.isDisplayed() == true);
		explicitWaitVisibilityOfElement(signUp, WAIT_20_SEC);
		Assert.assertTrue(signUp.isDisplayed() == true);
		explicitWaitVisibilityOfElement(sideMenuTrade, WAIT_20_SEC);
		Assert.assertTrue(sideMenuTrade.isDisplayed() == true);
	}

	public void verifySideMenuWithSignedin() {

		try {
			if (signIn.isDisplayed() == true) {
				homePage().clickOnSignIn();
				loginPage().setUsername(EMAIL_ID);
				loginPage().setPassword(PASSWORD);
				loginPage().clickSubmit();
			}
		} catch (NoSuchElementException e) {
			explicitWaitVisibilityOfElement(profile, WAIT_20_SEC);
			Assert.assertTrue(profile.isDisplayed() == true);
			explicitWaitVisibilityOfElement(sideMenuAssets, WAIT_20_SEC);
			Assert.assertTrue(sideMenuAssets.isDisplayed() == true);
			explicitWaitVisibilityOfElement(sideMenuTrade, WAIT_20_SEC);
			Assert.assertTrue(sideMenuTrade.isDisplayed() == true);
			explicitWaitVisibilityOfElement(sideMenuOrders, WAIT_20_SEC);
			Assert.assertTrue(sideMenuOrders.isDisplayed() == true);
			explicitWaitVisibilityOfElement(logOut, WAIT_20_SEC);
			Assert.assertTrue(logOut.isDisplayed() == true);
			logger.info("Verifying wether the elements in the side menu are appering for logged-in account");
		}
	}

	public void verifyProjectLogo() {

//		Assert.assertTrue(driver.getCurrentUrl().equals("https://trakx.opendax.app/profile"));
		explicitWaitVisibilityOfElement(projectLogo, WAIT_20_SEC);
		Assert.assertTrue(projectLogo.isDisplayed() == true);
		logger.info("Verified Project Logo");
		click(projectLogo);
//		Assert.assertTrue(driver.getCurrentUrl().equals("https://trakx.opendax.app/landing"));
	}

	public void verifyFavicon() {
		explicitWaitVisibilityOfElement(faviconTrade, WAIT_20_SEC);
		Assert.assertTrue(faviconTrade.isDisplayed() == true);
		click(faviconTrade);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://trakx.opendax.app/tradingSimple"));
		explicitWaitVisibilityOfElement(faviconAssets, WAIT_20_SEC);
		Assert.assertTrue(faviconAssets.isDisplayed() == true);
		click(faviconAssets);
		pause(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://trakx.opendax.app/wallets"));
		explicitWaitVisibilityOfElement(faviconOrders, WAIT_20_SEC);
		click(faviconOrders);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://trakx.opendax.app/orders"));
		Assert.assertTrue(faviconOrders.isDisplayed() == true);
		explicitWaitVisibilityOfElement(faviconFlag, WAIT_20_SEC);
		Assert.assertTrue(faviconFlag.isDisplayed() == true);
	}

	public void verifyColorTheme() {

		for (int i = 0; i < 2; i++) {
			explicitWaitVisibilityOfElement(colorThemeInactive, WAIT_20_SEC);
			click(colorThemeInactive);
     		pause(3000);
			Assert.assertTrue(colorThemeActive.isDisplayed() == true);
			logger.info("Verified colour theme of the page");
			
		}
	}

	public void clickOnOrders() {

		explicitWaitVisibilityOfElement(faviconOrders, WAIT_30_SEC);
		mousehover(faviconOrders);
		click(faviconOrders);
		logger.info("Clicking on Orders of registration page");
	}

	public void clickOnTrade() {

		explicitWaitVisibilityOfElement(assetsPage().USDcBalance, WAIT_20_SEC);
		click(tradeDropDown);
		logger.info("Clicking on Trade from homepage");
	}

	public void selectAdvancedTrade() {

		explicitWaitVisibilityOfElement(advanced, WAIT_20_SEC);
		click(advanced);
		logger.info("Clicking on advanced type of trade from homepage");
	}

	public void selectSimpleTrade() {

		explicitWaitVisibilityOfElement(simple, WAIT_20_SEC);
		click(simple);
		logger.info("Clicking on simple type of trade from homepage");
	}
}
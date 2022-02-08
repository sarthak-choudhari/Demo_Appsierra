package pageObjects.modules;

import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWait;

public class ProfilePage extends PageFactoryInitializer {

	@FindBy(xpath = "//input[@value='CHANGE']")
	public WebElement passwordChange;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement disabledPasswordChange;

	@FindBy(xpath = "//span[@class='slider']")
	public WebElement twoFactorToggle;

	@FindBy(xpath = "(//span[text()='Verified'])[1]")
	public WebElement emailVerification;

	@FindBy(xpath = "(//span[text()='Verified'])[2]")
	public WebElement phoneVerification;

	@FindBy(xpath = "(//span[text()='Verified'])[3]")
	public WebElement docVerification;

	@FindBy(xpath = "//div[@class='cr-email-form__option-inner']//span")
	public WebElement changePasswordTitle;

	@FindBy(xpath = "//input[@id='referral-id']")
	public WebElement referralLink;

	@FindBy(xpath = "//input[@value='COPY']")
	public WebElement copyReferralLink;

	@FindBy(xpath = "//p[@class='cr-alert__title']")
	public WebElement alertMessage;

	@FindBy(xpath = "//tbody//tr//td[1]")
	public List<WebElement> date;

	@FindBy(xpath = "//tbody//tr//td[2]")
	public List<WebElement> action;

	@FindBy(xpath = "//tbody//tr//td[3]")
	public List<WebElement> result;

	@FindBy(xpath = "//tbody//tr//td[4]")
	public List<WebElement> addressIP;

	@FindBy(xpath = "//tbody//tr//td[5]")
	public List<WebElement> userAgent;

	@FindBy(xpath = "//div[@class='pg-history-elem__pagination']//p")
	public WebElement pages;

	@FindBy(xpath = "//button[@class='pg-history__pagination-next']")
	public WebElement nextPageButton;
	
	@FindBy(xpath = "//div[@class='cr-email-form__cros-icon']//img")
	public WebElement closeButton;

	private static final Logger lOGGER = LogManager.getLogger(ProfilePage.class.getName());

	public void verifyProfileVerificationStatus() {

		ExplicitWait.explicitWaitVisibilityOfElement(emailVerification, WAIT_30_SEC);
		Assert.assertTrue(emailVerification.isDisplayed(), "Please make sure that your email ID has been verified");
		ExplicitWait.explicitWaitVisibilityOfElement(phoneVerification, WAIT_30_SEC);
		Assert.assertTrue(emailVerification.isDisplayed(), "Please make sure that your phone number has been verified");
		ExplicitWait.explicitWaitVisibilityOfElement(docVerification, WAIT_30_SEC);
		Assert.assertTrue(emailVerification.isDisplayed(),
				"Please make sure that your personal documents have been verified");
		lOGGER.info("Verifying wether the profile has been completely verified or not");
	}

	public void verifyChangePassword() {

		ExplicitWait.explicitWaitVisibilityOfElement(passwordChange, WAIT_10_SEC);
		click(passwordChange);
		ExplicitWait.explicitWaitVisibilityOfElement(changePasswordTitle, WAIT_10_SEC);
		Assert.assertTrue(changePasswordTitle.getText().equalsIgnoreCase("Change password"));
		ExplicitWait.explicitWaitVisibilityOfElement(disabledPasswordChange, WAIT_10_SEC);
		Assert.assertTrue(disabledPasswordChange.isEnabled() == false);
		lOGGER.info("Verifying the change password page in profile section");
		ExplicitWait.explicitWaitVisibilityOfElement(closeButton, WAIT_10_SEC);
		click(closeButton);
	}

	public void verifyreferralLink() throws HeadlessException, UnsupportedFlavorException, IOException {

		ExplicitWait.explicitWaitVisibilityOfElement(referralLink, WAIT_10_SEC);
		String actual = referralLink.getAttribute("value");
		click(copyReferralLink);
//		System.out.println(actual);
		String expected = copyClipboard();
		System.err.println(expected);
		ExplicitWait.explicitWaitVisibilityOfElement(alertMessage, WAIT_10_SEC);
		Assert.assertTrue(alertMessage.isDisplayed() == true);
		Assert.assertTrue(alertMessage.getText().contains("Address copied"));
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the referral link in profile section");
	}

	public void verifyAccountActivities() {

		verifyDate();
		for (int i = 0; i < date.size(); i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(action.get(i), WAIT_10_SEC);
			Assert.assertTrue(action.get(i).isDisplayed() == true);
			ExplicitWait.explicitWaitVisibilityOfElement(result.get(i), WAIT_10_SEC);
			Assert.assertTrue(result.get(i).isDisplayed() == true);
			ExplicitWait.explicitWaitVisibilityOfElement(addressIP.get(i), WAIT_10_SEC);
			Assert.assertTrue(addressIP.get(i).isDisplayed() == true);
			ExplicitWait.explicitWaitVisibilityOfElement(userAgent.get(i), WAIT_10_SEC);
			Assert.assertTrue(userAgent.get(i).isDisplayed() == true);
		}
		lOGGER.info("Verifying different accocunt activities in profile section");
		paginationVerification();
	}

	public void verifyDate() {

		for (int i = 0; i < date.size() - 1; i++) {
			ExplicitWait.explicitWaitVisibilityOfElement(date.get(i), WAIT_10_SEC);
			String date1 = date.get(i).getText();
			String date2 = date.get(i + 1).getText();

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
		lOGGER.info("Verifying the dates in descending order");
	}

	public void paginationVerification() {
//
//		ExplicitWait.explicitWaitVisibilityOfElement(pages, WAIT_10_SEC);
//		String x = pages.getText().replaceAll(" ", "");
//		System.out.println(x);
//		String total = x.substring(x.indexOf("f") + 1);
//		System.out.println(total);
//		int totalPages = Integer.parseInt(total);
//		for (int i = 0; i < (totalPages / 25); i++) {
///			click(nextPageButton);
//			String y = pages.getText().replaceAll(" ", "");
//			String first = y.substring(0, y.lastIndexOf("-"));
//			String last = y.substring(y.indexOf("-") + 1, y.lastIndexOf("o"));
//			int firstData = Integer.parseInt(first);
//			int lastData = Integer.parseInt(last);
//			Assert.assertEquals(firstData, (i * 25) + 1);
//			Assert.assertEquals(lastData, (i + 1) * 25);
//			click(nextPageButton);
		
		
		ExplicitWait.explicitWaitVisibilityOfElement(pages, WAIT_10_SEC);
		String x = pages.getText().replaceAll(" ", "");
		System.out.println(x);
		String total = x.substring(x.indexOf("f") + 1);
		System.out.println(total);
		int totalPages = Integer.parseInt(total);
		int tableLength=date.size();
		for (int i = 0; i < totalPages /tableLength ; i++) {

			String y = pages.getText().replaceAll(" ", "");
			String first = y.substring(0, y.lastIndexOf("-"));
			String last = y.substring(y.indexOf("-") + 1, y.lastIndexOf("o"));
			int firstData = Integer.parseInt(first);
			int lastData = Integer.parseInt(last);
			Assert.assertEquals(firstData, (lastData-date.size()) + 1);
			Assert.assertEquals(lastData, (firstData+date.size())-1);
			click(nextPageButton);
		}
	}
}
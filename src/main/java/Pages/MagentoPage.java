package Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MagentoPage extends BasePage {

	Random random = new Random();

	@FindBy(xpath = "//a//span[text()='Customers']")
	WebElement customers;

	@FindBy(xpath = "//a//span[text()='All Customers']")
	WebElement allCustomers;

	@FindBy(xpath = "//a//span[text()='Marketing']")
	WebElement marketing;

	@FindBy(xpath = "//a//span[text()='Customer Groups']")
	WebElement customerGroups;

	@FindBy(xpath = "(//input[@id='fulltext'])[1]")
	WebElement searchField;

	@FindBy(xpath = "//a//span[text()='Manage Promo Banner']")
	WebElement managePromoBanner;

	@FindBy(xpath = "//td[2]//div[@class='data-grid-cell-content']")
	List<WebElement> groupName;

	@FindBy(xpath = "//button[@class='action-select']")
	List<WebElement> select;

	@FindBy(xpath = "//a[text()='Edit']")
	List<WebElement> edit;

	@FindBy(xpath = "//a[@class='action-menu-item']")
	WebElement editButton;

	@FindBy(xpath = "//input[@id='prohibit_shipping_address_changes']")
	WebElement prohibitShippingCheckbox;

	@FindBy(xpath = "//input[@id='prohibit_billing_address_changes']")
	WebElement prohibitBillingCheckbox;

	@FindBy(xpath = "//button[@id='save']")
	WebElement saveCustomerGroup;

	@FindBy(xpath = "//select[@id='banner_status']")
	WebElement bannerStatusDropdown;

	@FindBy(xpath = "//select[@id='banner_status']//option")
	List<WebElement> selectedOption;

	@FindBy(xpath = "//div[@data-ui-id='messages-message-success']")
	WebElement alertMessage;

	@FindBy(xpath = "//table[@class='data-grid data-grid-draggable']//td[4]//div")
	List<WebElement> enabledOption;

	@FindBy(xpath = "//table[@class='data-grid data-grid-draggable']//td[2]//div")
	List<WebElement> bannerID;

	@FindBy(xpath = "//a[@id='tab_customer']")
	WebElement accountInfo;

	@FindBy(xpath = "//input[@name='customer[non_activated]']")
	WebElement nonActivatedAccountStatus;

	@FindBy(xpath = "//a[@id='tab_orders_content']")
	WebElement orders;

	@FindBy(xpath = "//td[@class=' col-increment_id                                        ']")
	WebElement orderID;

	private static final Logger lOGGER = LogManager.getLogger(MagentoPage.class.getName());

	public MagentoPage(WebDriver driver) {
		super(driver);
	}

	public boolean editCustomerGroup() {

		wait.forElementToBeVisible(customers);
		click(customers);
		lOGGER.info("selecting customers from side menu");

		wait.forElementToBeVisible(customerGroups);
		click(customerGroups);
		lOGGER.info("selecting customer groups from side menu");

		editBarcodeTestingGroup();
		boolean flag = verifyCheckbox();
		lOGGER.info("Clicking on the checkbox of the shipping details");
		return flag;
	}

	public void editBarcodeTestingGroup() {

		pause(10000);
		for (int i = 0; i < groupName.size(); i++) {
			wait.forElementToBeVisible(groupName.get(i));
			if (groupName.get(i).getText().equals("BarcodesTesting")) {
				scrollToElementView(select.get(i - 2));
				wait.forElementToBeVisible(select.get(i - 2));
				wait.forElementToBeClickable(select.get(i - 2));
				js.clickElement(select.get(i - 2));
				scrollToElementView(edit.get(i));
				wait.forElementToBeVisible(edit.get(i));
				wait.forElementToBeClickable(edit.get(i));
				js.clickElement(edit.get(i));
			}
		}
	}

	public boolean verifyCheckbox() {

		wait.forElementToBeVisible(prohibitBillingCheckbox);
		Assert.assertTrue(prohibitBillingCheckbox.isDisplayed() == true);
		wait.forElementToBeVisible(prohibitBillingCheckbox);
		Assert.assertTrue(prohibitShippingCheckbox.isDisplayed() == true);

		wait.forElementToBeVisible(prohibitShippingCheckbox);
		click(prohibitShippingCheckbox);
		boolean flag = prohibitShippingCheckbox.isSelected();
		wait.forElementToBeVisible(saveCustomerGroup);
		click(saveCustomerGroup);
		wait.forElementToBeVisible(alertMessage);
		Assert.assertTrue(alertMessage.getText().equals("You saved the customer group."));
		return flag;
	}

	public Map<String, String> editPromoBanner() {

		Map<String, String> selectedBanner = new HashMap<String, String>();
		wait.forElementToBeVisible(marketing);
		click(marketing);
		lOGGER.info("selecting marketing from side menu");

		wait.forElementToBeVisible(managePromoBanner);
		click(managePromoBanner);
		lOGGER.info("selecting manage promo banner from marketing option");

		wait.forPage();
//		wait.forElementToBeVisible(editButton);
//		click(editButton);
//		lOGGER.info("editing the manage promo banner");
//
//		for (int i = 0; i < selectedOption.size(); i++) {
//			wait.forElementToBeVisible(selectedOption.get(i));
//			try {
//				if (selectedOption.get(i).isSelected()==true) {
//					selected = selectedOption.get(i).getText();
//				}
//			} catch (NullPointerException e) {
//				if (selectedOption.get(i + 1).getAttribute("selected").equals("selected")) {
//					selected = selectedOption.get(i + 1).getText();
//				}
//			}
//		}
//		wait.forElementToBeVisible(bannerStatusDropdown);
//		dropDownMethod(bannerStatusDropdown, "VisibleText", "Yes");
//		lOGGER.info("Enabling the banner to be visible on product page");

//		wait.forElementToBeVisible(saveCustomerGroup);
//		click(saveCustomerGroup);
//		wait.forElementToBeVisible(alertMessage);
//		Assert.assertTrue(alertMessage.getText().equals("You saved the customer group."));

		pause(10000);
		wait.forElementToBeVisible(bannerID.get(0));
		int randomNumberIndex = random.nextInt(bannerID.size());
		wait.forElementToBeVisible(bannerID.get(randomNumberIndex));
		String randomID = bannerID.get(randomNumberIndex).getText();
		System.out.println("The random ID selected is :------" + randomID);

		wait.forElementToBeVisible(enabledOption.get(randomNumberIndex));
		String randomOption = enabledOption.get(randomNumberIndex).getText();
		System.out.println("The random option selected is :------" + randomOption);

		selectedBanner.put(randomID, randomOption);
		return selectedBanner;
	}

	public void verifyCustomerUpdate(String username) {

		wait.forElementToBeVisible(customers);
		click(customers);
		lOGGER.info("selecting customers from side menu");

		wait.forElementToBeVisible(allCustomers);
		click(allCustomers);
		lOGGER.info("selecting all customers from customers menu");

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, username);
		searchField.sendKeys(Keys.ENTER);
		lOGGER.info("selecting all customers from customers menu");

		wait.forElementToBeVisible(editButton);
		click(editButton);
		lOGGER.info("editing the new user");
	}

	public void verifyOrderID(String expected) {

		wait.forElementToBeVisible(orders);
		click(orders);
		lOGGER.info("Clicking on Orders of account info section");

		wait.forElementToBeVisible(orderID);
		String actual = orderID.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying the order ID created for purchased product");
	}

	public String verifyAccountActivation(String passwordStatus) {

		String expected = "";
		wait.forElementToBeVisible(accountInfo);
		click(accountInfo);
		lOGGER.info("Clicking on account information of account info section");

		scrollToElementView(nonActivatedAccountStatus);
		wait.forElementToBeVisible(nonActivatedAccountStatus);
		String actual = nonActivatedAccountStatus.getAttribute("value");
		if (actual.equals("1"))
			actual = actual.replaceAll("1", "Yes");
		else
			actual = actual.replaceAll("0", "No");
		if (passwordStatus.equals("No Password"))
			expected = "Yes";
		else
			expected = "No";
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying whether Non activated account status is Yes or No ");
		return expected;
	}
}
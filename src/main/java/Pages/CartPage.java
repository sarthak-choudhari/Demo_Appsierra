package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class CartPage extends BasePage {

	@FindBy(xpath = "//th[text()='SubTotal']")
	WebElement subTotal;

	@FindBy(xpath = "//li[@class='item']//button")
	WebElement checkout;

	@FindBy(xpath = "//select[@name='shipping_address_id']")
	WebElement shippingAddressDropdown;

	@FindBy(xpath = "//div[@class='top-checkout-container']//button[@title='Place Order Now']")
	WebElement placeOrder;

	@FindBy(xpath = "//input[@id='purchaseorder']")
	List<WebElement> purchaseOrder;

	@FindBy(xpath = "//input[@id='chcybersource']")
	List<WebElement> creditCard;

	@FindBy(xpath = "//div[@class='top-checkout-container']//input[@type='email']")
	WebElement email;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='firstname']")
	WebElement firstName;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='lastname']")
	WebElement lastName;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='telephone']")
	WebElement telephone;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='street[0]']")
	WebElement address;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='country_id']")
	WebElement country;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='city']")
	WebElement city;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='postcode']")
	WebElement zipcode;

	@FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='region_id']")
	WebElement state;

	@FindBy(xpath = "//input[@value='ups_01']")
	WebElement freeSuperSaver;

	@FindBy(xpath = "//input[@id='cashondelivery']")
	WebElement cashOnDelivery;

	@FindBy(xpath = "//div[@class='checkout-success']//following-sibling::p//span")
	WebElement orderID;

	@FindBy(xpath = "//span[@id='password-strength-meter-label']")
	WebElement passwordStrength;

	@FindBy(xpath = "//button[@id='empty_cart_button']")
	WebElement emptyCart;

	private static final Logger lOGGER = LogManager.getLogger(CartPage.class.getName());

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnCheckout() {

		wait.forPage();
		try {
			wait.forElementToBeVisible(subTotal);
			wait.forElementToBeVisible(checkout);
			wait.forElementToBeClickable(checkout);
			click(checkout);
			lOGGER.info("Selecting the checkout option");
		} catch (TimeoutException e) {
			wait.forElementToBeVisible(checkout);
			wait.forElementToBeClickable(checkout);
			click(checkout);
			lOGGER.info("Selecting the checkout option");
		}
	}

	public void verifyShippingAddress(boolean flag) {

		if (flag == true) {
			wait.forElementToBeVisible(shippingAddressDropdown);
			Assert.assertTrue(shippingAddressDropdown.isEnabled() == false);
		} else {
			wait.forElementToBeVisible(shippingAddressDropdown);
			Assert.assertTrue(shippingAddressDropdown.isEnabled() == true);
		}
		lOGGER.info("Verifying whether the shipping address is disabled or not");
	}

	public void verifyPaymentMethods() {

		Assert.assertTrue(purchaseOrder.size() == 0);
		Assert.assertTrue(creditCard.size() == 0);
		lOGGER.info("Verifying whether the payment options are available or not for approval required products");
	}

	public void proceedToCheckout() {

		// wait.forElementToBeClickable(placeOrder);
		click(freeSuperSaver);
		pause(10000);
		click(placeOrder);
		lOGGER.info("Clicking on checkout button");
	}

	public String enterShippingDetails(String user) {

		wait.forElementToBeVisible(email);
//		if (user.length() < 3)
//			user = user.concat("userautomation@yahoo.com");
		sendKeys(email, user);
		wait.forElementToBeVisible(firstName);
		sendKeys(firstName, "Test");
		wait.forElementToBeVisible(lastName);
		sendKeys(lastName, "Automation");
		wait.forElementToBeVisible(telephone);
		sendKeys(telephone, genRandomString(10));
		wait.forElementToBeVisible(address);
		sendKeys(address, "123, 4th street");
		wait.forElementToBeVisible(country);
		wait.forElementToBeVisible(city);
		sendKeys(city, "Hamilton");
		wait.forElementToBeVisible(zipcode);
		sendKeys(zipcode, "45011");
		wait.forElementToBeVisible(state);
		dropDownMethod(state, "Visibletext", "Ohio");
		wait.forPage();
		wait.forElementToBeVisible(freeSuperSaver);
		click(freeSuperSaver);
		wait.forPage();
		wait.forElementToBeVisible(cashOnDelivery);
		click(cashOnDelivery);
		return user;
	}

	public String getOrderID() {

		wait.forElementToBeVisible(orderID);
		return orderID.getText();
	}

	public String getPasswordStatus() {

		wait.forElementToBeVisible(passwordStrength);
		return passwordStrength.getText();
	}

	public String concatString(String user) {

		return user.concat("userautomation@yahoo.com");
	}

	public void removeAllItems() {

		try {
			wait.forElementToBeVisible(subTotal);
			wait.forElementToBeVisible(emptyCart);
			click(emptyCart);
			lOGGER.info("Clearing all the items from the cart before adding any products into it");
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}
}
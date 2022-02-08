package Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyAccountPage extends BasePage {

	protected Random random = new Random();
	protected Actions action = new Actions(driver);

	@FindBy(xpath = "//div[@class='item-order-row']")
	public List<WebElement> recentOrders;

	@FindBy(xpath = "//div[@class='col id']//a")
	public List<WebElement> orderIDs;

	@FindBy(xpath = "//div[@class='col shipping']")
	public List<WebElement> placedBy;

	@FindBy(xpath = "//input[@id='filter_value']")
	public WebElement searchField;

	@FindBy(xpath = "//button[@id='ordersearch']")
	public WebElement searchButton;

	@FindBy(xpath = "//td[@data-th='Order Customer']")
	public List<WebElement> orderCustomer;

	private static final Logger lOGGER = LogManager.getLogger(MyAccountPage.class.getName());

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	public void verifyContentsOfOrders() {

		Assert.assertEquals(recentOrders.size(), 5);
		lOGGER.info("Verifying the recent order contents are minimum in size");
	}

	public void verifyBackgroundColor() {

		int randomNumberIndex = random.nextInt(recentOrders.size());
		wait.forElementToBeVisible(recentOrders.get(randomNumberIndex));
		action.moveToElement(recentOrders.get(randomNumberIndex)).perform();
		String color = recentOrders.get(randomNumberIndex).getCssValue("background-color");
		color = color.substring(color.indexOf("(") + 1, color.lastIndexOf(","));
		color = convertRGBToHex(color);
		Assert.assertTrue(color.equalsIgnoreCase("#F3F3F3"));
		action.moveToElement(recentOrders.get(randomNumberIndex)).click().perform();
		lOGGER.info("Verifying the background color turns to grey when mouse-hover");
	}

	public ArrayList<String> getOrderIDDetails(int i) {

		ArrayList<String> orderDetails=new ArrayList<String>();
		wait.forElementToBeVisible(orderIDs.get(i));
		orderDetails.add(orderIDs.get(i).getText().replace("#", ""));
		wait.forElementToBeVisible(placedBy.get(i));
		orderDetails.add(placedBy.get(i).getText());
		lOGGER.info("Fetching the order details from Account dashboard section");
		return orderDetails;
	}

	public void verifyOrderDetails(ArrayList<String> orderDetails) {

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, orderDetails.get(0));
		wait.forElementToBeVisible(searchButton);
		click(searchButton);
		wait.forPage();
		wait.forElementToBeVisible(placedBy.get(0));
		String actual = placedBy.get(0).getText();
		String expected = orderDetails.get(1);
		Assert.assertEquals(actual, expected);
		lOGGER.info("verifying the order details in my orders section");
	}
}
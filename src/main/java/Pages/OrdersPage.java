package Pages;

import java.util.ArrayList;
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

public class OrdersPage extends BasePage {

	protected Random random = new Random();
	protected Actions action = new Actions(driver);

	@FindBy(xpath = "//li[@class='item orders']//a")
	public WebElement pageTitle;

	@FindBy(xpath = "//div[@class='item-order-row']")
	public List<WebElement> products;

	@FindBy(xpath = "//div[@class='item-order-row']//a[@class='link']")
	public List<WebElement> ordersTopBar;

	@FindBy(xpath = "//a[@class='details']")
	public List<WebElement> details;

	@FindBy(xpath = "//a[@class='details-link']")
	public List<WebElement> rightArrow;

	@FindBy(xpath = "//div[@class='item-order-row']//img")
	public List<WebElement> thumbnailImage;

	@FindBy(xpath = "//button[@title='Reorder']")
	public List<WebElement> reorder;

// ---------> Order details page locators

	@FindBy(xpath = "//div[@class='box-content']")
	public List<WebElement> columns;

	private static final Logger lOGGER = LogManager.getLogger(OrdersPage.class.getName());

	public OrdersPage(WebDriver driver) {
		super(driver);
	}

	public void verifyOrderDetailsPageTitle() {

		wait.forElementToBeVisible(pageTitle);
		Assert.assertEquals(pageTitle.getText(), pageTitle.getAttribute("title"));
		lOGGER.info("Verifying the title of order details page");
	}

	public void verifyTotalProductsOnPage() {

		Assert.assertEquals(products.size(), 4);
		lOGGER.info("Verifying the total products are minimum in size");
	}

	public void verifyOrdersSeperation() {

		int randomNumberIndex = random.nextInt(products.size());
		wait.forElementToBeVisible(products.get(randomNumberIndex));
		String color = products.get(randomNumberIndex).getCssValue("background-color");
		color = color.substring(color.indexOf("(") + 1, color.lastIndexOf(","));
		color = convertRGBToHex(color);
		Assert.assertTrue(color.equalsIgnoreCase("#F3F3F3"));
		lOGGER.info("Verifying the background color as grey orders top bar");

		randomNumberIndex = random.nextInt(products.size());
		wait.forElementToBeVisible(products.get(randomNumberIndex));
		String corners = products.get(randomNumberIndex).getCssValue("border-radius");
		corners = corners.substring(0, corners.lastIndexOf("p"));
		Assert.assertTrue(Integer.parseInt(corners) > 0);
		lOGGER.info("Verifying the border of orders to be rounded corners");

		randomNumberIndex = random.nextInt(thumbnailImage.size());
		wait.forElementToBeVisible(thumbnailImage.get(randomNumberIndex));
		Assert.assertTrue(thumbnailImage.get(randomNumberIndex).isDisplayed());
		lOGGER.info("Verifying the Thumbnail images are available for the orders");
	}

	public void verifyOrders() {

		int randomNumberIndex1 = random.nextInt(details.size());
		int randomNumberIndex2 = random.nextInt(rightArrow.size());
		wait.forElementToBeVisible(details.get(randomNumberIndex1));
		wait.forElementToBeVisible(rightArrow.get(randomNumberIndex2));
		List<WebElement> list = new ArrayList<WebElement>();
		list.add(details.get(randomNumberIndex1));
		list.add(rightArrow.get(randomNumberIndex2));
		int randomNumberIndex = random.nextInt(list.size());
		click(list.get(randomNumberIndex));
		lOGGER.info("Selecting either details or right arrow randomly");
		verifyOrderDetailsPageTitle();
		driver.navigate().back();
	}

	public void verifyReorderButton() {

		int randomNumberIndex = random.nextInt(reorder.size());
		wait.forElementToBeVisible(reorder.get(randomNumberIndex));
		action.moveToElement(reorder.get(randomNumberIndex)).perform();
		String color = getBackgroundColor(reorder.get(randomNumberIndex));
		color = convertRGBToHex(color);
		System.out.println(color);
		Assert.assertTrue(color.equalsIgnoreCase("#DC7416") || color.equalsIgnoreCase("#DF7616")
				|| color.equalsIgnoreCase("#DF7516"));
		lOGGER.info("Verifying the background color of reorder button turns to dark orange when mouse-hover");
	}

	public void verifyOrderDetailsPage() {

		int randomNumberIndex1 = random.nextInt(details.size());
		click(details.get(randomNumberIndex1));
		Assert.assertTrue(columns.size() < 5);
		lOGGER.info("Verifying the total columns are minimum in size");

		int randomNumberIndex = random.nextInt(reorder.size());
		wait.forElementToBeVisible(reorder.get(randomNumberIndex));
		String color = getBackgroundColor(reorder.get(randomNumberIndex));
		color = convertRGBToHex(color);
		Assert.assertTrue(color.equalsIgnoreCase("#F78F1E"));
		action.moveToElement(reorder.get(randomNumberIndex)).perform();
		color = getBackgroundColor(reorder.get(randomNumberIndex));
		color = convertRGBToHex(color);
		Assert.assertTrue(color.equalsIgnoreCase("#dc7416"));
		lOGGER.info("Verifying the background color of reorder button before and after mouse-hover");
	}
}
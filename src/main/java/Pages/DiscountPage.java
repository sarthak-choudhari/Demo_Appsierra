package Pages;

import java.awt.AWTException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class DiscountPage extends BasePage {

	@FindBy(xpath = "//input[@id='search']")
	public WebElement searchField;

	@FindBy(xpath = "//div[@class='form-search category-search ng-scope']//button[@title='Search']")
	public WebElement searchButton;

	@FindBy(xpath = "//div[@class='breadcrumbs']//strong")
	public WebElement breadcrumbTitle;

	@FindBy(xpath = "//p[@class='ss-ac-item-name ng-binding']")
	public List<WebElement> searchSuggestions;

	@FindBy(xpath = "//a//parent::li[@class='menu-static-width']//a[text()=' Barcoding']")
	public WebElement barcodingCategory;

	@FindBy(xpath = "//a[text()=' Barcoding']//parent::li[@class='menu-static-width']//li")
	public List<WebElement> barcodingSubcategory;

	@FindBy(xpath = "//h2[@class='product-name']//a")
	public List<WebElement> productsInCategories;

	@FindBy(xpath = "//button[@title='Add To Cart']")
	public List<WebElement> addToCart;

	@FindBy(xpath = "//span[@class='price']")
	public List<WebElement> productPrice;

	private static final Logger lOGGER = LogManager.getLogger(DiscountPage.class.getName());

	public DiscountPage(WebDriver driver) {
		super(driver);
	}

	public void enterProductToSearch(String product) {

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, product);
		wait.forElementToBeVisible(searchButton);
		click(searchButton);
		lOGGER.info("Entering the product to be searched on search field");
	}

	public void selectRandomSuggestionProduct() {

		int randomProduct = random.nextInt(searchSuggestions.size());
		wait.forElementToBeVisible(searchSuggestions.get(randomProduct / 2));
		click(searchSuggestions.get(randomProduct / 2));
		lOGGER.info("Searching for a random product among the list of suggestions");
	}

	public void selectRandomCategory() {

		Actions action = new Actions(driver);
		wait.forElementToBeVisible(barcodingCategory);
		int randomSubCategory = random.nextInt(barcodingSubcategory.size());
		action.moveToElement(barcodingCategory).moveToElement(barcodingSubcategory.get(randomSubCategory)).click()
				.build().perform();
		lOGGER.info("Mouse hover to random categories among the menu");
	}

	public void verifyProducts() {

		wait.forPage();
		for (int i = 0; i < productsInCategories.size(); i++) {
			wait.forPage();
			wait.forElementToBeVisible(productsInCategories.get(i));
			Assert.assertTrue(productsInCategories.get(i).isDisplayed());
			lOGGER.info("Verifying whether there are products in categories");
		}
	}

	public void verifyProductPrices() {

		for (int i = 0; i < productPrice.size(); i++) {
			wait.forElementToBeVisible(productPrice.get(i));
			Assert.assertTrue(productPrice.get(i).isDisplayed());
		}
		lOGGER.info("Verifying whether the Product's Price is visible or not");
	}

	public void verifyAddToCart() {

		for (int i = 0; i < addToCart.size(); i++) {
			wait.forElementToBeVisible(addToCart.get(i));
			Assert.assertTrue(addToCart.get(i).isDisplayed());
		}
		lOGGER.info("Verifying whether the Add To Cart button is visible or not");
	}

	public void verifyBreadcrumbs() {

		enterProductToSearch("Printer");
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("Verifying the breadcrumbs in barcodes discount");
	}

	public void verifyPageRedirect() {

		enterProductToSearch("Scanner");
		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("barcodegiant.com"));
		Assert.assertTrue(currentUrl.contains("https://www.barcodediscount.com/"));
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("Verifying the page redirect upon searching");
	}

	public void verifySearchSuggestions() {

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, "Mobile Computing");
//		selectRandomSuggestionProduct();
		searchField.sendKeys(Keys.ENTER);
		int randomProduct = random.nextInt(productsInCategories.size());
		wait.forElementToBeVisible(productsInCategories.get(randomProduct));
		click(productsInCategories.get(randomProduct));
		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("barcodegiant.com"));
		Assert.assertTrue(currentUrl.contains("https://www.barcodediscount.com/"));
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("verifying the page redirect upn clicking random product from suggestion list");
	}

	public void verifyProductsInCategories() throws AWTException {

		selectRandomCategory();
		verifyProducts();
		lOGGER.info("verifying the products and their respective price from category section");
	}

	public void verifyPriceAndAddToCart() {

		verifyProductPrices();
		verifyAddToCart();
	}
}
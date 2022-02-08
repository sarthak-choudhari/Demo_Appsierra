package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class MyProductsPage extends BasePage {

	@FindBy(xpath = "//div[@class='search-instock search-discontinued']//span")
	public List<WebElement> myProducts;

	@FindBy(xpath = "//span[text()='Needs Approval']//ancestor::td//following-sibling::td//button")
	public List<WebElement> addToCart;

	@FindBy(xpath = "//button[@name='finish_and_checkout']")
	public WebElement goToCart;

	private static final Logger lOGGER = LogManager.getLogger(MyProductsPage.class.getName());

	public MyProductsPage(WebDriver driver) {
		super(driver);
	}

	public void selectProduct() {

		for (int i = 0; i < addToCart.size(); i++) {
			wait.forElementToBeVisible(addToCart.get(i));
			js.clickElement(addToCart.get(i));
			lOGGER.info("Selecting the product that needs approval");
		}

		wait.forElementToBeVisible(goToCart);
		click(goToCart);
	}
}
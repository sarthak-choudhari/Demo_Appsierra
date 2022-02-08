package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ProductsPage extends BasePage {

	HomePage homepage = new HomePage(driver);

	@FindBy(xpath = "//h1[@class='product-title']")
	public WebElement productTitle;

	@FindBy(xpath = "//div[@id='description-short']")
	public WebElement productDesc;

	@FindBy(xpath = "//a//b")
	public List<WebElement> products;

	@FindBy(xpath = "//div[@class='top-description']//h1")
	public WebElement labelHeader;

	@FindBy(xpath = "//div[@class='search-blocks']")
	public WebElement searchBlocks;

	@FindBy(xpath = "//div[@class='pre-printed-block']")
	public WebElement preprintedBlock;

	@FindBy(xpath = "//div[@class='pre-printed-labels']")
	public WebElement preprintedLabels;

	private static final Logger lOGGER = LogManager.getLogger(ProductsPage.class.getName());

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyProduct() {

		wait.forElementToBeVisible(productTitle);
		Assert.assertTrue(productTitle.getText().contains("MC9300") == true);
		wait.forElementToBeVisible(productDesc);
		Assert.assertTrue(productDesc.isDisplayed() == true);
		for (int i = 0; i < 10; i++) {
			wait.forElementToBeVisible(products.get(i));
			Assert.assertTrue(products.get(i).isDisplayed() == true);
		}
		lOGGER.info("Verifying whether the product is correct as per searched or not");
	}

	public void verifyLabelPage() {

		wait.forElementToBeVisible(labelHeader);
		Assert.assertTrue(labelHeader.isDisplayed() == true);
		wait.forElementToBeVisible(preprintedBlock);
		Assert.assertTrue(preprintedBlock.isDisplayed() == true);
		wait.forElementToBeVisible(preprintedLabels);
		Assert.assertTrue(preprintedLabels.isDisplayed() == true);
		wait.forElementToBeVisible(searchBlocks);
		Assert.assertTrue(searchBlocks.isDisplayed() == true);
		lOGGER.info("Ensuring that the barcodes label page is not broken");
	}
}
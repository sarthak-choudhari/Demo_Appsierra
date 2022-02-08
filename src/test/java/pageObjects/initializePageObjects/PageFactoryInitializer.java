package pageObjects.initializePageObjects;

import org.openqa.selenium.support.PageFactory;

import pageObjects.modules.AssetsPage;
import pageObjects.modules.ControlTowerPage;
import pageObjects.modules.GhostInspectorPage;
import pageObjects.modules.HomePage;
import pageObjects.modules.LoginPage;
import pageObjects.modules.OrdersPage;
import pageObjects.modules.ProfilePage;
import pageObjects.modules.ShuftiProPage;
import pageObjects.modules.SignUpPage;
import pageObjects.modules.TradePage;
import pageObjects.modules.VerificationPage;
import utils.ExplicitWait;

public class PageFactoryInitializer extends ExplicitWait {

	public LoginPage loginPage() {
		return PageFactory.initElements(getWebDriver(), LoginPage.class);
	}

	public HomePage homePage() {
		return PageFactory.initElements(getWebDriver(), HomePage.class);
	}

	public AssetsPage assetsPage() {
		return PageFactory.initElements(getWebDriver(), AssetsPage.class);
	}

	public GhostInspectorPage GIPage() {
		return PageFactory.initElements(getWebDriver(), GhostInspectorPage.class);
	}

	public OrdersPage ordersPage() {
		return PageFactory.initElements(getWebDriver(), OrdersPage.class);
	}

	public ProfilePage profilePage() {
		return PageFactory.initElements(getWebDriver(), ProfilePage.class);
	}

	public ShuftiProPage shuftiProPage() {
		return PageFactory.initElements(getWebDriver(), ShuftiProPage.class);
	}

	public SignUpPage signUpPage() {
		return PageFactory.initElements(getWebDriver(), SignUpPage.class);
	}

	public TradePage tradePage() {
		return PageFactory.initElements(getWebDriver(), TradePage.class);
	}

	public VerificationPage verificationPage() {
		return PageFactory.initElements(getWebDriver(), VerificationPage.class);
	}

	public ControlTowerPage controlTowerPage() {
		return PageFactory.initElements(getWebDriver(), ControlTowerPage.class);
	}
}

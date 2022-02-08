package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.initializePageObjects.PageFactoryInitializer;

public class LoginTest extends PageFactoryInitializer {

	@BeforeClass
	public void applicationLogin() {
		homePage().clickOnAccountIcon();
		homePage().clickOnSignIn();
		loginPage().setUsername(EMAIL_ID);
		loginPage().setPassword(PASSWORD);
		loginPage().clickSubmit();
	}
	
	@Test(priority=1)
	public void test1() {
		System.out.println("Pass");
	}
	
	@Test(priority=2)
	public void test2() throws IOException {
		throw new IOException("Errrrrrrrrrrrooooorrrrrr");
	}

	@Test(priority=3)
	public void test3()  {
		System.out.println("Pass");
	}

	
}
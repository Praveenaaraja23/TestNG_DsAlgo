package testCases;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pageFactory.LoginPage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;

import java.io.IOException;

public class Login_Test extends BaseTests {
	LoginPage login;
	@BeforeMethod
	@Parameters("browser")
	public void setup() {
		LoggerLoad.info("Setting up for test");
		login = new LoginPage();
		login.GetStarted();
		login.clicksign();
	}

	@Test(dataProvider ="LoginTestData", dataProviderClass = LoginPage.class,retryAnalyzer=RetryAnalyzer.class)
	public void testLoginPage(String Username, String Password, String Message) throws InvalidFormatException, IOException {
		login.login(Username, Password);
		String ActulaResult = login.processCredentials(Username , Password);
		Assert.assertEquals( ActulaResult, Message, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + Message);
		if (Message.equalsIgnoreCase("You are logged in")) {
			login.Signout();
			LoggerLoad.info("Login successful, performing sign-out...");
		} else {
			LoggerLoad.info("Login unsuccessful, no need to perform sign-out.");
		}

	}
}





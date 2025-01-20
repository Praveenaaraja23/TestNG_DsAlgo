package testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseTest.BaseTests;
import pageFactory.HomePage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;

public class Home_Test extends  BaseTests{
	
	HomePage Home;
	@BeforeMethod
	@Parameters("browser")
	
	public void setup() {
		LoggerLoad.info("Setting up for test");
		Home = new HomePage();
		Home.GetStarted();
		
	}

	@Test(dataProvider ="HomeTestData", dataProviderClass = HomePage.class,retryAnalyzer=RetryAnalyzer.class,priority=1)
	public void testhomePage(String LinkText, String Message,String text,String href,String Message1) throws InvalidFormatException, IOException {

		Home.dropdownselect();
		Home.dropdownexcel(LinkText);
		String ActulaResult = Home.printwarnmsg();
		Assert.assertEquals( ActulaResult, Message, "Validation failed for code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + Message);

	}
	
	@Test(dataProvider ="HomeTestData", dataProviderClass = HomePage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testhomePage1(String LinkText, String Message,String text,String href,String Message1) throws InvalidFormatException, IOException {
		
		Home.checkallpagesexcel(text, href);
		String ActulaResult = Home.printwarnmsg();
		Assert.assertEquals( ActulaResult, Message1, "Validation failed for code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + Message1);
		
}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,priority=3)
	public void testsigninandregister()  {
		
		Home.signin();
		Home.register();
	}
}




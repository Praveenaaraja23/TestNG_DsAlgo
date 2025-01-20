package testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseTest.BaseTests;
import pageFactory.ArrayPage;
import pageFactory.LinkedlistPage;
import pageFactory.LoginPage;
import pageFactory.StackPage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;

public class Stack_Test extends BaseTests {

	BaseTests base;
	StackPage stack;
	String expectedMessage = "";
	String actualMessage = "";
	@BeforeMethod
	@Parameters("browser")
	public void enterstackpage() throws InvalidFormatException, IOException {
		base=new BaseTests();
		base.validlogin();
		stack =new StackPage();
		stack.getStartedbutton();
		stack.Operations_in_Stack_Link();	
		
	}
	
	@Test(dataProvider ="StackTestData", dataProviderClass = StackPage.class,retryAnalyzer=RetryAnalyzer.class,priority=1)
	public void testStackPage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		stack.navigateToLink(linkText);
		actualMessage = stack.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		stack.TryHere_Link();
		stack.Entercode_Tryeditor(Code);
		stack.RunbuttonLink();
		String ActulaResult = stack.getActualmsg();
		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}
	
	@Test(dataProvider ="Stackassert", dataProviderClass = StackPage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testPracticequestions(String Expectedtitle) {
		stack.Practicequestions_button();
		Assert.assertEquals(stack.getHomePageText(), Expectedtitle);
		LoggerLoad.info("No practice Questions blank page is displayed");    
	}
}

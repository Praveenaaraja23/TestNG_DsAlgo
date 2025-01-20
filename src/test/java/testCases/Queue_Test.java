package testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseTest.BaseTests;
import pageFactory.QueuePage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;


	
	public class Queue_Test extends BaseTests{
	
	QueuePage queue; 
	String expectedMessage = "";
	String actualMessage = "";
	BaseTests base;
	
	@BeforeMethod
	@Parameters("browser")
	public void enterqueuepage() throws InvalidFormatException, IOException {
		
		base=new BaseTests();
		base.validlogin();
		queue=new QueuePage();
		queue.getStarted();
		queue.click_implemenation_queue();
	}

	@Test(dataProvider ="queueTestData", dataProviderClass = QueuePage.class,retryAnalyzer=RetryAnalyzer.class,priority=1)
	
	public void testqueuePage(String linkText, String Code, String ExpectedOutput) {

		// Navigate to the link based on linkText
		queue.navigateToLink(linkText);
		
		actualMessage =queue.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		
		queue.click_Tryherebtn();
		queue.Entercode_Tryeditor(Code);
		queue.runbtn();
		String ActulaResult = queue.getActualmsg();
		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}
	
	@Test(dataProvider ="Queueassert", dataProviderClass = QueuePage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	
	public void testPracticequestions(String Expectedtitle) {

		queue.click_Practice_Questions();
		Assert.assertEquals(queue.getHomePageText(), Expectedtitle);
		LoggerLoad.info("NO questions found ");   
	
	
	}
}







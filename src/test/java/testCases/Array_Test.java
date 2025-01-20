package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseTest.BaseTests;
import pageFactory.ArrayPage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;

public class Array_Test extends BaseTests {
	
	ArrayPage arrays;
    BaseTests base;
	String expectedMessage = "";
	String actualMessage = "";
	@BeforeMethod
	@Parameters("browser")
	public void entertreepage() throws InvalidFormatException, IOException {
		base=new BaseTests();
		base.validlogin();
		arrays =new ArrayPage();
		arrays.getStartedbutton();
		arrays.Arrays_in_Python_Link();
		
		
	}
	@Test(dataProvider ="ArrayTestData", dataProviderClass = ArrayPage.class,retryAnalyzer=RetryAnalyzer.class, priority=1)
	public void testArrayPage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		arrays.navigateToLink(linkText);
		actualMessage = arrays.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		arrays.TryHere_Link();
		arrays.Entercode_Tryeditor(Code);
		arrays.RunbuttonLink();
		String ActulaResult = arrays.getActualmsg();
		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testPracticequestions() {
		arrays.Practicequestions_button();
//		Assert.assertEquals(arrays.getHomePageText(), Expectedtitle);
		Assert.assertEquals(arrays.getArrayPageTitle(),"Practice Questions");
 
	}
	

//	@Test(dataProvider ="PracticequestionTestData", dataProviderClass = ArrayPage.class,retryAnalyzer=RetryAnalyzer.class, priority=3)
//		public void PracticequestionsEditor(String linkText, String Code, String ExpectedOutput) {
//		arrays.Practicequestions_button();
//		arrays.navigateToLink(linkText);	
////		arrays.enterCodePractice(Code,null);(this one needs to uncomment if needed)
//		arrays.editorInput(Code);
//		arrays.RunbuttonLink();
//		String ActulaResult = arrays.getActualmsg();
//		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
//		LoggerLoad.info("Actual result  :" + ActulaResult);
//		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
//		
//		

//	}
		  
		  
	
}


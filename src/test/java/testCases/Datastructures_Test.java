package testCases;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import baseTest.BaseTests;
import utils.LoggerLoad;
import utils.RetryAnalyzer;
import pageFactory.DatastructuresPage;


public class Datastructures_Test extends BaseTests {
	DatastructuresPage datas;
	BaseTests base;

	@BeforeMethod
	@Parameters("browser")
	public void enterdataspage() throws InvalidFormatException, IOException {
		base = new BaseTests();
		base.validlogin();
		datas = new DatastructuresPage();
		datas.getStarted();
		datas.click_Timecomplexity_dspage();
	}

	@Test(dataProvider = "DatastructuresTestData", dataProviderClass = DatastructuresPage.class,retryAnalyzer=RetryAnalyzer.class,priority = 1)
	public void testdatasPage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		datas.navigateToLink(linkText);
		String actualMessage = datas.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		datas.click_Tryherebtn();
		datas.Entercode_Tryeditor(Code);
		datas.runbtn();
		String ActulaResult = datas.getActualmsg();
		Assert.assertEquals(ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}

	@Test(dataProvider ="Datastructuresassert", dataProviderClass = DatastructuresPage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testPracticequestions(String Expectedtitle) {
		datas.click_Practice_Questions();
		Assert.assertEquals(datas.Homepagetext(), Expectedtitle);
		//Assert.assertEquals(datas.Homepagetext(), "practice Questions");
		LoggerLoad.info("NO questions found ");
	}
}
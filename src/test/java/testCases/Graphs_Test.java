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
import pageFactory.GraphsPage;


public class Graphs_Test extends BaseTests {
	GraphsPage graph;
	BaseTests base;

	@BeforeMethod
	@Parameters("browser")
	public void entergraphpage() throws InvalidFormatException, IOException {
		base = new BaseTests();
		base.validlogin();
		graph = new GraphsPage();
		graph.getStarted();
		graph.click_graphpage();

	}

	@Test(dataProvider = "GraphsTestData", dataProviderClass = GraphsPage.class,retryAnalyzer=RetryAnalyzer.class,priority = 1)
	public void testgraphPage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		graph.navigateToLink(linkText);
		String actualMessage = graph.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		graph.click_Tryherebtn();
		graph.Entercode_Tryeditor(Code);
		graph.runbtn();
		String ActulaResult = graph.getActualmsg();
		Assert.assertEquals(ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}

	@Test(dataProvider ="Graphassert", dataProviderClass = GraphsPage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testPracticequestions(String Expectedtitle) {
		graph.click_Practice_Questions();
		Assert.assertEquals(graph.Homepagetext(), Expectedtitle);
		//Assert.assertEquals(graph.Homepagetext(), "practice Questions");
		LoggerLoad.info("NO questions found ");
	}
}

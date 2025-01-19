package testCases;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import baseTest.BaseTests;
import utils.LoggerLoad;
import pageFactory.TreePage;

public class Tree_Test extends BaseTests{
	TreePage tree;
	BaseTests base;

	@BeforeMethod
	@Parameters("browser")
	public void entertreepage() throws InvalidFormatException, IOException {
		base=new BaseTests();
		base.validlogin();
		tree=new TreePage();
		tree.getStarted();
		tree.click_overview_tree();
	}

	@Test(dataProvider ="treeTestData", dataProviderClass = TreePage.class,priority=1)
	public void testTreePage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		tree.navigateToLink(linkText);
		String actualMessage = tree.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		tree.click_Tryherebtn();
		tree.Entercode_Tryeditor(Code);
		tree.runbtn();
		String ActulaResult = tree.getActualmsg();
		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}
	
	
	@Test(dataProvider ="treeassert", dataProviderClass = TreePage.class,priority=2)
	public void testPracticequestions(String Expectedtitle) {
		tree.click_Practice_Questions();
		Assert.assertEquals(tree.Homepagetext(),Expectedtitle);
		LoggerLoad.info("NO questions found ");   
	}

}






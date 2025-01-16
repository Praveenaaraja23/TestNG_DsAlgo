package testCases;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import baseTest.BaseTests;
import utils.LoggerLoad;
import pageFactory.LoginPage;
import pageFactory.TreePage;

public class Tree_Test extends BaseTests{
	LoginPage	login;
	TreePage tree;

	@BeforeMethod
	@Parameters("browser")
	public void entertreepage() throws InvalidFormatException, IOException {
		login = new LoginPage();
		login.GetStarted();
		login.clicksign();
		String Username = "Vidya"; 
		String Password = "Kanaka@185";
		login.login(Username, Password);
		tree=new TreePage();
		tree.getStarted();
		tree.click_overview_tree();
	}

	@Test(dataProvider ="treeTestData", dataProviderClass = TreePage.class)
	public void testTreePage(String linkText, String Code, String ExpectedOutput) {
		// Navigate to the link based on linkText
		tree.navigateToLink(linkText);
		tree.click_Tryherebtn();
		tree.Entercode_Tryeditor(Code);
		tree.runbtn();
		String ActulaResult = tree.getActualmsg();
		Assert.assertEquals( ActulaResult, ExpectedOutput, "Validation failed for  code");
		LoggerLoad.info("Actual result  :" + ActulaResult);
		LoggerLoad.info("ExpectedOutput  :" + ExpectedOutput);
	}
	@Test
	public void testPracticequestions() {
		tree.click_Practice_Questions();
		Assert.assertEquals(tree.Homepagetext(),"practice Questions");
		LoggerLoad.info("NO questions found ");   
	}
}







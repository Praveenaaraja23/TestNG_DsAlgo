package testCases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import baseTest.BaseTests; 
import pageFactory.LinkedlistPage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;


public class Linkedlist_Test extends BaseTests {
	

	LinkedlistPage Linkedlist; 
	String expectedMessage = "";
	String actualMessage = "";
	BaseTests base;
	
	@BeforeMethod
	@Parameters("browser")
	public void setup() throws Exception {
		base=new BaseTests();
		base.validlogin();
		Linkedlist =new LinkedlistPage(); 
		Linkedlist.clickLLgetstarted();
		Linkedlist.clickIntroductionLink();	
		
	}
		
		   	
	@Test(dataProvider ="Linkedlistdata", dataProviderClass = LinkedlistPage.class,retryAnalyzer=RetryAnalyzer.class,priority=1)
	public void linkedlistpage(String linkText, String phythonCode, String ExpectedMessage) {
		
		Linkedlist.navigate(linkText);
		actualMessage = Linkedlist.getallpagestext();
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + linkText);	
		Assert.assertEquals( actualMessage,linkText);
		Linkedlist.clickTryhere();
		Linkedlist.EnterTryeditor(phythonCode);
		Linkedlist.clickrunbtn();
		actualMessage = Linkedlist.alertmsg();
		Assert.assertEquals( actualMessage, ExpectedMessage);
		LoggerLoad.info("Actual Message  :" + actualMessage);
		LoggerLoad.info("Expected Message  :" + ExpectedMessage);		
	}
	
	
	@Test(dataProvider ="Linkedlistassert", dataProviderClass = LinkedlistPage.class,retryAnalyzer=RetryAnalyzer.class,priority=2)
	public void testPracticequestions(String Expectedtitle) {
		Linkedlist.clickpracticequestionLink();
		Assert.assertEquals(Linkedlist.getHomePageText(), Expectedtitle);
		LoggerLoad.info("No practice Questions blank page is displayed");  
	}
	

		
	}
		
	
	


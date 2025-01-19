package testCases;


import java.lang.reflect.InvocationTargetException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import baseTest.BaseTests;
import pageFactory.RegisterPage;
import utils.LoggerLoad;
import utils.RetryAnalyzer;


public class Register_Test extends BaseTests{
	
	RegisterPage register; 
	String expectedMessage = "";
	String actualMessage = "";
	
	
	@BeforeMethod
	public void Registerpage() throws Exception {
		register =new RegisterPage(); 
		register.GetStarted();
		Thread.sleep(3000);
	    register.Rlink();
		Thread.sleep(3000);
	   
	}
@Test(dataProvider="RegisterData", dataProviderClass=RegisterPage.class,retryAnalyzer=RetryAnalyzer.class)
	
	public void registerUser(String userName,String password,String ConfirmPwd,String Message) throws InvocationTargetException  {
		register.loginclick(userName, password, ConfirmPwd);
		actualMessage = register.printPopupmessage(userName, password, ConfirmPwd);
		LoggerLoad.info("Actual Result  :" + actualMessage);
		LoggerLoad.info("Expected Result:" + Message);
		Assert.assertEquals(Message, actualMessage);
		
		
         
	}
	

}

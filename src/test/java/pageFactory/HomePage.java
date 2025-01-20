package pageFactory;
				
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import utils.DriverManager;
import utils.configReader;
import utils.excelReader;
			
			
	public class HomePage {
		WebDriver driver= DriverManager.getDriver();
		configReader configFileReader=DriverManager.configReader();
		String excelPath = configReader.excelpath();
		excelReader excelReader = new excelReader(excelPath);
				
		@FindBy(xpath = "//button[text()='Get Started']") WebElement getStarted_home;
				
		@FindBy (xpath ="//a[text()='Data Structures']")WebElement dropdown_datastructure;	
		@FindBy (xpath ="//a[@href='/register']")WebElement register;
		@FindBy (xpath ="//a[@href='/login']")WebElement signin;
				
		@FindBy(xpath = "//div[contains(text(),'You are not logged in')]") WebElement buttonwarningmsg;
			
				
			public HomePage() {
			
				PageFactory.initElements(driver , this);
			}
					
			public void GetStarted() {
				
				getStarted_home.click();
			}
			
			
			public String printwarnmsg() {
				
				String result  = buttonwarningmsg.getText();		
				return result;
			
			}
			
			
			public void dropdownselect() {
				
				dropdown_datastructure.click();
			}
			
			
			public void dropdownexcel(String LinkText) {
				WebElement link = driver.findElement(By.xpath("//a[text()='" + LinkText + "']"));
				link.click();
			}
			
			public void checkallpagesexcel(String text,String href ) {
				String xpathExpression = "//a[text()='" + text + "' and @href='" + href + "']";
				WebElement link1 = driver.findElement(By.xpath(xpathExpression));
				link1.click();
			}
			
			
			@DataProvider(name="HomeTestData")
			public  Object[][] getHomeTestData() throws Exception {
				return excelReader.readSheet("Homepage");
			
			}
				
			
			public void signin()
			{
				
				signin.click();
			}
			
			
			public void register()
			
			{
				register.click();
			
			}
			
			public String alert() {
			
				String msg =buttonwarningmsg.getText();
				return msg;
			
			}
			
			public String pagetitle()
			{
				return driver.getTitle();
			}
			
			}
			
			
			

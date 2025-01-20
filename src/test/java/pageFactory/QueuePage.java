package pageFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import utils.DriverManager;
import utils.configReader;
import utils.excelReader;

public class QueuePage {
	
	
	@FindBy(xpath = "//a[@href='queue']")WebElement getstartedqueue;
	
	@FindBy(xpath = "//a[@href='implementation-lists']")WebElement implementationLink; 
	
	@FindBy(xpath="//a[@href='/tryEditor']")WebElement tryHere;
	
	@FindBy(xpath = "//textarea[@tabindex='0']")WebElement tryEditor;
	
	@FindBy(xpath = "//button[@type='button']")WebElement runButton;
	
	@FindBy(id = "output")WebElement output;

	@FindBy(xpath = "//a[@class='list-group-item list-group-item-light text-info']")WebElement PracticeQuestionsLink;
	 
	@FindBy(xpath= "//a[text()='NumpyNinja']")WebElement hometext;
	
	@FindBy(xpath= "//div[@class='col-sm']/strong/p[@class='bg-secondary text-white']") WebElement allliststext;
	
	String result;
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver= DriverManager.getDriver();
	configReader configFileReader=DriverManager.configReader();
	
	public Object alert;
	
	public QueuePage() {

		PageFactory.initElements(driver , this);
	}
	
	public void getStarted() {
		getstartedqueue.click();

	}

	public void click_implemenation_queue() {
		implementationLink.click();

	}

	public void click_Practice_Questions() {
		PracticeQuestionsLink.click();

	}

	public void click_Tryherebtn() {
		tryHere.click();


	}
	 public String getHomePageText() {
			String hometitle = hometext.getText();
			return hometitle;

		}
	public String Entercode_Tryeditor(String Code)  {
		tryEditor.sendKeys(Code);
		return  Code;
	}

	public void runbtn() {

		runButton.click();

	}
	public String getActualmsg() {
		try {

			Alert alert = driver.switchTo().alert();	
			result=alert.getText();
			alert.accept();
		}catch(NoAlertPresentException e) {
			result=output.getText();
		}
		return result;
	}
	


	public void navigateToLink(String linkText) {
		WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
		link.click();
	}
     
     public String getallpagestext() {
    		String pages =allliststext.getText();
    		return pages;
    		
    	}

     
	@DataProvider(name="queueTestData")
	public  Object[][] getqueueTestData() throws Exception {
		return excelReader.readSheet("Queuepage");

	}
	
	@DataProvider(name="Queueassert")
    public  Object[][] checkassert() throws Exception {
    return excelReader.readSheet("Assertions");

    }

}


	
	


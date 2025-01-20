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

public class StackPage {
	
	@FindBy(xpath="//a[@href='stack']")WebElement getStarted;
	@FindBy(xpath="//a[@href='operations-in-stack']")WebElement Operations_in_Stack_Link;
	@FindBy(xpath="//a[@href='implementation']")WebElement Implementations_Link;
	@FindBy(xpath="//a[@href='stack-applications']")WebElement Applications_Link;
	@FindBy(xpath="//a[@href='/tryEditor']")WebElement TryHere_Link;
	@FindBy(xpath="//a[@href='/stack/practice']")WebElement PracticeQuestions_Link;
	@FindBy(xpath="//button[@type='button']")WebElement RunbuttonLink;
	@FindBy(xpath = "//textarea[@tabindex='0']")WebElement TryEditorbox;
	@FindBy (xpath="//pre[@id='output']")WebElement Console_Output;
	@FindBy(xpath= "//a[text()='NumpyNinja']")	WebElement hometext;
	@FindBy(xpath= "//div[@class='col-sm']/strong/p[@class='bg-secondary text-white']") WebElement allliststext;
	String result;
	

	
	
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver= DriverManager.getDriver();
	configReader configFileReader=DriverManager.configReader();
	public Object alert;
	
	public StackPage() {		

			PageFactory.initElements(driver , this);
		}
	
	public void getStartedbutton() {
		
		getStarted.click();
		
	}
	
	
	public void Operations_in_Stack_Link() {
		
		Operations_in_Stack_Link.click();
		
		
	}

	
	public void TryHere_Link() {
		
		TryHere_Link.click();			
	}
	
//	public String Homepagetext() {
//		String hometitle=hometext.getText();
//		return hometitle;
//
//	}
	
	 public String getHomePageText() {
			String hometitle = hometext.getText();
			return hometitle;

		}
	
	public String getallpagestext() {
		String pages =allliststext.getText();
		return pages;
		
	}
	
	public void RunbuttonLink() {
		
		RunbuttonLink.click();
		
	}
	
	public String Entercode_Tryeditor(String Code)  {
		TryEditorbox.sendKeys(Code);
		return  Code;
	}
	
	
	public void Practicequestions_button() {
		PracticeQuestions_Link.click();
	}

//	public void Practicequestions_Run_button() {
//
//		Practicequestions_Run_button.click();
//	}
//
//	public void Practicequestion_Submitbutton() {
//		Practicequestion_Submitbutton.click();
//	}
//	
	public String getStackPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	
	public void navigateToLink(String linkText) {
	
		WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
		link.click();
	
	}
	
	
	

     public String getActualmsg() {
		try {

			Alert alert = driver.switchTo().alert();	
			result=alert.getText();
			alert.accept();
		}catch(NoAlertPresentException e) {
			result=Console_Output.getText();
		}
		return result;
	}

	@DataProvider(name="StackTestData")
	public  Object[][] getStackTestData() throws Exception {
		return excelReader.readSheet("Stackpage");

	}
	
  @DataProvider(name="Stackassert")
  public  Object[][] checkassert() throws Exception {
  return excelReader.readSheet("Assertions");
  }

}

package pageFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utils.configReader;
import utils.DriverManager;
import utils.excelReader;

public class TreePage {

	@FindBy (xpath="//a[@href='tree']")
	WebElement Tree_Getstarted;
	@FindBy(xpath="//a[@href='overview-of-trees']")
	WebElement OverviewofTrees;
	@FindBy(xpath="//a[@href='/tryEditor']")
	WebElement TryHere;
	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement TryEditor;
	@FindBy(xpath = "//button[@type='button']")
	WebElement Runbutton;
	@FindBy (xpath="//pre[@id='output']")
	WebElement Output;
	@FindBy(xpath="//a[@href='/tree/practice']")
	WebElement Practice_Questions;
	@FindBy(xpath= "//a[text()='NumpyNinja']")
	WebElement hometext;
	String result;
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver= DriverManager.getDriver();
	configReader configFileReader=DriverManager.configReader();
	public Object alert;



	public TreePage() {

		PageFactory.initElements(driver , this);
	}

	public void getStarted() {
		Tree_Getstarted.click();

	}

	public void click_overview_tree() {
		OverviewofTrees.click();

	}

	public void click_Practice_Questions() {
		Practice_Questions.click();

	}

	public void click_Tryherebtn() {
		TryHere.click();


	}
	public String Homepagetext() {
		String hometitle=hometext.getText();
		return hometitle;

	}

	public String Entercode_Tryeditor(String Code)  {
		TryEditor.sendKeys(Code);
		return  Code;
	}

	public void runbtn() {

		Runbutton.click();

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
			result=Output.getText();
		}
		return result;
	}
	public String getTreePageTitle() {
		String title = driver.getTitle();
		return title;
	}

	@DataProvider(name="treeTestData")
	public  Object[][] getTreeTestData() throws Exception {
		return excelReader.readSheet("Treepage");

	}

}




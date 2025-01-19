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

public class DatastructuresPage {

	@FindBy(xpath = "//a[@href='data-structures-introduction']")
	WebElement getstarted;

	@FindBy(xpath = "//a[@href='time-complexity']")
	WebElement Timecomplexity;

	@FindBy(xpath = "//a[@href='/tryEditor']")
	WebElement TryHere;

	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement TryEditor;

	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement Runcommand;

	@FindBy(xpath = "//pre[@id='output']")
	WebElement Output;

	@FindBy(xpath = "//a[@href='/data-structures-introduction/practice']")
	WebElement PracticeQuestions;

	@FindBy(xpath = "//a[text()='NumpyNinja']")
	WebElement hometext;

	@FindBy(xpath = "//div[@class='col-sm']/strong/p[@class='bg-secondary text-white']")
	WebElement allliststext;

	String result;
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver = DriverManager.getDriver();
	configReader configFileReader = DriverManager.configReader();

	public Object alert;

	public DatastructuresPage() {

		PageFactory.initElements(driver, this);
	}

	public void getStarted() {
		// getstartedqueue.click();
		getstarted.click();
	}

	public void click_Timecomplexity_dspage() {
		// implementationLink.click();
		Timecomplexity.click();
	}

	public void click_Practice_Questions() {
		// PracticeQuestionsLink.click();
		PracticeQuestions.click();

	}

	public void click_Tryherebtn() {
		// tryHere.click();
		TryHere.click();

	}

	public String Homepagetext() {
		String hometitle = hometext.getText();
		return hometitle;

	}

	public String Entercode_Tryeditor(String Code) {
		// tryEditor.sendKeys(Code);
		TryEditor.sendKeys(Code);
		return Code;
	}

	public void runbtn() {

		// runButton.click();
		Runcommand.click();

	}

	public void navigateToLink(String linkText) {
		WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
		link.click();
	}

	public String getallpagestext() {
		String pages = allliststext.getText();
		return pages;

	}

	public String getActualmsg() {
		try {

			Alert alert = driver.switchTo().alert();
			result = alert.getText();
			alert.accept();
		} catch (NoAlertPresentException e) {
			result = Output.getText();
		}
		return result;
	}

	@DataProvider(name = "DatastructuresTestData")
	public Object[][] getDatastructuresTestData() throws Exception {
		return excelReader.readSheet("Datastructurespage");

	}
	
	@DataProvider(name="Datastructuresassert")
	public  Object[][] checkassert() throws Exception {
	return excelReader.readSheet("Assertions");

	}


}
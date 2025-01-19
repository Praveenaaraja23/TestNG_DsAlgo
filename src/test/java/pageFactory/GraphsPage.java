package pageFactory;

import java.util.NoSuchElementException;

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
import utils.LoggerLoad;
import utils.excelReader;

public class GraphsPage {

	@FindBy(xpath = "//a[@href='graph']")
	WebElement getstarted;

	@FindBy(xpath = "//a[@href='graph']")
	WebElement graph;

	@FindBy(xpath = "//a[@href='/tryEditor']")
	WebElement tryEditorLink;

	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement runbutton;

	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement tryEditor;

	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement runcmd;

	@FindBy(xpath = "//pre[@id='output']")
	WebElement Output;

	@FindBy(xpath = "//a[@href='/graph/practice']")
	WebElement practiceQuestionsLink;

	@FindBy(xpath = "//a[text()='NumpyNinja']")
	WebElement hometext;

	String result;
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver = DriverManager.getDriver();
	configReader configFileReader = DriverManager.configReader();

	public Object alert;

	public GraphsPage() {

		PageFactory.initElements(driver, this);
	}

	public void getStarted() {
		// getstartedqueue.click();
		getstarted.click();
	}

	public void click_graphpage() {

		graph.click();
	}

	public void click_Practice_Questions() {

		practiceQuestionsLink.click();

	}

	public void click_Tryherebtn() {
		// tryHere.click();
		tryEditorLink.click();

	}

	public String Homepagetext() {
		String hometitle = hometext.getText();
		return hometitle;

	}

	public String Entercode_Tryeditor(String Code) {
		// tryEditor.sendKeys(Code);
		// TryEditor.sendKeys(Code);
		tryEditor.sendKeys(Code);
		return Code;
	}

	public void runbtn() {

		// runButton.click();
		// Runcommand.click();
		runcmd.click();

	}

	public void navigateToLink(String linkText) {

		try {
			WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
			if (link.isDisplayed() && link.isEnabled()) {
				link.click();
			} else {
				LoggerLoad.info("Link is not clickable or visible.");
			}
		} catch (NoSuchElementException e) {
			LoggerLoad.error("Element not found: " + e.getMessage());
		}
//			WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
//			link.click();
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

	@DataProvider(name = "GraphsTestData")
	public Object[][] getDatastructuresTestData() throws Exception {
		return excelReader.readSheet("Graphpage");

	}

}

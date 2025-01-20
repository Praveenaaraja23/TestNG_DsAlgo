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

public class ArrayPage {

	
	@FindBy (xpath="//a[@href='array']")WebElement getStarted;
	@FindBy (xpath="//a[@href='arrays-in-python']")WebElement Arrays_in_Python_Link;
	@FindBy (xpath="//a[@href='arrays-using-list']")WebElement Arrays_Using_List_Link;
	@FindBy (xpath="//a[@href='basic-operations-in-lists']")WebElement Basic_Operations_in_Lists_Link;
	@FindBy (xpath="//a[@href='applications-of-array']")WebElement Applications_of_Array_Link;
	@FindBy (xpath="//a[@href='/tryEditor']")WebElement TryHere_Link;	
	@FindBy (xpath="//a[@href='/array/practice']")WebElement PracticeQuestions_Link;
	@FindBy (xpath="//button[@type='button']")WebElement RunbuttonLink;
	@FindBy(xpath = "//textarea[@tabindex='0']")WebElement TryEditorbox;
	@FindBy (xpath="//pre[@id='output']")WebElement Console_Output;
	@FindBy (xpath="//a[@href='/array/practice']")WebElement Practicequestions_button;
	@FindBy(xpath= "//a[text()='NumpyNinja']")	WebElement hometext;
	
	String result;
	@FindBy (xpath="//textarea[@tabindex='0']")WebElement editorInput;
	@FindBy (xpath="//button[text()='Run']")WebElement Practicequestions_Run_button;	
	@FindBy (xpath="//*[@id='answer_form']")WebElement Practicequestion_Editorinput;
	@FindBy (xpath="//input[@value='Submit']")WebElement Practicequestion_Submitbutton;

	@FindBy (xpath="//a[@href='/question/1']") WebElement SearchtheArrayLink;
	@FindBy (xpath="//a[@href='/question/2']") WebElement findMaxConsecutiveOnesLink;
	@FindBy (xpath="//a[@href='/question/3']") WebElement FindNumberswithEvenNumberofDigits;
	@FindBy (xpath="//a[@href='/question/4']") WebElement SquaresofaSortedArray;
	@FindBy(xpath= "//div[@class='col-sm']/strong/p[@class='bg-secondary text-white']") WebElement allliststext;
	@FindBy (id="output")WebElement output;
	
	
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver= DriverManager.getDriver();
	configReader configFileReader=DriverManager.configReader();
	public Object alert;
	
	public ArrayPage() {		

			PageFactory.initElements(driver , this);
		}
	
	public void getStartedbutton() {
		
		getStarted.click();
		
	}
	
	public void Arrays_in_Python_Link() {
		
		Arrays_in_Python_Link.click();		
		
	}
	
	public void TryHere_Link() {
		
		TryHere_Link.click();			
	}
	
	public String getallpagestext() {
		String pages =allliststext.getText();
		return pages;
		
	}
	
//	public String Homepagetext() {
//		String hometitle=hometext.getText();
//		return hometitle;
//
//	}
//	
	 public String getHomePageText() {
			String hometitle = hometext.getText();
			return hometitle;

		}
	
	public void RunbuttonLink() {
		
		RunbuttonLink.click();
		
	}
	
	public String Entercode_Tryeditor(String Code)  {
		TryEditorbox.sendKeys(Code);
		return  Code;
	}
	
	
	public void Practicequestions_button() {
		Practicequestions_button.click();
	}

	public void Practicequestions_Run_button() {

		Practicequestions_Run_button.click();
	}

	public void Practicequestion_Submitbutton() {
		Practicequestion_Submitbutton.click();
	}
	
	public String getArrayPageTitle() {
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

	@DataProvider(name="ArrayTestData")
	public  Object[][] getArrayTestData() throws Exception {
		return excelReader.readSheet("Arrayspage");

	}

	
	@DataProvider(name="PracticequestionTestData")
	public  Object[][] PracticequestionTestData() throws Exception {
		return excelReader.readSheet("PracticeQuestions");

	}

	
//	public void getCode(WebElement editorInput) {
//		String Code = editorInput("your Code");
//		enterCodePractice(Code,editorInput);
//	}
//	
	public String editorInput(String Code) {
		editorInput.sendKeys(Code);
		return Code;
		
}
//	public String enterCodePractice(String Code, WebElement editorInput) {
//		String[] str1 = Code.split("\n");
//		new Actions(driver).moveToElement(editorInput).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
//		for (int i = 0; i < str1.length; i++) {
//			if (str1[i].equalsIgnoreCase("\\b")) {
//				editorInput.sendKeys(Keys.BACK_SPACE);
//			} else {
//				editorInput.sendKeys(str1[i]);
//				editorInput.sendKeys(Keys.RETURN);
//			}
//		}
//		return Code;
//	}

	
   public void SearchtheArrayLink()
	   {
		   SearchtheArrayLink.click();
	   }
	
//   @DataProvider(name="Arraysassert")
//   public  Object[][] checkassert() throws Exception {
//   return excelReader.readSheet("Assertions");
//   }
//	Practicequestion_Editorinput
	
//	public void enterPythonCodePractice(String Sheetname,int Rownumber) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
//		util.waitForElement(Practicequestion_Editorinput);
//		String code = util.getCodefromExcel(Sheetname, Rownumber);
//		util.enterCodePractice(code, editorInput);
//
//	}

//	public String getExpectedResult(String Sheetname,int Rownumber) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
//		String expectedResult = util.getResultfromExcel(Sheetname, Rownumber);
//		return expectedResult;
//	}
	

}

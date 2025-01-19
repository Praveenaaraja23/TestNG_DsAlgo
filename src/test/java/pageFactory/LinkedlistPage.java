package pageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import utils.DriverManager;
import utils.configReader;
import utils.excelReader;


public class LinkedlistPage {
	
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);
	WebDriver driver= DriverManager.getDriver();
	configReader configFileReader=DriverManager.configReader();
	

	 String getalertmsg;
	String pythoncode;
	
	   @FindBy (xpath = "//a[@href='linked-list']") WebElement getstartedlinkedlist;
	   @FindBy (xpath="//a[@href='introduction']")WebElement introductionLink;  
		@FindBy (xpath="//a[@href='/linked-list/creating-linked-list/']")WebElement creatinglinkedlistLink; 
		@FindBy (xpath="//a[@href='/linked-list/types-of-linked-list/']")WebElement typesOfLinkedlistLink;  
		@FindBy (xpath="//a[@href='/linked-list/implement-linked-list-in-python/']")WebElement implementLinkedlistInPythonLink; 
		@FindBy (xpath="//a[@href='/linked-list/traversal/']")WebElement traversalLink; 
		@FindBy (xpath="//a[@href='/linked-list/insertion-in-linked-list/']")WebElement insertionLink; 
		@FindBy (xpath="//a[@href='/linked-list/deletion-in-linked-list/']")WebElement deletionLink; 
		@FindBy (xpath="//a[@href='/linked-list/practice']")WebElement practicequestionLink; 
		@FindBy(xpath = "//a[@href='/tryEditor']")WebElement tryHerebtn;
		@FindBy(xpath = "//textarea[@tabindex='0']")WebElement entertryeditor; 
		@FindBy(xpath = "//button[@type='button']")WebElement runButton;
		@FindBy(id = "output")WebElement output;
		@FindBy(xpath= "//h4[text()='Linked List']") WebElement linkedlisthometext;
		@FindBy(xpath= "//div[@class='col-sm']/strong/p[@class='bg-secondary text-white']") WebElement allliststext;
		@FindBy(xpath = "//a[text()='NumpyNinja']")
		WebElement hometext;

		 public LinkedlistPage() {
		    	
				PageFactory.initElements(driver,this);
		}
		 
		 public void clickLLgetstarted() {
			 getstartedlinkedlist.click();
			 
}
		 public void clickIntroductionLink() {
				
				introductionLink.click();
			}	 

 public void clickpracticequestionLink() {
	 
	 practicequestionLink.click(); 	
		
	}
 
 public String getHomePageText() {
		String hometitle = hometext.getText();
		return hometitle;

	}


public void clickTryhere() {
	tryHerebtn.click();
	 
}

public String EnterTryeditor(String pythoncode)  {

	
	 entertryeditor.sendKeys(pythoncode);
	return pythoncode;


}
public void clickrunbtn() {
	
	runButton.click();	  
}


public String alertmsg() {
    try {
        Alert alert = driver.switchTo().alert();
        getalertmsg = alert.getText();
        alert.accept();
        return getalertmsg;
    } catch (Exception e) {
    	
    	getalertmsg=output.getText();
    }
    return getalertmsg;
}
public String getallpagestext() {
	String pages =allliststext.getText();
	return pages;
	
}

public void navigate(String linkText) {
    WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
    link.click();
}

public String getlinkedTitle() {
	String title = driver.getTitle();
	return title;
}

@DataProvider(name="Linkedlistdata")
public Object[][] LinkedListData() throws Exception {
	return excelReader.readSheet("Linkedlist");

}

@DataProvider(name="Linkedlistassert")
public  Object[][] checkassert() throws Exception {
return excelReader.readSheet("Assertions");

}

}
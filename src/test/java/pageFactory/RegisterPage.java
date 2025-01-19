package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utils.DriverManager;
import utils.configReader;
import utils.excelReader;


public class RegisterPage {
	
	
	WebDriver driver = DriverManager.getDriver();
	configReader configFileReader = DriverManager.configReader();
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);


	@FindBy(xpath = "//button[text()='Get Started']")
	WebElement Getstartedbtn;
	@FindBy(xpath = "//a[text()='NumpyNinja']")
	WebElement hometext;
	@FindBy(xpath = "//h5[@class='card-title']")
	WebElement HomeDStext;
	@FindBy(xpath = "//a[text()=' Register ']")
	WebElement Registerlink;
	@FindBy(name = "username")
	WebElement UName;
	@FindBy(name = "password1")
	WebElement Pwd;
	@FindBy(name = "password2")
	WebElement confirmpwd;
	@FindBy(xpath = "//input[@value='Register']")
	WebElement Registerbtn;
	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement errorMessage;
	
	
	@FindBy(xpath = "//div[@class=\"alert alert-primary\"]") WebElement successmsg;
	
	
	public RegisterPage() {
		
		PageFactory.initElements(driver, this);
	}


	public void GetStarted() {
		Getstartedbtn.click();
	}


	public void Rlink() {

		Registerlink.click();
	}

	public void loginclick(String username, String password, String Passwordconfirmation) {

		UName.clear();
		UName.sendKeys(username);
		Pwd.clear();
		Pwd.sendKeys(password);
		confirmpwd.clear();
		confirmpwd.sendKeys(Passwordconfirmation);
		 Registerbtn.click();
	}
	
	public String printPopupmessage(String Username, String Password, String confirmpassword) {
		String Message = "";

		if (Username.isEmpty()) {
			Message = UName.getAttribute("validationMessage");

		}

		else if (Password.isEmpty()) {
			Message = Pwd.getAttribute("validationMessage");
		}
		else if (confirmpassword.isEmpty()) {
			Message = confirmpwd.getAttribute("validationMessage");
		}

		else {
			if (!Username.isEmpty()||!Password.isEmpty()||!confirmpassword.isEmpty()) {
				Message=errorMessage.getText();	
			}
			else {
				
				Message = successmsg.getText();
			}
		}

		return Message;
	}

	@DataProvider(name = "RegisterData")
	public  Object[][] registerData() throws Exception {
		return excelReader.readSheet("Register");

	}
	}



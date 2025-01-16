package pageFactory;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utils.configReader;
import utils.DriverManager;
import utils.excelReader;

public class LoginPage {

	WebDriver driver = DriverManager.getDriver();
	configReader configFileReader = DriverManager.configReader();
	String excelPath = configReader.excelpath();
	excelReader excelReader = new excelReader(excelPath);

	@FindBy(xpath = "//button[@class='btn']")
	WebElement getstarted;

	@FindBy(xpath = "//a[@href='/login']")
	WebElement Signin;

	@FindBy(id = "id_username")
	WebElement username1;

	@FindBy(id = "id_password")
	WebElement password1;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement Login;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement successmsg;

	@FindBy(xpath = "//a[text()='Sign out']")
	WebElement signout;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement errormessage;

	@FindBy(xpath = "//label[@for='id_username']")
	WebElement usernamefield;

	@FindBy(xpath = "//label[@for='id_password']")
	WebElement passwordfield;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void GetStarted() {
		getstarted.click();
	}

	public void clicksign() {
		Signin.click();
	}

	public void login(String Username,String Password) throws IOException, InvalidFormatException {
		username1.clear();
		username1.sendKeys(Username);
		password1.clear();
		password1.sendKeys(Password);
		Login.click();
	}

	public void Signout() {
		signout.click();  

	}

	public String processCredentials(String username, String password) {
		String message = "";


		if (username.isEmpty() && password.isEmpty()) {
			message = username1.getAttribute("validationMessage");

		}

		else if (username.isEmpty()) {
			message = username1.getAttribute("validationMessage");

		}

		else if (password.isEmpty()) {
			message = password1.getAttribute("validationMessage");
		}

		else {
			if (isLoginSuccessful()) {
				message = successmsg.getText();
			}
			else {
				message = errormessage.getText();
			}
		}

		return message;
	}

	private boolean isLoginSuccessful() {
		try {

			return successmsg.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginTestData")
	public  Object[][] getLoginTestData() throws Exception {
		return excelReader.readSheet("Login");

	}
}





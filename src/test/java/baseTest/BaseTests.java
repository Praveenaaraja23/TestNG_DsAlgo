package baseTest;

import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pageFactory.LoginPage;
import utils.LoggerLoad;
import utils.configReader;
import utils.excelReader;
import utils.DriverManager;
//import com.aventstack.chaintest.plugins.ChainTestListener;



 // @Listeners(ChainTestListener.class)
	public class BaseTests {
	    private static WebDriver driver;
	    private static DriverManager driverFactory;
	    configReader configFileReader = new configReader();
	    @Parameters("browser")
	    @BeforeMethod
	    public void before() throws Throwable {
	        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
	        if (browser == null) {
	            System.out.println("About to open browser: chrome");
	            driverFactory = new DriverManager();
	            driver.get(configReader.getApplicationUrl());
	            driver = driverFactory.initializeDriver("chrome");
	        } else {
	            driverFactory = new DriverManager();
	            driver = driverFactory.initializeDriver(browser);
	            driver.get(configReader.getApplicationUrl());
	            LoggerLoad.info("Initializing driver for : "+ browser);
	        }
	        
	    }
	    
	    public void validlogin() throws IOException, InvalidFormatException {
	    	LoginPage login;
	    	String excelPath = configReader.excelpath();
	    	excelReader excelReader1 = new excelReader(excelPath);
			Object[][] validLoginData = excelReader1.readSheetWithColumns("Login", Arrays.asList("Username", "Password"));
			String Username = validLoginData[0][0].toString(); // Assuming the first row
			String Password = validLoginData[0][1].toString();
			login = new LoginPage();
			login.GetStarted();
			login.clicksign();
			login.login(Username, Password);
	    }
	    
	     @AfterMethod
	    public  void after() throws Throwable {
	        driverFactory.quitDriver();
	    }
	}
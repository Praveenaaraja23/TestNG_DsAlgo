package baseTest;
	import org.openqa.selenium.WebDriver;
	import org.testng.Reporter;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Parameters;
	import utils.LoggerLoad;
    import utils.configReader;
    import utils.DriverManager;

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
	    
	     @AfterMethod
	    public  void after() throws Throwable {
	        driverFactory.quitDriver();
	    }
	}



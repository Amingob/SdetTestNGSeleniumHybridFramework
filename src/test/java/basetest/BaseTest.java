package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.LoginPage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import utils.ReadConfig;

public class BaseTest {

    ReadConfig readConfig = new ReadConfig();

    public String strURL = readConfig.getApplicationURL();
    public String strUserName = readConfig.getUserName();
    public String strPassword = readConfig.getpassword();

    public static Logger logger;
    public static WebDriver driver;

    public LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod
    public void launchBrowser(String browser){
        logger = Logger.getLogger(this.getClass());
        PropertyConfigurator.configure("src/test/resources/log4j.properties");

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();
        } else if(browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }else if(browser.equals("edge")){
            System.setProperty("webdriver.edge.driver", readConfig.getMsEdgePath());
            driver = new EdgeDriver();
        }

        loginPage = new LoginPage(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        logger.info("Test has started");

    }

    @AfterMethod
    public void tearDown(){
        logger.info("Test has finished");
        driver.quit();
    }
}

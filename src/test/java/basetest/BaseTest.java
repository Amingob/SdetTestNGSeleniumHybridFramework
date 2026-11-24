package basetest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import java.io.File;
import java.io.IOException;

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

    public static void captureScreen(WebDriver driver, String tname) throws IOException {
        isAlertPresent(); // Ensures no open alert blocks the screenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshots/"+ tname +".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }
    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert().accept(); // Closes the "User or Password is not valid" alert
            driver.switchTo().defaultContent();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

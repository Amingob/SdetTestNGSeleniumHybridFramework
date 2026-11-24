package testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    String expectedTitle = "Guru99 Bank Manager HomePage";

    @Test
    public void testLoginWithValidUserNameAndValidPassword() throws InterruptedException, IOException {

        driver.get(strURL);

        loginPage.enterUserName(strUserName);
        loginPage.enterPassword(strPassword);
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        // Dismiss the alert if it pops up so the browser page can be captured
        isAlertPresent();

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            captureScreen(driver, "testLoginWithValidUserNameAndValidPassword");
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithInvalidUserNameAndValidPassword() throws InterruptedException, IOException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName("TH"+strUserName+"TH");
        Thread.sleep(3000);
        loginPage.enterPassword(strPassword);
        loginPage.clickBtnLogin();

        Thread.sleep(3000);
        // Dismiss the alert if it pops up so the browser page can be captured
        isAlertPresent();

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            captureScreen(driver, "testLoginWithInvalidUserNameAndValidPassword");
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithValidUserNameAndInvalidPassword() throws InterruptedException, IOException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName(strUserName);
        Thread.sleep(3000);
        loginPage.enterPassword("TH"+strPassword+"TH");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);
        // Dismiss the alert if it pops up so the browser page can be captured
        isAlertPresent();

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            captureScreen(driver, "testLoginWithValidUserNameAndInvalidPassword");
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithInvalidUserNameAndInvalidPassword() throws InterruptedException, IOException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName("TH"+strUserName+"TH");
        Thread.sleep(3000);
        loginPage.enterPassword("TH"+strPassword+"TH");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);
        // Dismiss the alert if it pops up so the browser page can be captured
        isAlertPresent();

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            captureScreen(driver, "testLoginWithInvalidUserNameAndInvalidPassword");
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithNoUserNameAndNoPassword() throws InterruptedException, IOException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName(" ");
        Thread.sleep(3000);
        loginPage.enterPassword(" ");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);
        // Dismiss the alert if it pops up so the browser page can be captured
        isAlertPresent();

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            captureScreen(driver, "testLoginWithNoUserNameAndNoPassword");
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }
}

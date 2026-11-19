package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    String expectedTitle = "Guru99 Bank Manager HomePage";

    @Test
    public void testLoginWithValidUserNameAndValidPassword() throws InterruptedException {

        driver.get(strURL);

        loginPage.enterUserName(strUserName);
        loginPage.enterPassword(strPassword);
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithInvalidUserNameAndValidPassword() throws InterruptedException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName("TH"+strUserName+"TH");
        Thread.sleep(3000);
        loginPage.enterPassword(strPassword);
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithValidUserNameAndInvalidPassword() throws InterruptedException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName(strUserName);
        Thread.sleep(3000);
        loginPage.enterPassword("TH"+strPassword+"TH");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithInvalidUserNameAndInvalidPassword() throws InterruptedException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName("TH"+strUserName+"TH");
        Thread.sleep(3000);
        loginPage.enterPassword("TH"+strPassword+"TH");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }

    @Test
    public void testLoginWithNoUserNameAndNoPassword() throws InterruptedException {

        driver.get(strURL);

        Thread.sleep(3000);
        loginPage.enterUserName(" ");
        Thread.sleep(3000);
        loginPage.enterPassword(" ");
        loginPage.clickBtnLogin();

        Thread.sleep(3000);

        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Assert.assertTrue(true);
            logger.info("Test has passed");
        }else{
            Assert.assertTrue(false);
            logger.info("Test has failed");
        }
    }
}

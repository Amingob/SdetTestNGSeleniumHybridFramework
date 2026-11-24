package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.XLUtils;

import java.io.IOException;

public class TC002_LoginTestDDT extends BaseTest {

    String expectedTitle = "Guru99 Bank Manager HomePage";

    @Test(dataProvider = "LoginData")
    public void loginTestDDT(String userName, String passWord) throws InterruptedException, IOException {

        driver.get(strURL);

        loginPage = new LoginPage(driver);

        loginPage.enterUserName(userName);
        loginPage.enterPassword(passWord);
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

    @DataProvider(name="LoginData")
    String[][] getData() throws IOException {

        String path = System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\LoginData.xlsx";

        int rowNum = XLUtils.getRowCount(path,"Sheet1");
        int colNum = XLUtils.getCellCount(path,"Sheet1",1);

        String[][] loginData = new String[rowNum][colNum];

        for(int i=1; i <= rowNum; i++){

            for(int j=0; j< colNum ;j++){
                loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);//1 0

            }
        }
        return loginData;
    }

}

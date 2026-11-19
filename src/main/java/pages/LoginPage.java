package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy( name = "uid" )
    private WebElement usernameInput;

    @FindBy( name = "password" )
    private WebElement passwordInput;

    @FindBy( name= "btnLogin" )
    private WebElement loginButton;

    public void enterUserName(String userName) {
        usernameInput.sendKeys(userName);

    }
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);

    }
    public void clickBtnLogin() {
        loginButton.click();

    }
}

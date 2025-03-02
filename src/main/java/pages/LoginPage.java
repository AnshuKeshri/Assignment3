package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "idToken1")  
    WebElement usernameField;

    @FindBy(id = "idToken2")  
    WebElement passwordField;

    @FindBy(id = "login-button")  
    WebElement loginButton;

   
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void loginToSauceLabs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utils.TestListener;

@Listeners(TestListener.class)  
public class LoginTest {
    public WebDriver driver; 
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
    	System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // Ensure path is correct
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.saucelabs.com/am/XUI/#login/");
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        loginPage.loginToSauceLabs(username, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs"), "Login failed or incorrect URL after login!");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
            {"validUser", "validPass"},
            {"invalidUser", "invalidPass"}
            
        };
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

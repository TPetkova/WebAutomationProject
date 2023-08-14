package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.LoginPage;

public class Hooks {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeTest
    public void setup() {
        System.out.println("This is setup method");
        driver = new ChromeDriver();
        driver.get("https://robotsparebinindustries.com/");
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}

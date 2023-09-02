package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username") private WebElement username;
    @FindBy(id = "password") private WebElement password;
    @FindBy(xpath = "//button[contains(text(),'Log in')]") private WebElement signInButton;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        signInButton.click();
    }
}

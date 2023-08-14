package tests;

import helpers.Hooks;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTests extends Hooks{
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

//    public HomePageTests(WebDriver driver) {
//        this.driver = driver;
//        this.loginPage = new LoginPage(driver);
//        this.homePage = new HomePage(driver);
//    }

    @Test
    public void positiveDifference() {
        loginPage.login("maria", "thoushallnotpass");
        homePage.setFirstName("TestFirstName01");
        homePage.setLastName("TestLastName01");
        homePage.selectTarget("$5,000");
        homePage.setSalesResult("6000");
        homePage.clickSubmitButton();
        //Assert.assertEquals(homePage.getName().contains());
        Assert.assertTrue(homePage.getName().contains("TestFirstName01"));
        Assert.assertTrue(homePage.getName().contains("TestLastName01"));
        Assert.assertEquals(homePage.getTarget(),"$5,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$6,000","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),"$1,000","Difference is different than expected");
    }
}

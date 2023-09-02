package tests;

import helpers.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTests{
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

//    public HomePageTests(WebDriver driver) {
//        this.driver = driver;
//        this.loginPage = new LoginPage(driver);
//        this.homePage = new HomePage(driver);
//    }
@BeforeTest
public void setup() {
    System.out.println("This is setup method");
    driver = new ChromeDriver();
    driver.get("https://robotsparebinindustries.com/");
    this.homePage = new HomePage(driver);
    this.loginPage = new LoginPage(driver);
}

    @Test(priority = 1)
    public void positiveDifference() {
        loginPage.login("maria", "thoushallnotpass");
        homePage.verifyNameIsDisplayed();
        homePage.setFirstName("TestFirstName01");
        homePage.setLastName("TestLastName01");
        homePage.selectTarget("$5,000");
        homePage.setSalesResult("6000");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.getName(), "TestFirstName01 TestLastName01", "Name is not as expected");
        Assert.assertEquals(homePage.getTarget(),"$5,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$6,000","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),1000.0,"Difference is not calculated properly");
        Assert.assertEquals(homePage.getDifference(),"$1,000","Difference shown is not as expected");
        Assert.assertTrue(homePage.calculateDifference()== homePage.getDifferenceDouble(), "Calculation should be equal to shown difference on the page");
    }

    @Test(priority = 2)
    public void negativeDifference() {
        homePage.verifyNameIsDisplayed();
        homePage.setFirstName("TestFirstName02");
        homePage.setLastName("TestLastName02");
        homePage.selectTarget("$10,000");
        homePage.setSalesResult("2000");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.getName(), "TestFirstName02 TestLastName02", "Name is not as expected");
        Assert.assertEquals(homePage.getTarget(),"$10,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$2,000","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),-8000.0,"Difference is not calculated properly");
        Assert.assertEquals(homePage.getDifference(),"$-8,000","Difference shown is not as expected");
        Assert.assertTrue(homePage.calculateDifference()== homePage.getDifferenceDouble(), "Calculation should be equal to shown difference on the page");
    }

    @Test(priority = 2)
    public void zeroDifference() {
        homePage.verifyNameIsDisplayed();
        homePage.setFirstName("TestFirstName03");
        homePage.setLastName("TestLastName03");
        homePage.selectTarget("$15,000");
        homePage.setSalesResult("15000");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.getName(), "TestFirstName03 TestLastName03", "Name is not as expected");
        Assert.assertEquals(homePage.getTarget(),"$15,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$15,000","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),0,"Difference is not calculated properly");
        Assert.assertEquals(homePage.getDifference(),"$0","Difference shown is not as expected");
        Assert.assertTrue(homePage.calculateDifference()== homePage.getDifferenceDouble(), "Calculation should be equal to shown difference on the page");
    }

    @Test(priority = 2)
    public void negativeResult() {
        homePage.verifyNameIsDisplayed();
        homePage.setFirstName("TestFirstName04");
        homePage.setLastName("TestLastName04");
        homePage.selectTarget("$5,000");
        homePage.setSalesResult("-1500");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.getName(), "TestFirstName04 TestLastName04", "Name is not as expected");
        Assert.assertEquals(homePage.getTarget(),"$5,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$-1,500","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),-6500.0,"Difference is not calculated properly");
        Assert.assertEquals(homePage.getDifference(),"$-6,500","Difference shown is not as expected");
        Assert.assertTrue(homePage.calculateDifference()== homePage.getDifferenceDouble(), "Calculation should be equal to shown difference on the page");
    }

    @Test(priority = 2)
    public void zeroResult() {
        homePage.verifyNameIsDisplayed();
        homePage.setFirstName("TestFirstName05");
        homePage.setLastName("TestLastName05");
        homePage.selectTarget("$20,000");
        homePage.setSalesResult("0");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.getName(), "TestFirstName05 TestLastName05", "Name is not as expected");
        Assert.assertEquals(homePage.getTarget(),"$20,000","Target is different than expected");
        Assert.assertEquals(homePage.getResult(),"$0","Result is different than expected");
        Assert.assertEquals(homePage.calculateDifference(),-20000.0,"Difference is not calculated properly");
        Assert.assertEquals(homePage.getDifference(),"$-20,000","Difference shown is not as expected");
        Assert.assertTrue(homePage.calculateDifference()== homePage.getDifferenceDouble(), "Calculation should be equal to shown difference on the page");
    }

    @Test(priority = 3)
    public void checkSumPeople() {
        Assert.assertEquals(homePage.countTableRows(),5, "All active sales people should be 5");
        //add one more assert with element in another test case
    }

    @Test(priority = 3)
    public void checkSumPeopleShownOnPage() {
        Assert.assertEquals(homePage.getActiveSalesPeopleQuantity(),homePage.countTableRows(), "All active sales people shown on page should be " + homePage.countTableRows());
    }

    @Test(priority = 3)
    public void checkSumTarget() {
        Assert.assertEquals(homePage.sumAllTargetSales(),55000, "All target sales should be 55 000");
    }

    @Test(priority = 3)
    public void checkSumTargetShownOnpage() {
        Assert.assertEquals(homePage.getTotalSales(),homePage.sumAllTargetSales(), "All target sales shown on page should be " + homePage.sumAllTargetSales());
    }

    @Test(priority = 3)
    public void checkSumResult() {
        Assert.assertEquals(homePage.sumAllResultSales(),21500, "All result sales should be 21 500");
    }

    @Test(priority = 3)
    public void checkSumResultShownOnpage() {
        Assert.assertEquals(homePage.getTotalResults(),homePage.sumAllResultSales(), "All result sales shown on page should be " + homePage.sumAllResultSales());
    }

    @Test(priority = 3)
    public void checkSumDifference() {
        Assert.assertEquals(homePage.sumAllDifferences(),-33500, "All records differences should be -33 500");
    }

    @Test(priority = 3)
    public void checkSumDifferenceShownOnpage() {
        Assert.assertEquals(homePage.getTotalDifference(),homePage.sumAllDifferences(), "All differences shown on page should be " + homePage.sumAllDifferences());
    }

    @Test(priority = 4)
    public void checkShowPerformance() {
        homePage.clickShowPerformanceButton();

        if (homePage.calculateDifference() < 0) {
            Assert.assertEquals(homePage.checkPerformance(), "The boss wants to see you...");
        } else if (homePage.calculateDifference() == 0 || (homePage.calculateDifference() > 0 && homePage.calculateDifference() <= 20000)) {
            Assert.assertEquals(homePage.checkPerformance(), "A positive result. Well done!");
        } else if (homePage.calculateDifference() > 20000 && homePage.calculateDifference() <= 45000) {
            Assert.assertEquals(homePage.checkPerformance(), "Magnificent!");
        } else if (homePage.calculateDifference() > 45000) {
            Assert.assertEquals(homePage.checkPerformance(), "Holy macaroni!");
        } else {
            Assert.assertEquals(homePage.checkPerformance(), "There is no such number");
        }

    }

    @Test(priority = 5)
    public void checkHidePerformance() {
        homePage.clickHidePerformance();
        Assert.assertNull(homePage.checkPerformance());
    }

    @Test(priority = 6)
    public void checkEmptyFirstName() {
        Assert.assertTrue(homePage.getFirstName().isEmpty(),"The name should be empty " + homePage.getFirstName());
        homePage.setLastName("TestLastName06");
        homePage.selectTarget("$50,000");
        homePage.setSalesResult("50000");
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.countTableRows(), 5, "There shouldn't be added new row for TestLastName06");
        Assert.assertTrue(homePage.verifyEmptyMessageShow());
    }

    @Test(priority = 7)
    public void checkEmptyLastName() {
        homePage.setFirstName("TestFirstName07");
        homePage.clearLastName();
        Assert.assertTrue(homePage.getLastName().isEmpty(),"The name should be empty " + homePage.getLastName());
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.countTableRows(), 5, "There shouldn't be added new row for TestLastName07");
        Assert.assertTrue(homePage.verifyEmptyMessageShow());
    }

    @Test(priority = 8)
    public void checkEmptyResult() {
        homePage.setLastName("TestLastName07");
        homePage.clearResult();
        Assert.assertTrue(homePage.getResultFromField().isEmpty(),"The result should be empty " + homePage.getResultFromField());
        homePage.clickSubmitButton();
        Assert.assertEquals(homePage.countTableRows(), 5, "There shouldn't be added new row for TestLastName07");
        Assert.assertTrue(homePage.verifyEmptyMessageShow());
    }

    @Test(priority = 9)
    public void deleteEntries() {
        homePage.clickDeleteEntriesButton();
        homePage.waitUntilTableDeleted();
        Assert.assertEquals(homePage.countTableRows(),0, "There are still " + homePage.countTableRows() + " records");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}

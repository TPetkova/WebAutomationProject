package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname") private WebElement firstName;
    @FindBy(id = "lastname") private WebElement lastName;
    @FindBy(id = "salestarget") private WebElement salesTarget;
    @FindBy(id = "salesresult") private WebElement salesResult;
    @FindBy(xpath = "//button[contains(text(),'Submit')]") private WebElement submitButton;
    @FindBy(xpath = "//div[@class='alert alert-dark sales-summary']//div//span[contains(text(),'Active sales people:')]/following-sibling::span[1]")
    private WebElement activeSalesPeopleQuantity;
    @FindBy(xpath = "//div[@class='alert alert-dark sales-summary']//div//span[contains(text(),'Expected sales:')]/following-sibling::span[1]")
    private WebElement totalExpectedSalesTarget;
    @FindBy(xpath = "//div[@class='alert alert-dark sales-summary']//div//span[contains(text(),'Actual sales:')]/following-sibling::span[1]")
    private WebElement totalActualSalesResult;
    @FindBy(xpath = "//div[@class='alert alert-dark sales-summary']//div//span[contains(text(),'Difference:')]/following-sibling::span[1]")
    private WebElement totalDifference;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr//td[1]") private WebElement name;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr//td[2]") private WebElement targetTable;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr//td[3]") private WebElement resultTable;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr//td[4]") private WebElement differenceTable;
    @FindBy(className = "btn btn-info btn-secondary") private WebElement showPerformanceButton;
    @FindBy(className = "btn btn-danger btn-secondary") private WebElement deleteEntriesButton;

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void selectTarget(String target) {
        Select targetNumber = new Select(salesTarget);
        targetNumber.selectByVisibleText(target);
    }

    public void setSalesResult(String salesResult) {
        this.salesResult.sendKeys(salesResult);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public double calculateDifference() {
        String resultTrim = this.salesResult.getText().substring(1);
        double result = Double.parseDouble(resultTrim);
        String targetTrim = this.salesTarget.getText().substring(1);
        double target = Double.parseDouble(targetTrim);
        double difference = result - target;
        return difference;
    }

    public String getName() {
        return name.getText();
    }

    public String getTarget() {
        return targetTable.getText();
    }

    public String getResult() {
        return resultTable.getText();
    }

    public String getDifference() {
        return differenceTable.getText();
    }
}

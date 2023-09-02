package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    Duration timeout = Duration.ofSeconds(5);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.ID, using = "firstname") private WebElement firstName;
    @FindBy(id = "lastname") private WebElement lastName;
    @FindBy(id = "salestarget") private WebElement salesTarget;
    @FindBy(id = "salesresult") private WebElement salesResult;
    @FindBy(xpath = "//button[contains(text(),'Submit')]") private WebElement submitButton;
    @FindBy(xpath = "//div//span[contains(text(),'Active sales people:')]/following-sibling::span[1]")
    private WebElement activeSalesPeopleQuantity;
    @FindBy(xpath = "//div//span[contains(text(),'Expected sales:')]/following-sibling::span[1]")
    private WebElement totalExpectedSalesTarget;
    @FindBy(xpath = "//div//span[contains(text(),'Actual sales:')]/following-sibling::span[1]")
    private WebElement totalActualSalesResult;
    @FindBy(xpath = "//div//span[contains(text(),'Difference:')]/following-sibling::span[1]")
    private WebElement totalDifference;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr[1]//td[1]") private WebElement name;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr[1]//td[2]") private WebElement targetTable;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr[1]//td[3]") private WebElement resultTable;
    @FindBy(xpath = "//div[@id='sales-results']//table//tbody//tr[1]//td[4]") private WebElement differenceTable;
    @FindBy(xpath = "//button[contains(text(),'Show performance')]") private WebElement showPerformanceButton;
    @FindBy(xpath = "//button[contains(text(),'Delete all sales entries')]") private WebElement deleteEntriesButton;
    @FindBy(xpath = "//button[contains(text(),'Hide performance')]") private WebElement hidePerformanceButton;
    @FindBy(xpath = "//span[@class='performance']") private WebElement performanceMessage;
    //@FindBy(xpath = "//*[contains(text(),'Please fill out this field')]") private WebElement emptyFieldMessage;
    @FindBy(xpath = "//*[text() = 'Please fill out this field']") private WebElement emptyFieldMessage;

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
        String resultTrim = this.resultTable.getText().substring(1);
        String stringWithoutCommas = resultTrim.replace(",", "");
        double result = Double.parseDouble(stringWithoutCommas);
        String targetTrim = this.targetTable.getText().substring(1);
        String stringWithoutCommasTarget = targetTrim.replace(",", "");
        double target = Double.parseDouble(stringWithoutCommasTarget);
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

    public double getDifferenceDouble() {
        String diff = differenceTable.getText().substring(1);
        String diffRemoveComma = diff.replace(",","");
        double difference = Double.parseDouble(diffRemoveComma);
        return difference;
    }

    public boolean verifyNameIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstname")));
        return firstName.isDisplayed();
    }

    public void clickShowPerformanceButton() {
        showPerformanceButton.click();
    }

    public void clickDeleteEntriesButton() {
        deleteEntriesButton.click();
    }

    public String checkPerformance() {
        try {
            performanceMessage.isDisplayed();
            return performanceMessage.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            //this is the case where element is not displayed, hide performance is clicked
            return null;
        }

        //use try catch instead
    }

    public void clickHidePerformance() {
        hidePerformanceButton.click();
    }

    public int countTableRows() {
        int tableRows = driver.findElements(By.xpath("//div[@id='sales-results']//table//tbody//tr")).size();
        return tableRows;
    }

    public void waitUntilTableDeleted() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sales-results']//table//tbody//tr[1]//td[1]")));
    }

    public boolean verifyEmptyMessageShow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Please fill out this field']")));
        return emptyFieldMessage.isDisplayed();
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public void clearLastName() {
        lastName.clear();
    }

    public void clearResult() {
        salesResult.clear();
    }

    public String getResultFromField() {
        return salesResult.getText();
    }

    public int getActiveSalesPeopleQuantity() {
        String activePeopleQuantityStr = activeSalesPeopleQuantity.getText();
        int activePeopleQuantity = Integer.parseInt(activePeopleQuantityStr);
        return activePeopleQuantity;
    }

    public int getTotalSales() {
        String totalSalesStr = totalExpectedSalesTarget.getText().substring(1);
        String saleRemoveComma = totalSalesStr.replace(",","");
        int totalSales = Integer.parseInt(saleRemoveComma);
        return totalSales;
    }

    public int sumAllTargetSales() {
        int sumTargets = 0;
        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='sales-results']//table//tbody//tr"));
        for (WebElement row : rows) {
            WebElement columns = row.findElement(By.xpath(".//td[2]"));

            String targetValueStr = columns.getText().substring(1);
            String targetRemoveComma = targetValueStr.replace(",","");
            int targetValue = Integer.parseInt(targetRemoveComma);
            sumTargets = sumTargets + targetValue;
        }
        return sumTargets;
    }

    public int getTotalResults() {
        String totalResultsStr = totalActualSalesResult.getText().substring(1);
        String resultRemoveComma = totalResultsStr.replace(",","");
        int totalResults = Integer.parseInt(resultRemoveComma);
        return totalResults;
    }

    public int sumAllResultSales() {
        int sumResults = 0;
        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='sales-results']//table//tbody//tr"));
        for (WebElement row : rows) {
            WebElement columns = row.findElement(By.xpath(".//td[3]"));

            String resultValueStr = columns.getText().substring(1);
            String resultRemoveComma = resultValueStr.replace(",","");
            int resultValue = Integer.parseInt(resultRemoveComma);
            sumResults = sumResults + resultValue;
        }
        return sumResults;
    }

    public int getTotalDifference() {
        String totalDifferenceStr = totalDifference.getText().substring(1);
        String differenceRemoveComma = totalDifferenceStr.replace(",","");
        int totalDifference = Integer.parseInt(differenceRemoveComma);
        return totalDifference;
    }

    public int sumAllDifferences() {
        int sumDifferences = 0;
        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='sales-results']//table//tbody//tr"));
        for (WebElement row : rows) {
            WebElement columns = row.findElement(By.xpath(".//td[4]"));

            String diffValueStr = columns.getText().substring(1);
            String diffRemoveComma = diffValueStr.replace(",","");
            int diffValue = Integer.parseInt(diffRemoveComma);
            sumDifferences = sumDifferences + diffValue;
        }
        return sumDifferences;
    }

}

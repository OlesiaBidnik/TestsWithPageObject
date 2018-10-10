import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    private By firstNameField = By.xpath("//input[@name = 'firstname']");
    private By lastNameField = By.xpath("//input[@name = 'lastname']");
    private By emailField = By.xpath("//input[@name = 'reg_email__']");
    private By reEnterEmail = By.xpath("//input[@name='reg_email_confirmation__']");
    private By passwordField = By.xpath("//input[@name = 'reg_passwd__']");
    private By dayDropDown = By.xpath("//select[@id = 'day']");
    private String dayDropDownOption = "//select[@id = 'day']/option[@value = '%s']";
    private By monthDropDown = By.xpath("//select[@id = 'month']");
    private String monthDropDownOption = "//select[@id = 'month']/option[text() = '%s']";
    private By yearDropDown = By.xpath("//select[@id = 'year']");
    private String yearDropDownOption = "//select[@id = 'year']/option[@value = '%s']";
    private String sexRadioButton = "//label[text() = '%s']/preceding-sibling::input";
    private By signUpButton = By.xpath("//button[@name = 'websubmit']");
    private By errorNotification = By.xpath("//i[@class = '_5dbc img sp_n5C5Uq05yB8 sx_842229']/preceding-sibling::div/input[@aria-required = 'true']");
    private String errorMessage = "//div[text() = \"%s\"]";

    public SignUpPage typeFirstName(String name){
        driver.findElement(firstNameField).sendKeys(name);
        return this;
    }

    public SignUpPage typeLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public SignUpPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeReEnterEmail(String email){
        driver.findElement(reEnterEmail).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage setDay(String day){
        driver.findElement(dayDropDown).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(dayDropDownOption, day)))).click();
        return this;
    }

    public SignUpPage setMonth(String month){
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption,month)))).click();
        return this;
    }

    public SignUpPage setYear(String year) {
        driver.findElement(yearDropDown).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(yearDropDownOption, year)))).click();
        return this;
    }

    public SignUpPage setSex(String value){
        driver.findElement(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage clickSignUpButton(){
        driver.findElement(signUpButton).click();
        return this;
    }

    public boolean isErrorVisible(String message){
        return driver.findElement(By.xpath(String.format(errorMessage, message))).isDisplayed();
    }

    public boolean isReEnterEmailVisible(){
        return driver.findElement(reEnterEmail).isDisplayed();
    }


    public List<WebElement> getErrors(){
        return driver.findElements(errorNotification);
    }

}

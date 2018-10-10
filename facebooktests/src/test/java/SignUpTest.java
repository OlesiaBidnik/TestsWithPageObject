import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {

    WebDriver driver;
    SignUpPage page;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/users/OlesiaBidnik/downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://en-gb.facebook.com/");
    }

    @Test
    public void signUpWithoutPassword(){
        page = new SignUpPage(driver);
        page.typeFirstName("Test");
        page.typeLastName("Test");
        page.typeEmail("test@mail.ru");
        page.typeReEnterEmail("test@mail.ru");
        page.setDay("5");
        page.setMonth("Mar");
        page.setYear("1987");
        page.setSex("Male");
        page.clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Enter a combination of at least six numbers, letters and punctuation marks (like ! and &)."));
    }

    @Test
    public void signUpWithoutGender(){
        page = new SignUpPage(driver);
        page.typeFirstName("Test");
        page.typeLastName("Test");
        page.typeEmail("test@mail.ru");
        page.typeReEnterEmail("test@mail.ru");
        page.typePassword("12345");
        page.setDay("5");
        page.setMonth("Mar");
        page.setYear("1987");
        page.clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a gender. You can change who can see this later."));
    }

    @Test
    public void signUpWithInvalidEmail(){
        page = new SignUpPage(driver);
        page.typeFirstName("Test");
        page.typeLastName("Test");
        page.typeEmail("test.mail.ru");
        page.clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please enter a valid mobile number or email address."));

    }

    @Test
    public void signUpWithPhoneNumber(){
        page = new SignUpPage(driver);
        page.typeEmail("89870000000");
        page.clickSignUpButton();
        Assert.assertFalse(page.isReEnterEmailVisible());

    }

    @Test
    public void signUpWithEmptyData(){
        page = new SignUpPage(driver);
        page.clickSignUpButton();
        Assert.assertEquals(5, page.getErrors().size());
        Assert.assertTrue(page.isErrorVisible("What's your name?"));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static utils.DataGenerator.getRandomEmail;
import static utils.DataGenerator.getRandomString;

public class CreateNewUserTest {

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "userData")
    public Object[] userData() {
        User user = new User();
        user.setFirstName(getRandomString(5));
        user.setLastName(getRandomString(5));
        user.setAddress1(getRandomString(5));
        user.setPostcode("12345");
        user.setCity(getRandomString(5));
        user.setEmail(getRandomEmail(7));
        user.setPhone("+79876543210");
        user.setCountry("United States");
        user.setPassword(getRandomString(5));
        return new Object[]{user};
    }

    @Test(dataProvider = "userData")
    public void checkNewUserRegistered(User user) {
        driver.get("http://localhost/litecart/en/");
        homePage = new HomePage(driver);
        registrationPage = homePage.openRegistrationPage();
        homePage = registrationPage.registerUser(user);
        homePage.logout();
        homePage.login(user.getEmail(), user.getPassword());
        homePage.logout();
    }
}

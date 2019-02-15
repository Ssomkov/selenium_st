package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.account.LoginPage;
import pages.account.MainPage;

public class CategoryItemsTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkMenuItemsHeaders() {
        driver.get("http://localhost/litecart/admin/login.php");
        String login = "admin";
        String password = "admin";
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(login, password);
        mainPage.verifyAllCategoryHaveHeaders();
    }
}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.account.CountriesPage;
import pages.account.LoginPage;
import pages.account.MainPage;

public class CountriesSortingTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CountriesPage countriesPage;

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
    public void checkCountriesSorted() {
        driver.get("http://localhost/litecart/admin/login.php");
        String login = "admin";
        String password = "admin";
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(login, password);
        countriesPage = mainPage.openCountriesPage();
        countriesPage.verifyCountriesListSorted();
        countriesPage.verifyZonesInCountrySorted();
    }
}
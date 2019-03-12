package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.account.CountriesPage;
import pages.account.CountryPage;
import pages.account.LoginPage;
import pages.account.MainPage;
import utils.Navigation;

import java.util.concurrent.TimeUnit;

public class NewWindowsTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CountriesPage countriesPage;
    private CountryPage countryPage;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkLinksOpenedInNewWindows() {
        Navigation navigation = new Navigation(driver);
        loginPage = navigation.openLoginPage();
        String login = "admin";
        String password = "admin";
        mainPage = loginPage.login(login, password);
        countriesPage = mainPage.openCountriesPage();
        countryPage = countriesPage.editFirstCountry();
        countryPage.verifyAllLinksOpenInNewWindow();
    }
}

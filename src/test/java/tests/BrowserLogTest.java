package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.account.LoginPage;
import pages.account.MainPage;
import pages.account.catalog.CatalogPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserLogTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CatalogPage catalogPage;


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences log = new LoggingPreferences();
        log.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, log);
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkMessagesInBrowserLog() {
        driver.get("http://localhost/litecart/admin/login.php");
        String login = "admin";
        String password = "admin";
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(login, password);
        catalogPage = mainPage.openCatalogPage();
        catalogPage.openAllProductsInRubberDucks();
        //проверка логов
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        Assert.assertTrue(logs.isEmpty(), "Лог содержит сообщения: " + logs.toString());
    }
}
